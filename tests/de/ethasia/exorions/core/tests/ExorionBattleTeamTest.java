package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExorionBattleTeamTest {
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddExorion_slotsAreEmpty_exorionIsAddedOnFirstSlot() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();
        
        IndividualExorion addedExorion = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        testCandidate.addExorion(addedExorion);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionOnSlot(0);
        assertThat(firstExorionInTeam, is(equalTo(addedExorion)));
    }
    
    @Test
    public void testAddExorion_slotsAreEmptyTwoAreAdded_exorionIsAddedOnFirstAndSecondSlot() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();;
        
        IndividualExorion addedExorionOne = new IndividualExorion.Builder().setSpecies(species).build();
        IndividualExorion addedExorionTwo = new IndividualExorion.Builder().setSpecies(species).build();
        testCandidate.addExorion(addedExorionOne);
        testCandidate.addExorion(addedExorionTwo);
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionOnSlot(0);
        IndividualExorion secondExorionInTeam = testCandidate.getExorionOnSlot(1);
        assertThat(firstExorionInTeam, is(equalTo(addedExorionOne)));
        assertThat(secondExorionInTeam, is(equalTo(addedExorionTwo)));
    } 
    
    @Test
    public void testAddExorion_slotsAreEmptyFiveAreAdded_exorionIsAddedOnFirstFiveSlots() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();;
        
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
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionOnSlot(0);
        IndividualExorion secondExorionInTeam = testCandidate.getExorionOnSlot(1);
        IndividualExorion thirdExorionInTeam = testCandidate.getExorionOnSlot(2);
        IndividualExorion fourthExorionInTeam = testCandidate.getExorionOnSlot(3);
        IndividualExorion fifthExorionInTeam = testCandidate.getExorionOnSlot(4);
        assertThat(firstExorionInTeam, is(equalTo(addedExorionOne)));
        assertThat(secondExorionInTeam, is(equalTo(addedExorionTwo)));
        assertThat(thirdExorionInTeam, is(equalTo(addedExorionThree)));
        assertThat(fourthExorionInTeam, is(equalTo(addedExorionFour)));
        assertThat(fifthExorionInTeam, is(equalTo(addedExorionFive)));
    }   
    
    @Test(expected = BattleTeamIsFullException.class)
    public void testAddExorion_slotsAreEmptySixAreAdded_throwsTeamFullException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        ExorionBattleTeam testCandidate = new ExorionBattleTeam();
        
        ExorionSpecies species = createExorionSpecies();;
        
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
        
        IndividualExorion firstExorionInTeam = testCandidate.getExorionOnSlot(0);   
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
        IndividualExorion exorionAtSlotFour = testCandidate.getExorionOnSlot(4); 
          
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