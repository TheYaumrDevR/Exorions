package de.ethasia.exorions.usecases.crosslayer;

public class InformationForMapsCouldNotBeLoadedException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BASIC_MESSAGE = "Could not start the game because information for maps could not be loaded.";
    
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
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public InformationForMapsCouldNotBeLoadedException(String detailedReason, String stackTrace) {
        super(BASIC_MESSAGE);
        errorCause = detailedReason;       
        this.stackTrace = stackTrace;
    }
    
    //</editor-fold>
}