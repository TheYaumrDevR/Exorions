package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class StartNewGameUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final OverworldStatePresenter overworldStatePresenter;
    private final FatalErrorPresenter fatalErrorPresenter;
    private final MapDefinitionsGateway mapMetaDataGateway;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StartNewGameUseCase() {
        overworldStatePresenter = PresentersFactory.getInstance().createOverworldStatePresenter();
        fatalErrorPresenter = PresentersFactory.getInstance().createFatalErrorPresenter();
        mapMetaDataGateway = GatewaysFactory.getInstance().createMapMetaDataGateway();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void startNewGame() {
        try {
            MapMetaData startingMapMetaData = mapMetaDataGateway.tryToRetrieveMetaDataForNewGameMap();
            InteriorMap map = createStartingMap(startingMapMetaData);
            placePlayerOnMapAfterReadingTheStartingPosition(map, startingMapMetaData);
            
            overworldStatePresenter.presentOverworldWithMapFromMetaData(startingMapMetaData);  
        } catch (InformationForMapsCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        } catch (MapDataCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private InteriorMap createStartingMap(MapMetaData startingMapMetaData) {
        MapBuilder mapBuilder = new MapBuilder();
        
        int mapDimensionX = mapMetaDataGateway.getMapDimensionX(startingMapMetaData.getLogicFilePath());
        int mapDimensionZ = mapMetaDataGateway.getMapDimensionZ(startingMapMetaData.getLogicFilePath());  
        DefinitionsForUndistinguishableMapTiles floorTileDefinitions = mapMetaDataGateway.findFloorTileDefinitions(startingMapMetaData.getLogicFilePath());
        DefinitionsForUndistinguishableMapTiles collisionTileDefinitions = mapMetaDataGateway.findCollisionTileDefinitions(startingMapMetaData.getLogicFilePath());
        
        mapBuilder.withDimensions((short)mapDimensionX, (short)mapDimensionZ);
        mapBuilder.withFloorDefinitions(floorTileDefinitions);   
        mapBuilder.withCollisionDefinitions(collisionTileDefinitions);
        
        return mapBuilder.build();
    }
    
    private void placePlayerOnMapAfterReadingTheStartingPosition(InteriorMap map, MapMetaData startingMapMetaData) {
        int playerPositionX = 0;
        int playerPositionY = 0;
        int playerPositionZ = 0;
        
        try {
            playerPositionX = mapMetaDataGateway.getInitialPlayerPositionX(startingMapMetaData.getLogicFilePath());
            playerPositionY = mapMetaDataGateway.getInitialPlayerPositionY(startingMapMetaData.getLogicFilePath());
            playerPositionZ = mapMetaDataGateway.getInitialPlayerPositionZ(startingMapMetaData.getLogicFilePath()); 
        } catch (MapDataCouldNotBeLoadedException ex) {}
        
        Player.getInstance().placeOnMapWithPosition(map, (short)playerPositionX, (short)playerPositionY, (short)playerPositionZ);
    }
    
    //</editor-fold>
}