package de.ethasia.exorions.usecases.crosslayer;

public class InformationForMapsCouldNotBeLoadedException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BASIC_MESSAGE = "Could not start the game because information for maps could not be loaded.";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public InformationForMapsCouldNotBeLoadedException(String detailedReason, String stackTrace) {
        super(BASIC_MESSAGE + "\n" + detailedReason + "\n\nStacktrace:\n" + stackTrace);        
    }
    
    //</editor-fold>
}