package de.ethasia.exorions.usecases.crosslayer;

public class MapLogicCouldNotBeLoadedException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BASIC_MESSAGE = "Could not load the logic for a map.";
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public String getErrorMessage() {
        return BASIC_MESSAGE;
    }
    
    private final String errorCause;
    public String getErrorCause() {
        return errorCause;
    }
    
    private final String stackTrace;
    public String getStackTraceString() {
        return stackTrace;
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MapLogicCouldNotBeLoadedException(String detailedReason, String stackTrace) {
        super(BASIC_MESSAGE);
        errorCause = detailedReason;       
        this.stackTrace = stackTrace;
    }
    
    //</editor-fold>
}