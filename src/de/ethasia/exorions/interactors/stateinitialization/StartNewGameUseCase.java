package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.interactors.crosslayer.DebugWarningLogPresenter;
import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;
import de.ethasia.exorions.interactors.crosslayer.PlayerAvatarMovementPresenter;

public class StartNewGameUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final OverworldStatePresenter overworldStatePresenter;
    private final PlayerAvatarMovementPresenter playerMovementPresenter;
    private final FatalErrorPresenter fatalErrorPresenter;
    private final DebugWarningLogPresenter debugLogPresenter;
    private final MapDefinitionsGateway mapMetaDataGateway;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StartNewGameUseCase() {
        overworldStatePresenter = PresentersFactory.getInstance().createOverworldStatePresenter();
        playerMovementPresenter = PresentersFactory.getInstance().createPlayerAvatarMovementPresenter();
        fatalErrorPresenter = PresentersFactory.getInstance().createFatalErrorPresenter();
        mapMetaDataGateway = GatewaysFactory.getInstance().createMapMetaDataGateway();
        debugLogPresenter = PresentersFactory.getInstance().getDebugWarningLogPresenterSingletonInstance();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void startNewGame() {
        try {
            MapMetaData startingMapMetaData = mapMetaDataGateway.tryToRetrieveMetaDataForNewGameMap();
            InteriorMap map = createStartingMap(startingMapMetaData);
            
            overworldStatePresenter.presentOverworldWithMapFromMetaData(startingMapMetaData);  
            placePlayerOnMapAfterReadingTheStartingPosition(map, startingMapMetaData);
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

        readAndSetDimensionsForStartingMap(startingMapMetaData, mapBuilder);
        readAndSetTilesForStartingMap(startingMapMetaData, mapBuilder);
        
        return mapBuilder.build();
    }
    
    private void readAndSetDimensionsForStartingMap(MapMetaData startingMapMetaData, MapBuilder startingMapBuilder) {
        int mapDimensionX = mapMetaDataGateway.getMapDimensionX(startingMapMetaData.getLogicFilePath());
        int mapDimensionZ = mapMetaDataGateway.getMapDimensionZ(startingMapMetaData.getLogicFilePath());  
        
        startingMapBuilder.withDimensions((short)mapDimensionX, (short)mapDimensionZ);        
    }
    
    private void readAndSetTilesForStartingMap(MapMetaData startingMapMetaData, MapBuilder startingMapBuilder) {
        DefinitionsForUndistinguishableMapTiles floorTileDefinitions = null;
        DefinitionsForUndistinguishableMapTiles collisionTileDefinitions = null;

        try {
            floorTileDefinitions = mapMetaDataGateway.findFloorTileDefinitions(startingMapMetaData.getLogicFilePath());
            collisionTileDefinitions = mapMetaDataGateway.findCollisionTileDefinitions(startingMapMetaData.getLogicFilePath());
        } catch (MapDataCouldNotBeLoadedException ex) {
            debugLogPresenter.addLogEntry("Some tile definitions from " + startingMapMetaData.getLogicFilePath() + " could not be loaded due to XML schema malformation.");
            
            if (null == floorTileDefinitions) {
                floorTileDefinitions = new DefinitionsForUndistinguishableMapTiles();
            }
            
            if (null == collisionTileDefinitions) {
                collisionTileDefinitions = new DefinitionsForUndistinguishableMapTiles();
            }            
        }
        
        startingMapBuilder.withFloorDefinitions(floorTileDefinitions);
        startingMapBuilder.withCollisionDefinitions(collisionTileDefinitions);
    }
    
    private void placePlayerOnMapAfterReadingTheStartingPosition(InteriorMap map, MapMetaData startingMapMetaData) {
        int playerPositionX = 0;
        int playerPositionY = 0;
        int playerPositionZ = 0;
        
        try {
            playerPositionX = mapMetaDataGateway.getInitialPlayerPositionX(startingMapMetaData.getLogicFilePath());
            playerPositionY = mapMetaDataGateway.getInitialPlayerPositionY(startingMapMetaData.getLogicFilePath());
            playerPositionZ = mapMetaDataGateway.getInitialPlayerPositionZ(startingMapMetaData.getLogicFilePath()); 
        } catch (MapDataCouldNotBeLoadedException ex) {
            debugLogPresenter.addLogEntry("Some initial position coordinate for the player was not found in map definition " + startingMapMetaData.getLogicFilePath() + " due to an XML error or the coordinate is missing.");
        }
        
        Player.getInstance().placeOnMapWithPosition(map, (short)playerPositionX, (short)playerPositionY, (short)playerPositionZ);
        playerMovementPresenter.teleportTo((short)playerPositionX, (short)playerPositionY, (short)playerPositionZ);
    }
    
    //</editor-fold>
}