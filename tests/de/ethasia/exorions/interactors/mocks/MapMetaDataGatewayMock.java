package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.MapMetaDataGateway;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;

public class MapMetaDataGatewayMock implements MapMetaDataGateway {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static RuntimeException nextExceptionToThrow;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setNextExceptionToThrow(RuntimeException value) {
        nextExceptionToThrow = value;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapMetaDataGateway Overrides">
    
    @Override
    public MapMetaData tryToRetrieveMetaDataForNewGameMap() {
        if (null != nextExceptionToThrow) {
            RuntimeException exceptionToThrow = nextExceptionToThrow;
            nextExceptionToThrow = null;
            throw exceptionToThrow;
        }
        
        return new MapMetaData("", "", "");
    }    
    
    //</editor-fold>
}