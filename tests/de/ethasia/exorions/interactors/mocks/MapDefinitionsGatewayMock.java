package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class MapDefinitionsGatewayMock implements MapDefinitionsGateway {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static RuntimeException nextExceptionToThrow;
    private static String lastPassedFindFloorTileDefinitionsPath;
    private static String lastPassedFindCollisionTileDefinitionsPath;
    private static String lastPassedGetMapDimensionXPath;
    private static String lastPassedGetMapDimensionZPath;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void resetMocks() {
        lastPassedFindFloorTileDefinitionsPath = "";
        lastPassedFindCollisionTileDefinitionsPath = "";
        lastPassedGetMapDimensionXPath = "";
        lastPassedGetMapDimensionZPath = "";
    }
    
    public static void setNextExceptionToThrow(RuntimeException value) {
        nextExceptionToThrow = value;
    }
    
    public static String getLastPassedFindFloorTileDefinitionsPath() {
        return lastPassedFindFloorTileDefinitionsPath;
    }
    
    public static String getLastPassedFindCollisionTileDefinitionsPath() {
        return lastPassedFindCollisionTileDefinitionsPath;
    }
    
    public static String getLastPassedGetMapDimensionXPath() {
        return lastPassedGetMapDimensionXPath;
    }
    
    public static String getLastPassedGetMapDimensionZPath() {
        return lastPassedGetMapDimensionZPath;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapDefinitionsGateway Overrides">
    
    @Override
    public int getMapDimensionX(String pathToMapDefinition) {
        throwExceptionIfSet();
        lastPassedGetMapDimensionXPath = pathToMapDefinition;
        return 0;
    }

    @Override
    public int getMapDimensionZ(String pathToMapDefinition) {
        throwExceptionIfSet();
        lastPassedGetMapDimensionZPath = pathToMapDefinition;
        return 0;
    }    
    
    @Override
    public MapMetaData tryToRetrieveMetaDataForNewGameMap() {
        throwExceptionIfSet();
        return new MapMetaData("Suriver City NE", "Maps/Exterior/SuriverCityNE.xml", "Scenes/Exterior/SuriverCityNE.j3o");
    }    
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findFloorTileDefinitions(String pathToMapDefinition) {
        throwExceptionIfSet();
        lastPassedFindFloorTileDefinitionsPath = pathToMapDefinition;
        return new DefinitionsForUndistinguishableMapTiles();
    }  
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findCollisionTileDefinitions(String pathToMapDefinition) {
        throwExceptionIfSet();
        lastPassedFindCollisionTileDefinitionsPath = pathToMapDefinition;
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