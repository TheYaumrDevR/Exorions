package de.ethasia.exorions.interactors.crosslayer;

import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;

public interface MapMetaDataGateway {
    
    public MapMetaData tryToRetrieveMetaDataForNewGameMap();
}