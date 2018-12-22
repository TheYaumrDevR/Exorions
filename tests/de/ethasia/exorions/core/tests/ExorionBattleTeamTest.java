package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.ExorionSpecies;
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
        
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
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
        
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
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
        
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
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
        
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
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
}