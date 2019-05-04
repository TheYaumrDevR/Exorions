package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.ExorionBattleTeam;
import de.ethasia.exorions.core.IndividualExorion;

public class BattleField {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    ExorionBattleTeam teamOne;
    ExorionBattleTeam teamTwo;
    
    ExorionBattleTeam teamToMoveNext;
    
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
        
        determineWhichTeamMovesNext();
    }  
    
    public boolean teamOneHasToMove() {
        return teamToMoveNext == teamOne;
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