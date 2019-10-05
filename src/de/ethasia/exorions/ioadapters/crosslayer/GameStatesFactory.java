package de.ethasia.exorions.ioadapters.crosslayer;

import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;
import de.ethasia.exorions.technical.engine.EngineMapData;

public abstract class GameStatesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static GameStatesFactory instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void setInstance(GameStatesFactory value) {
        instance = value;
    }
    
    public static GameStatesFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract methods">
    
    public abstract OverworldGameState createOverworldGameState(EngineMapData mapDataToShow, CharacterSpriteAtlas playerSpriteAtlas);
    
    //</editor-fold>
}