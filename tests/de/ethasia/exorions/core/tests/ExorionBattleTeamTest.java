package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.mocks.MockGenome;
import de.ethasia.exorions.core.mocks.TestExorions;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityRequiredLevelTables;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExorionBattleTeamTest {

    @Test
    public void testAddExorion_slotsAreEmpty_exorionIsAddedOnFirstSlot() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        testCandidate.addExorion(addedExorion);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionInSlot(0);
        assertThat(firstExorionInTeam, is(equalTo(addedExorion)));
    }
    
    @Test
    public void testAddExorion_slotsAreEmptyTwoAreAdded_exorionIsAddedOnFirstAndSecondSlot() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.addExorion(addedExorionOne);
        testCandidate.addExorion(addedExorionTwo);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionInSlot(0);
        IndividualExorion secondExorionInTeam = testCandidate.getExorionInSlot(1);
        assertThat(firstExorionInTeam, is(equalTo(addedExorionOne)));
        assertThat(secondExorionInTeam, is(equalTo(addedExorionTwo)));
    } 
    
    @Test
    public void testAddExorion_slotsAreEmptyFiveAreAdded_exorionIsAddedOnFirstFiveSlots() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionThree = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionFour = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionFive = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.addExorion(addedExorionOne);
        testCandidate.addExorion(addedExorionTwo);
        testCandidate.addExorion(addedExorionThree);
        testCandidate.addExorion(addedExorionFour);
        testCandidate.addExorion(addedExorionFive);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionInSlot(0);
        IndividualExorion secondExorionInTeam = testCandidate.getExorionInSlot(1);
        IndividualExorion thirdExorionInTeam = testCandidate.getExorionInSlot(2);
        IndividualExorion fourthExorionInTeam = testCandidate.getExorionInSlot(3);
        IndividualExorion fifthExorionInTeam = testCandidate.getExorionInSlot(4);
        assertThat(firstExorionInTeam, is(equalTo(addedExorionOne)));
        assertThat(secondExorionInTeam, is(equalTo(addedExorionTwo)));
        assertThat(thirdExorionInTeam, is(equalTo(addedExorionThree)));
        assertThat(fourthExorionInTeam, is(equalTo(addedExorionFour)));
        assertThat(fifthExorionInTeam, is(equalTo(addedExorionFive)));
    }   
    
    @Test(expected = BattleTeamIsFullException.class)
    public void testAddExorion_slotsAreEmptySixAreAdded_throwsTeamFullException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionThree = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionFour = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionFive = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionSix = new IndividualExorion.Builder().setSpecies(species).build();
        
        testCandidate.addExorion(addedExorionOne);
        testCandidate.addExorion(addedExorionTwo);
        testCandidate.addExorion(addedExorionThree);
        testCandidate.addExorion(addedExorionFour);
        testCandidate.addExorion(addedExorionFive);
        testCandidate.addExorion(addedExorionSix);
    } 
    
    @Test
    public void testReplaceExorionAtWith_slotIsValid_exorionIsAddedToSlotAndOldOneIsRemoved() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.addExorion(addedExorionOne);
        testCandidate.replaceExorionAtWith(0, addedExorionTwo);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionInSlot(0);   
        assertThat(firstExorionInTeam, is(equalTo(addedExorionTwo)));
    }
    
    @Test
    public void testReplaceExorionAtWith_slotIsValid_exorionIsAddedToSlotAndOldOneIsReturned() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.addExorion(addedExorionOne);
        IndividualExorion removedExorion = testCandidate.replaceExorionAtWith(0, addedExorionTwo);
          
        assertThat(removedExorion, is(equalTo(addedExorionOne)));
    }    
    
    @Test
    public void testReplaceExorionAtWith_slotIsValidAndWasNotUsedBefore_noExorionIsRemoved() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion removedExorion = testCandidate.replaceExorionAtWith(4, addedExorion);
        IndividualExorion exorionAtSlotFour = testCandidate.getExorionInSlot(4); 
          
        assertThat(removedExorion, is(nullValue()));
        assertThat(exorionAtSlotFour, is(equalTo(addedExorion)));
    }    
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReplaceExorionAtWith_slotIndexInvalid_throwsException() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.replaceExorionAtWith(6, addedExorion);
    }   
    
    @Test
    public void testAllExorionAreFainted_twoUnfaintedExorionPresent_returnsFalse() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorionBaseStats individualBaseStatsOne = new IndividualExorionBaseStats.Builder()
            .build();
        IndividualExorionBaseStats individualBaseStatsTwo = new IndividualExorionBaseStats.Builder()
            .build();        
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStatsOne)
            .setGenome(new MockGenome(0))
            .build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStatsTwo)
            .setGenome(new MockGenome(0))
            .build();
        
        testCandidate.addExorion(addedExorionOne);
        testCandidate.addExorion(addedExorionTwo);
        
        addedExorionOne.levelUpBy(1);
        addedExorionTwo.levelUpBy(1);
        
        assertThat(testCandidate.allExorionAreFainted(), is(equalTo(false)));
    } 
    
    @Test
    public void testAllExorionAreFainted_twoFaintedExorionArePresent_returnsTrue() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorionBaseStats individualBaseStatsOne = new IndividualExorionBaseStats.Builder()
            .build();
        IndividualExorionBaseStats individualBaseStatsTwo = new IndividualExorionBaseStats.Builder()
            .build();        
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStatsOne)
            .setGenome(new MockGenome(0))
            .build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStatsTwo)
            .setGenome(new MockGenome(0))
            .build();
        
        testCandidate.addExorion(addedExorionOne);
        testCandidate.replaceExorionAtWith(2, addedExorionTwo);
        
        addedExorionOne.levelUpBy(1);
        addedExorionTwo.levelUpBy(1);
        
        addedExorionOne.takeDamage(100);
        addedExorionTwo.takeDamage(200);
        
        assertThat(testCandidate.allExorionAreFainted(), is(equalTo(true)));
    } 

    @Test
    public void testGetFirstExorionInTeam_firstExorionIsOnSlotThree_exorionIsReturned() throws NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.replaceExorionAtWith(4, addedExorion); 
          
        assertThat(testCandidate.getFirstExorion(), is(equalTo(addedExorion)));
    } 
    
    @Test
    public void testIsEmpty_noExorionAreSet_returnsTrue() {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam(); 
          
        assertThat(testCandidate.isEmpty(), is(true));
    } 
    
    @Test
    public void testIsEmpty_exorionIsSet_returnsFalse() throws NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam(); 
          
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.replaceExorionAtWith(3, addedExorion);         
        
        assertThat(testCandidate.isEmpty(), is(false));
    }     
    
    @Test
    public void testAllExorionHaveAbilities_teamMembersHaveNoAbilities_returnsFalse() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        IndividualExorion exorionOne = TestExorions.findExorionById(0);
        IndividualExorion exorionTwo = TestExorions.findExorionById(1);
        
        testCandidate.addExorion(exorionOne);
        testCandidate.addExorion(exorionTwo);
        
        assertThat(testCandidate.allExorionHaveAbilities(), is(equalTo(false)));
    }
    
    @Test
    public void testAllExorionHaveAbilities_teamMembersHaveAbilities_returnsTrue() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        IndividualExorion exorionOne = TestExorions.findExorionById(0);
        IndividualExorion exorionTwo = TestExorions.findExorionById(1);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);
        
        BattleAbilityBase abilityTwo = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);    
        
        exorionOne.learnAbilityOnSlotOne(ability);
        exorionTwo.learnAbilityOnSlotThree(abilityTwo);
        
        testCandidate.addExorion(exorionOne);
        testCandidate.addExorion(exorionTwo);
        
        assertThat(testCandidate.allExorionHaveAbilities(), is(equalTo(true)));
    }  
    
    @Test
    public void testAllExorionHaveAbilities_oneTeamMemberDoesNotHaveAbilities_returnsFalse() throws NotAllPropertiesAreSetException, BattleTeamIsFullException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        IndividualExorion exorionOne = TestExorions.findExorionById(0);
        IndividualExorion exorionTwo = TestExorions.findExorionById(1);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);  
        
        exorionOne.learnAbilityOnSlotOne(ability);
        
        testCandidate.replaceExorionAtWith(2, exorionOne);
        testCandidate.replaceExorionAtWith(4, exorionTwo);
        
        assertThat(testCandidate.allExorionHaveAbilities(), is(equalTo(false)));
    }     
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private ExorionSpecies createExorionSpecies() {
        ExorionSpeciesBaseStatsAtMaximumLevel baseStats = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(50)
            .setAttackBaseStat(49)
            .setDefenseBaseStat(48)
            .setSpecialAttackBaseStat(47)
            .setSpecialDefenseBaseStat(46)
            .setAccuracyBaseStat(45)
            .setEvasivenessBaseStat(44)
            .setCriticalHitFrequencyBaseStat(43)
            .setCriticalHitAvoidanceBaseStat(42)
            .setSwiftnessBaseStat(41)
            .build(); 
        
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(baseStats)
            .build();
        
        return species;
    }
    
    //</editor-fold>
}