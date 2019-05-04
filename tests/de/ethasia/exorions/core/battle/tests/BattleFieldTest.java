package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.BattleCannotStartBecauseRequirementsAreNotMetException;
import de.ethasia.exorions.core.battle.BattleField;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.mocks.TestExorions;

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
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
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
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
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
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
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
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
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
}