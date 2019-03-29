package de.ethasia.exorions.core;

public class OutOfLevelBoundsException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OutOfLevelBoundsException() {
        super("Cannot increase an Exorion level over the maximum.");
    }
    
    //</editor-fold>
}