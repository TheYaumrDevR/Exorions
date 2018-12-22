package de.ethasia.exorions.core;

import java.util.ArrayList;
import java.util.List;

public class ExorionBattleTeam {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int MAXIMUM_TEAM_SIZE = 5;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<IndividualExorion> teamMembers;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ExorionBattleTeam() {
        teamMembers = new ArrayList<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void addExorion(IndividualExorion exorion) throws BattleTeamIsFullException {
        throwExceptionIfTeamIsFull();
        teamMembers.add(exorion);
    }
    
    public IndividualExorion getExorionOnSlot(int slotIndex) {
        return teamMembers.get(slotIndex);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void throwExceptionIfTeamIsFull() throws BattleTeamIsFullException {
        if (teamMembers.size() >= MAXIMUM_TEAM_SIZE) {
            throw new BattleTeamIsFullException();
        }        
    }
    
    //</editor-fold>
}