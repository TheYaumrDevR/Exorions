package de.ethasia.exorions.interactors.crosslayer;

import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;

public interface MapDefinitionsGateway {
    
    public int getMapDimensionX(String pathToMapDefinition);
    public int getMapDimensionZ(String pathToMapDefinition);
    public MapMetaData tryToRetrieveMetaDataForNewGameMap();
    public DefinitionsForUndistinguishableMapTiles findFloorTileDefinitions(String pathToMapDefinition);
    public DefinitionsForUndistinguishableMapTiles findCollisionTileDefinitions(String pathToMapDefinition);
    public int getInitialPlayerPositionX(String pathToMapDefinition);
    public int getInitialPlayerPositionY(String pathToMapDefinition);
    public int getInitialPlayerPositionZ(String pathToMapDefinition);    
}