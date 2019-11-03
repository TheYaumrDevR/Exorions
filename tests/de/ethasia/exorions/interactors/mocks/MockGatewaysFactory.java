package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class MockGatewaysFactory extends GatewaysFactory {

    //<editor-fold defaultstate="collapsed" desc="GatewaysFactory Overrides">
    
    @Override
    public MapDefinitionsGateway createMapMetaDataGateway() {
        return new MapDefinitionsGatewayMock();
    }    
    
    //</editor-fold>    
}