package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.BattleCannotStartBecauseRequirementsAreNotMetException;
import de.ethasia.exorions.core.battle.BattleField;
import de.ethasia.exorions.core.battle.BattleFieldAbilityIdentifiers;
import de.ethasia.exorions.core.battle.NoBattleInProgressException;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.battle.TeamIdentifiers;
import de.ethasia.exorions.core.mocks.TestExorions;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityRequiredLevelTables;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleFieldTest {
    
    @Test(expected = BattleCannotStartBecauseRequirementsAreNotMetException.class)
    public void testStartBattle_noTeamsAreSet_throwsException() {
        BattleField testCandidate = new BattleField();
        testCandidate.startBattle();
    }
    
    @Test(expected = BattleCannotStartBecauseRequirementsAreNotMetException.class)
    public void testStartBattle_bothTeamsAreTheSame_throwsException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));      
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamOne);
        
        testCandidate.startBattle();
    }   
    
    @Test(expected = BattleCannotStartBecauseRequirementsAreNotMetException.class)
    public void testStartBattle_oneTeamIsEmpty_throwsException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        teamOne.addExorion(TestExorions.findExorionById(0));
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
    }    
    
    @Test
    public void testStartBattle_differentTeamsAreSetWithEqualExorion_teamOneMoves() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability);    
        
        IndividualExorion teamOneExorionOne = TestExorions.findExorionById(0);
        IndividualExorion teamOneExorionTwo = TestExorions.findExorionById(0);
        IndividualExorion teamOneExorionThree = TestExorions.findExorionById(0);
        
        IndividualExorion teamTwoExorionOne = TestExorions.findExorionById(0);
        IndividualExorion teamTwoExorionTwo = TestExorions.findExorionById(0);
        IndividualExorion teamTwoExorionThree = TestExorions.findExorionById(0);       
        
        teamOneExorionOne.learnAbilityOnSlotThree(directDamageEffect);
        teamOneExorionTwo.learnAbilityOnSlotThree(directDamageEffect);
        teamOneExorionThree.learnAbilityOnSlotThree(directDamageEffect);
        
        teamTwoExorionOne.learnAbilityOnSlotThree(directDamageEffect);
        teamTwoExorionTwo.learnAbilityOnSlotThree(directDamageEffect);
        teamTwoExorionThree.learnAbilityOnSlotThree(directDamageEffect);        
        
        teamOne.addExorion(teamOneExorionOne);
        teamOne.addExorion(teamOneExorionTwo);
        teamOne.addExorion(teamOneExorionThree);
        
        teamTwo.addExorion(teamTwoExorionOne);
        teamTwo.addExorion(teamOneExorionTwo);
        teamTwo.addExorion(teamOneExorionThree);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
        
        assertThat(testCandidate.teamOneHasToMove(), is(equalTo(true)));
    }  
    
    @Test
    public void testStartBattle_teamOneExorionIsSlower_teamTwoMoves() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();         
        
        IndividualExorion teamOneExorionOne = TestExorions.findExorionById(1);
        IndividualExorion teamTwoExorionOne = TestExorions.findExorionById(0); 
        
        teamOneExorionOne.learnAbilityOnSlotTwo(abilityTwo);
        teamTwoExorionOne.learnAbilityOnSlotTwo(directDamageEffect);
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
        
        assertThat(testCandidate.teamOneHasToMove(), is(equalTo(false)));
    }
    
    @Test
    public void testGetCurrentExorionOfFirstTeam_firstTeamHasExorionOnSlotOne_thatExorionIsReturned() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();         
        
        IndividualExorion firstExorionOfFirstTeam = TestExorions.findExorionById(1);
        IndividualExorion firstExorionOfSecondTeam = TestExorions.findExorionById(0);
        firstExorionOfFirstTeam.learnAbilityOnSlotOne(abilityTwo);
        firstExorionOfSecondTeam.learnAbilityOnSlotOne(directDamageEffect);        
        
        teamOne.addExorion(firstExorionOfFirstTeam);
        teamTwo.addExorion(firstExorionOfSecondTeam);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();

        assertThat(testCandidate.getCurrentExorionOfFirstTeam(), is(equalTo(firstExorionOfFirstTeam)));
    }
    
    @Test
    public void testGetCurrentExorionOfSecondTeam_secondTeamHasExorionOnSlotTwo_thatExorionIsReturned() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        IndividualExorion firstExorionOfFirstTeam = TestExorions.findExorionById(1);
        IndividualExorion firstExorionOfSecondTeam = TestExorions.findExorionById(0);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build(); 
        
        firstExorionOfFirstTeam.learnAbilityOnSlotOne(abilityTwo);
        firstExorionOfSecondTeam.learnAbilityOnSlotOne(directDamageEffect);
        
        teamOne.addExorion(firstExorionOfFirstTeam);
        teamTwo.replaceExorionAtWith(1, firstExorionOfSecondTeam);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();

        assertThat(testCandidate.getCurrentExorionOfSecondTeam(), is(equalTo(firstExorionOfSecondTeam)));
    }    
    
    @Test
    public void testGetCurrentExorionOfFirstTeam_firstTeamHasExorionOnSlotThree_thatExorionIsReturned() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        IndividualExorion firstExorionOfFirstTeam = TestExorions.findExorionById(1);
        IndividualExorion firstExorionOfSecondTeam = TestExorions.findExorionById(0);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build(); 
        
        firstExorionOfFirstTeam.learnAbilityOnSlotOne(abilityTwo);
        firstExorionOfSecondTeam.learnAbilityOnSlotOne(directDamageEffect);        
        
        teamOne.replaceExorionAtWith(2, firstExorionOfFirstTeam);
        teamTwo.addExorion(firstExorionOfSecondTeam);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();

        assertThat(testCandidate.getCurrentExorionOfFirstTeam(), is(equalTo(firstExorionOfFirstTeam)));
    }    
    
    @Test(expected = BattleCannotStartBecauseRequirementsAreNotMetException.class)
    public void testStartBattle_oneTeamHasExorionWithNoAbilities_throwsException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam();
        
        IndividualExorion teamTwoExorionOne = TestExorions.findExorionById(0);
        IndividualExorion teamTwoExorionTwo = TestExorions.findExorionById(0);
        IndividualExorion teamTwoExorionThree = TestExorions.findExorionById(0); 
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageEffect.decorate(ability); 
        
        teamTwoExorionOne.learnAbilityOnSlotOne(directDamageEffect);
        teamTwoExorionTwo.learnAbilityOnSlotOne(directDamageEffect);
        teamTwoExorionThree.learnAbilityOnSlotOne(directDamageEffect);
        
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));
        
        teamTwo.addExorion(teamTwoExorionOne);
        teamTwo.addExorion(teamTwoExorionTwo);
        teamTwo.addExorion(teamTwoExorionThree);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
    } 
    
    @Test
    public void testGetTeamForWhichInputIsAwaited_battleHasJustStarted_bothTeamsNeedToEnterInput() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = TestExorions.findExorionById(0);
        IndividualExorion teamTwoExorionOne = TestExorions.findExorionById(1);   
        
        DirectDamageAbilityEffect bite = new DirectDamageAbilityEffect();
        BattleAbilityBase biteBase = new BattleAbilityBase.Builder()
            .setName("Bite")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        bite.decorate(biteBase); 
        teamOneExorionOne.learnAbilityOnSlotOne(bite);
        
        DirectDamageAbilityEffect ram = new DirectDamageAbilityEffect();
        BattleAbilityBase ramBase = new BattleAbilityBase.Builder()
            .setName("Ram")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        ram.decorate(ramBase); 
        teamTwoExorionOne.learnAbilityOnSlotOne(ram);
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
        
        TeamIdentifiers inputIsAwaitedFor = testCandidate.getTeamForWhichInputIsAwaited();
        assertThat(inputIsAwaitedFor, is(equalTo(TeamIdentifiers.BOTH_TEAMS)));
    }
    
    @Test
    public void testSimulatedBattleWithSlotOneAttacks_teamsPerformOffensiveAbilitiesInSuccession_firstTeamFaintsFirst() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = createExorionWithBite();
        IndividualExorion teamTwoExorionOne = createExorionWithRam();
        
        IndividualExorion referenceExorionOne = createExorionWithBite();
        IndividualExorion referenceExorionTwo = createExorionWithRam();
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();        
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        
        referenceExorionOne.useSlotOneAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotOneAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints())));
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        assertThat(testCandidate.hasBattleEnded(), is(false));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.NONE));
        
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE); 
        assertThat(testCandidate.hasBattleEnded(), is(true));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.TEAM_TWO));
        
        referenceExorionOne.useSlotOneAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotOneAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints()))); 
    }
    
    @Test
    public void testSimulatedBattleWithSlotTwoAttacks_teamsPerformOffensiveAbilitiesInSuccession_firstTeamFaintsFirst() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = createExorionWithBite();
        IndividualExorion teamTwoExorionOne = createExorionWithRam();
        
        IndividualExorion referenceExorionOne = createExorionWithBite();
        IndividualExorion referenceExorionTwo = createExorionWithRam();
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();        
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_TWO);
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_TWO);
        
        referenceExorionOne.useSlotTwoAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotTwoAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints())));
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_TWO);
        assertThat(testCandidate.hasBattleEnded(), is(false));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.NONE));
        
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_TWO); 
        assertThat(testCandidate.hasBattleEnded(), is(true));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.TEAM_TWO));
        
        referenceExorionOne.useSlotTwoAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotTwoAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints()))); 
    }   
    
    @Test
    public void testSimulatedBattleWithSlotThreeAttacks_teamsPerformOffensiveAbilitiesInSuccession_firstTeamFaintsFirst() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = createExorionWithBite();
        IndividualExorion teamTwoExorionOne = createExorionWithRam();
        
        IndividualExorion referenceExorionOne = createExorionWithBite();
        IndividualExorion referenceExorionTwo = createExorionWithRam();
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();        
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_THREE);
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_THREE);
        
        referenceExorionOne.useSlotThreeAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotThreeAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints())));
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_THREE);
        assertThat(testCandidate.hasBattleEnded(), is(false));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.NONE));
        
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_THREE); 
        assertThat(testCandidate.hasBattleEnded(), is(true));
        assertThat(testCandidate.getWinningTeam(), is(TeamIdentifiers.TEAM_TWO));
        
        referenceExorionOne.useSlotThreeAbility(referenceExorionTwo);
        referenceExorionTwo.useSlotThreeAbility(referenceExorionOne);
        
        assertThat(teamOneExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionOne.getBaseStats().getCurrentHealthPoints())));
        assertThat(teamTwoExorionOne.getBaseStats().getCurrentHealthPoints(), is(equalTo(referenceExorionTwo.getBaseStats().getCurrentHealthPoints()))); 
    }    
    
    @Test(expected = NoBattleInProgressException.class)
    public void testUseAbility_callAfterBattleHasEnded_throwsException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = createExorionWithBite();
        IndividualExorion teamTwoExorionOne = createExorionWithRam();
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle(); 
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        testCandidate.useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
    }
    
    @Test(expected = NoBattleInProgressException.class)
    public void testUseAbility_battleHasNotStarted_throwsException() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        ExorionBattleTeam teamTwo = new ExorionBattleTeam(); 
        
        IndividualExorion teamOneExorionOne = createExorionWithBite();
        IndividualExorion teamTwoExorionOne = createExorionWithRam();
        
        teamOne.addExorion(teamOneExorionOne);
        teamTwo.addExorion(teamTwoExorionOne);
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);   
        
        testCandidate.useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers.NORMAL_ABILITY_ONE);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private IndividualExorion createExorionWithBite() throws NotAllPropertiesAreSetException {
        IndividualExorion result = TestExorions.findExorionById(0);
        
        DirectDamageAbilityEffect bite = new DirectDamageAbilityEffect();
        BattleAbilityBase biteBase = new BattleAbilityBase.Builder()
            .setName("Bite")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        bite.decorate(biteBase); 
        result.learnAbilityOnSlotOne(bite);
        result.learnAbilityOnSlotTwo(bite);
        result.learnAbilityOnSlotThree(bite);

        return result;
    } 
    
    private IndividualExorion createExorionWithRam() throws NotAllPropertiesAreSetException {
        IndividualExorion result = TestExorions.findExorionById(1); 
        
        DirectDamageAbilityEffect ram = new DirectDamageAbilityEffect();
        BattleAbilityBase ramBase = new BattleAbilityBase.Builder()
            .setName("Ram")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        ram.decorate(ramBase); 
        result.learnAbilityOnSlotOne(ram);
        result.learnAbilityOnSlotTwo(ram);
        result.learnAbilityOnSlotThree(ram);
        
        return result;
    }
    
    //</editor-fold>
}