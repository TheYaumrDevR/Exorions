package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.ioadapters.gateways.MapDefinitionsGatewayImpl;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class RealGatewaysFactory extends GatewaysFactory {

    //<editor-fold defaultstate="collapsed" desc="GatewaysFactory Overrides">
    
    @Override
    public MapDefinitionsGateway createMapMetaDataGateway() {
        return new MapDefinitionsGatewayImpl();
    }    
    
    //</editor-fold>    
}