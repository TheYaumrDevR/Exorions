package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.fileaccess.Maps;
import de.ethasia.exorions.technical.engine.EngineMapData;
import de.ethasia.exorions.ui.gamestates.EvocriGameState;
import de.ethasia.exorions.ui.gamestates.OverworldGameState;
import de.ethasia.exorions.usecases.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.usecases.crosslayer.OverworldStatePresenter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class OverworldStatePresenterImpl implements OverworldStatePresenter {

    //<editor-fold defaultstate="collapsed" desc="OverworldStatePresenter Overrides">
    
    @Override
    public void presentOverworldWithNewGameMap() {
        Document mapList = Maps.readMapList();
        MapMetaData startingMapData = tryToFindMapDataOfStartingMap(mapList);
        
        Document mapLogic = Maps.readMapLogic(startingMapData.logicFilePath);
        EngineMapData.Builder mapDataForEngineBuilder = setupLogicalMapData(mapLogic);
        
        mapDataForEngineBuilder.setMapVisuals(Maps.readMapVisuals(startingMapData.visualFilePath));
        EngineMapData mapDataForEngine = mapDataForEngineBuilder.build();
        
        OverworldGameState overworldState = new OverworldGameState(mapDataForEngine);
        EvocriGameState.setGameState(overworldState);
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private MapMetaData tryToFindMapDataOfStartingMap(Document mapList) {
        String mapName = "";
        String logicFilePath = "";
        String visualFilePath = "";
        
        NodeList newGameMap = mapList.getElementsByTagName("newGameMap");
        if (newGameMap.getLength() > 0) {
            mapName = newGameMap.item(0).getAttributes().getNamedItem("name").getNodeValue();
            logicFilePath = newGameMap.item(0).getAttributes().getNamedItem("logic").getNodeValue();
            visualFilePath = newGameMap.item(0).getAttributes().getNamedItem("visuals").getNodeValue();
        } else {
            throw new MapDataCouldNotBeLoadedException("Information for the starting game map was not found. The maplist is corrupted.", "");
        }
        
        if (mapName.isEmpty() || logicFilePath.isEmpty() || visualFilePath.isEmpty()) {
            throw new MapDataCouldNotBeLoadedException("The name or the file paths for the starting game map are empty. Thus it cannot be loaded.", "");
        }
        
        return new MapMetaData(mapName, logicFilePath, visualFilePath);
    }
    
    private EngineMapData.Builder setupLogicalMapData(Document mapLogic) {
        EngineMapData.Builder result = new EngineMapData.Builder();
        
        NodeList floors = mapLogic.getElementsByTagName("floors");
        if (floors.getLength() > 0) {
            Element firstFloorsNode = (Element)floors.item(0);
            NodeList floorsNodeChildren = firstFloorsNode.getElementsByTagName("f");

            for (int i = 0; i < floorsNodeChildren.getLength(); i++) {
                int width = Integer.parseInt(floorsNodeChildren.item(i).getAttributes().getNamedItem("w").getNodeValue());
                int depth = Integer.parseInt(floorsNodeChildren.item(i).getAttributes().getNamedItem("l").getNodeValue());

                int posX = Integer.parseInt(floorsNodeChildren.item(i).getAttributes().getNamedItem("x").getNodeValue());
                int posY = Integer.parseInt(floorsNodeChildren.item(i).getAttributes().getNamedItem("y").getNodeValue());
                int posZ = Integer.parseInt(floorsNodeChildren.item(i).getAttributes().getNamedItem("z").getNodeValue()); 
                
                result.setFloorWidth(width)
                    .setFloorDepth(depth)
                    .setFloorX(posX)
                    .setFloorY(posY)
                    .setFloorZ(posZ)
                    .newFloor();
            }
        }
        
        return result;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Classes">
    
    private class MapMetaData {
        
        private final String mapName;
        private final String logicFilePath;
        private final String visualFilePath;
        
        public MapMetaData(String mapName, String logicFilePath, String visualFilePath) {
            this.mapName = mapName;
            this.logicFilePath = logicFilePath;
            this.visualFilePath = visualFilePath;
        }
        
        public String getMapName() {
            return mapName;
        }
        
        public String getLogicFilePath() {
            return logicFilePath;
        }
        
        public String getVisualFilePath() {
            return visualFilePath;
        }
    }
    
    //</editor-fold>
}