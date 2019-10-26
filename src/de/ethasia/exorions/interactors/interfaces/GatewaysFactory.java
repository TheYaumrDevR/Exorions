package de.ethasia.exorions.interactors.interfaces;

import de.ethasia.exorions.interactors.crosslayer.MapMetaDataGateway;

public abstract class GatewaysFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static GatewaysFactory instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void setInstance(GatewaysFactory value) {
        instance = value;
    }
    
    public static GatewaysFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract MapMetaDataGateway createMapMetaDataGateway();    
    
    //</editor-fold>
}