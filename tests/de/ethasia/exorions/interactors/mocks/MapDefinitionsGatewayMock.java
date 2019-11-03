package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class MapDefinitionsGatewayMock implements MapDefinitionsGateway {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static RuntimeException nextExceptionToThrow;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setNextExceptionToThrow(RuntimeException value) {
        nextExceptionToThrow = value;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapDefinitionsGateway Overrides">
    
    @Override
    public MapMetaData tryToRetrieveMetaDataForNewGameMap() {
        throwExceptionIfSet();
        return new MapMetaData("", "", "");
    }    
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findFloorTileDefinitions(String pathToMapDefinition) {
        throwExceptionIfSet();
        return new DefinitionsForUndistinguishableMapTiles();
    }  
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findCollisionTileDefinitions(String pathToMapDefinition) {
        throwExceptionIfSet();
        return new DefinitionsForUndistinguishableMapTiles();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void throwExceptionIfSet() {
        if (null != nextExceptionToThrow) {
            RuntimeException exceptionToThrow = nextExceptionToThrow;
            nextExceptionToThrow = null;
            throw exceptionToThrow;
        }         
    }
    
    //</editor-fold>
}