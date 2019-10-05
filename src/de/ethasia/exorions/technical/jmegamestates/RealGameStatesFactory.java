package de.ethasia.exorions.technical.jmegamestates;

import de.ethasia.exorions.ioadapters.crosslayer.GameStatesFactory;
import de.ethasia.exorions.ioadapters.crosslayer.OverworldGameState;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;
import de.ethasia.exorions.technical.engine.EngineMapData;

public class RealGameStatesFactory extends GameStatesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="GameStatesFactory Overrides">
    
    @Override
    public OverworldGameState createOverworldGameState(EngineMapData mapDataToShow, CharacterSpriteAtlas playerSpriteAtlas) {
        return new OverworldGameStateImpl(mapDataToShow, playerSpriteAtlas);
    }    
    
    //</editor-fold>
}