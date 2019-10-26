package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;
import de.ethasia.exorions.technical.engine.EngineMapData;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
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
    public void presentOverworldWithMapFromMetaData(MapMetaData newGameMapMetaData) {
        TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
        GameStatesFactory gameStatesFactory = GameStatesFactory.getInstance();
        
        Maps maps = technicalsFactory.createMaps();
        CharacterSprites characterSprites = technicalsFactory.createCharacterSprites();
        
        Document mapLogic = maps.readMapLogic(newGameMapMetaData.getLogicFilePath());
        EngineMapDataBuilder mapDataForEngineBuilder = setupLogicalMapData(mapLogic);
        
        mapDataForEngineBuilder.setMapVisuals(maps.readMapVisuals(newGameMapMetaData.getVisualFilePath()));
        EngineMapData mapDataForEngine = mapDataForEngineBuilder.build();
        CharacterSpriteAtlas playerSpriteAtlas = characterSprites.loadSpritesFrom("CharacterSprites/StandardMale/Bases");
        
        OverworldGameState overworldState = gameStatesFactory.createOverworldGameState(mapDataForEngine, playerSpriteAtlas);
        EvocriGameState.setGameState(overworldState);
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private EngineMapDataBuilder setupLogicalMapData(Document mapLogic) {
        EngineMapDataBuilder result = TechnicalsFactory.getInstance().createEngineMapDataBuilder();
        
        NodeList floors = mapLogic.getElementsByTagName("floors");
        if (floors.getLength() > 0) {
            Element firstFloorsNode = (Element)floors.item(0);
            NodeList floorsNodeChildren = firstFloorsNode.getElementsByTagName("f");

            for (int i = 0; i < floorsNodeChildren.getLength(); i++) {
                try {
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
                } catch (NumberFormatException | NullPointerException ex) {}
            }
        }
        
        return result;
    }
    
    //</editor-fold>
}