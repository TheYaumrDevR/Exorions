package de.ethasia.exorions.core.maps;

public class NoMapToMoveOnException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public final static String MESSAGE = "This object must first be placed onto a Map before it can move around.";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NoMapToMoveOnException() {
        super(MESSAGE);
    }
    
    //</editor-fold>
}