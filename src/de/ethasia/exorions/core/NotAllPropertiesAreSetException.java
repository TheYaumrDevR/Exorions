package de.ethasia.exorions.core;

public class NotAllPropertiesAreSetException extends Exception {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String EXCEPTION_MESSAGE = "Not all necessary properties were set when creating this object.";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NotAllPropertiesAreSetException() {
        super(EXCEPTION_MESSAGE);
    }
    
    //</editor-fold>
}