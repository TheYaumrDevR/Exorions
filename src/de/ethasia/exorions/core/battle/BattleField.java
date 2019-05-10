package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.IndividualExorion;

public class BattleField {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    ExorionBattleTeam teamOne;
    ExorionBattleTeam teamTwo;
    
    private boolean battleIsInProgress;
    
    ExorionBattleTeam teamToMoveNext;
    TeamIdentifiers teamWhichWon;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BattleField() {
        teamWhichWon = TeamIdentifiers.NONE;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setTeamOne(ExorionBattleTeam value) {
        teamOne = value;
    }    
    
    public void setTeamTwo(ExorionBattleTeam value) {
        teamTwo = value;
    } 
    
    public IndividualExorion getCurrentExorionOfFirstTeam() {
        return teamOne.getFirstExorion();
    }
    
    public IndividualExorion getCurrentExorionOfSecondTeam() {
        return teamTwo.getFirstExorion();
    }    
    
    public void startBattle() {
        if (!battleCanStart()) {
            throw new BattleCannotStartBecauseRequirementsAreNotMetException();
        }
        
        battleIsInProgress = true;
        determineWhichTeamMovesNext();
    }  
    
    public boolean teamOneHasToMove() {
        return teamToMoveNext == teamOne;
    }
    
    public boolean hasBattleEnded() {
        return teamOne.allExorionAreFainted() || teamTwo.allExorionAreFainted();
    }
    
    public TeamIdentifiers getWinningTeam() {
        return teamWhichWon;
    }
    
    public TeamIdentifiers getTeamForWhichInputIsAwaited() {
        return TeamIdentifiers.BOTH_TEAMS;
    }
    
    public void useAbilityOfCurrentTeamOneExorion(BattleFieldAbilityIdentifiers abilityIdentifier) {
        if (!battleIsInProgress) {
            throw new NoBattleInProgressException();
        }
        
        switch (abilityIdentifier) {
            case NORMAL_ABILITY_ONE:
                teamOne.getFirstExorion().useSlotOneAbility(teamTwo.getFirstExorion());
                break;
        }
        
        if (hasBattleEnded()) {
            battleIsInProgress = false;
            teamWhichWon = TeamIdentifiers.TEAM_ONE;
        }
    }
    
    public void useAbilityOfCurrentTeamTwoExorion(BattleFieldAbilityIdentifiers abilityIdentifier) {
        if (!battleIsInProgress) {
            throw new NoBattleInProgressException();
        }
        
        switch (abilityIdentifier) {
            case NORMAL_ABILITY_ONE:
                teamTwo.getFirstExorion().useSlotOneAbility(teamOne.getFirstExorion());
                break;
        }     
        
        if (hasBattleEnded()) {
            battleIsInProgress = false;
            teamWhichWon = TeamIdentifiers.TEAM_TWO;
        }
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private boolean battleCanStart() {
        return null != teamOne 
            && null != teamTwo 
            && teamOne != teamTwo 
            && !teamOne.isEmpty()
            && !teamTwo.isEmpty()
            && teamOne.allExorionHaveAbilities()
            && teamTwo.allExorionHaveAbilities();
    }
    
    private void determineWhichTeamMovesNext() {
        IndividualExorion firstExorionOnTeamOne = teamOne.getFirstExorion();
        IndividualExorion firstExorionOnTeamTwo = teamTwo.getFirstExorion();
        
        if (firstExorionOnTeamOne.getBaseStats().getSwiftness() == firstExorionOnTeamTwo.getBaseStats().getSwiftness()) {
            teamToMoveNext = teamOne;
        } else if (firstExorionOnTeamOne.getBaseStats().getSwiftness() > firstExorionOnTeamTwo.getBaseStats().getSwiftness()) {
            teamToMoveNext = teamOne;
        } else {
            teamToMoveNext = teamTwo;
        }
    }
    
    //</editor-fold>
}