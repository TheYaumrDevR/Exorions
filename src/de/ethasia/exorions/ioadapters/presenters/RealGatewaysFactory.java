package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.crosslayer.MapMetaDataGateway;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.ioadapters.gateways.MapMetaDataGatewayImpl;

public class RealGatewaysFactory extends GatewaysFactory {

    //<editor-fold defaultstate="collapsed" desc="GatewaysFactory Overrides">
    
    @Override
    public MapMetaDataGateway createMapMetaDataGateway() {
        return new MapMetaDataGatewayImpl();
    }    
    
    //</editor-fold>    
}