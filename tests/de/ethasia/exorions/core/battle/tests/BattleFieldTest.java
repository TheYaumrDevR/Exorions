package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.BattleTeamIsFullException;
import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException;
import de.ethasia.exorions.core.battle.BattleField;
import de.ethasia.exorions.core.mocks.TestExorions;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleFieldTest {
    
    @Test(expected = BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException.class)
    public void testStartBattle_noTeamsAreSet_throwsException() {
        BattleField testCandidate = new BattleField();
        testCandidate.startBattle();
    }
    
    @Test(expected = BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException.class)
    public void testStartBattle_bothTeamsAreTheSame_throwsException() throws BattleTeamIsFullException, NotAllPropertiesAreSetException {
        BattleField testCandidate = new BattleField();
        ExorionBattleTeam teamOne = new ExorionBattleTeam();
        
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));      
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamOne);
        
        testCandidate.startBattle();
    }   
    
    @Test(expected = BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException.class)
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
        
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));
        teamOne.addExorion(TestExorions.findExorionById(0));
        
        teamTwo.addExorion(TestExorions.findExorionById(0));
        teamTwo.addExorion(TestExorions.findExorionById(0));
        teamTwo.addExorion(TestExorions.findExorionById(0));
        
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
        
        teamOne.addExorion(TestExorions.findExorionById(1));
        
        teamTwo.addExorion(TestExorions.findExorionById(0));
        
        testCandidate.setTeamOne(teamOne);
        testCandidate.setTeamTwo(teamTwo);
        
        testCandidate.startBattle();
        
        assertThat(testCandidate.teamOneHasToMove(), is(equalTo(false)));
    }
}