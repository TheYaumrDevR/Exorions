package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.MapMetaDataGateway;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;

public class MockGatewaysFactory extends GatewaysFactory {

    //<editor-fold defaultstate="collapsed" desc="GatewaysFactory Overrides">
    
    @Override
    public MapMetaDataGateway createMapMetaDataGateway() {
        return new MapMetaDataGatewayMock();
    }    
    
    //</editor-fold>    
}