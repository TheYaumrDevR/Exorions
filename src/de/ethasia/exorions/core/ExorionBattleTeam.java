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
        teamMembers = new ArrayList<>(MAXIMUM_TEAM_SIZE);
        for (int i = 0; i < MAXIMUM_TEAM_SIZE; i++) {
            teamMembers.add(i, null);
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void addExorion(IndividualExorion exorion) throws BattleTeamIsFullException {
        int nextFreeSlotIndex = getNextFreeSlotIndex();
        throwExceptionIfTeamIsFull(nextFreeSlotIndex);
        teamMembers.add(nextFreeSlotIndex, exorion);
    }
    
    public IndividualExorion replaceExorionAtWith(int slotIndex, IndividualExorion exorion) {
        if (slotIndex >= MAXIMUM_TEAM_SIZE) {
            throw new IndexOutOfBoundsException("Exorions can only be added on the first five slots of the team.");
        }
        
        IndividualExorion removed = teamMembers.remove(slotIndex);
        
        teamMembers.add(slotIndex, exorion);
        return removed;
    }
    
    public IndividualExorion getExorionOnSlot(int slotIndex) {
        return teamMembers.get(slotIndex);
    }
    
    public boolean allExorionAreFainted() {
        return teamMembers.stream().anyMatch(
            (teamMember) -> (teamMember != null && teamMember.isFainted())
        );
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private int getNextFreeSlotIndex() {
        for (int i = 0; i < MAXIMUM_TEAM_SIZE; i++) {
            if (teamMembers.get(i) == null) {
                return i;
            }
        }        
        
        return 5;
    }
    
    private void throwExceptionIfTeamIsFull(int nextFreeSlotIndex) throws BattleTeamIsFullException {
        if (nextFreeSlotIndex >= MAXIMUM_TEAM_SIZE) {
            throw new BattleTeamIsFullException();
        }        
    }
    
    //</editor-fold>
}