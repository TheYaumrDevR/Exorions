package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;
import de.ethasia.exorions.technical.engine.EngineMapData;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.ioadapters.crosslayer.CharacterSprites;
import de.ethasia.exorions.ioadapters.crosslayer.GameStatesFactory;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.OverworldGameState;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class OverworldStatePresenterImpl implements OverworldStatePresenter {

    //<editor-fold defaultstate="collapsed" desc="OverworldStatePresenter Overrides">
    
    @Override
    public void presentOverworldWithNewGameMap() {
        TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
        GameStatesFactory gameStatesFactory = GameStatesFactory.getInstance();
        
        Maps maps = technicalsFactory.createMaps();
        CharacterSprites characterSprites = technicalsFactory.createCharacterSprites();
        
        Document mapList = maps.readMapList();
        MapMetaData startingMapData = tryToFindMapDataOfStartingMap(mapList);
        
        Document mapLogic = maps.readMapLogic(startingMapData.logicFilePath);
        EngineMapDataBuilder mapDataForEngineBuilder = setupLogicalMapData(mapLogic);
        
        mapDataForEngineBuilder.setMapVisuals(maps.readMapVisuals(startingMapData.visualFilePath));
        EngineMapData mapDataForEngine = mapDataForEngineBuilder.build();
        CharacterSpriteAtlas playerSpriteAtlas = characterSprites.loadSpritesFrom("CharacterSprites/StandardMale/Bases");
        
        OverworldGameState overworldState = gameStatesFactory.createOverworldGameState(mapDataForEngine, playerSpriteAtlas);
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
    
    private EngineMapDataBuilder setupLogicalMapData(Document mapLogic) {
        EngineMapDataBuilder result = TechnicalsFactory.getInstance().createEngineMapDataBuilder();
        
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