package de.ethasia.exorions.core;

public class BattleTeamIsFullException extends Exception {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String EXCEPTION_MESSAGE = "Cannot add the Exorion to the team, because the team is full.";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BattleTeamIsFullException() {
        super(EXCEPTION_MESSAGE);
    }
    
    //</editor-fold>
}