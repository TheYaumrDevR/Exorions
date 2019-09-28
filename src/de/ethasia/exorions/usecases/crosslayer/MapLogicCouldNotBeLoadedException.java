package de.ethasia.exorions.usecases.crosslayer;

public class MapLogicCouldNotBeLoadedException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final String BASIC_MESSAGE = "Could not load the logic for a map.";
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MapLogicCouldNotBeLoadedException(String detailedReason, String stackTrace) {
        super(BASIC_MESSAGE + "\n" + detailedReason + "\n\nStacktrace:\n" + stackTrace);
    }
    
    //</editor-fold>
}