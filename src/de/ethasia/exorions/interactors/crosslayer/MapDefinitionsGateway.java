package de.ethasia.exorions.interactors.crosslayer;

import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;

public interface MapDefinitionsGateway {
    
    public MapMetaData tryToRetrieveMetaDataForNewGameMap();
    public DefinitionsForUndistinguishableMapTiles findFloorTileDefinitions(String pathToMapDefinition);
    public DefinitionsForUndistinguishableMapTiles findCollisionTileDefinitions(String pathToMapDefinition);
}