package de.ethasia.exorions.ioadapters.mocks;

import de.ethasia.exorions.ioadapters.crosslayer.GameStatesFactory;
import de.ethasia.exorions.ioadapters.crosslayer.OverworldGameState;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;
import de.ethasia.exorions.technical.engine.EngineMapData;

public class MockGameStatesFactory extends GameStatesFactory {

    //<editor-fold defaultstate="collapsed" desc="GameStatesFactory Overrides">
    
    @Override
    public OverworldGameState createOverworldGameState(EngineMapData mapDataToShow, CharacterSpriteAtlas playerSpriteAtlas) {
        return new OverworldGameStateMock();
    }    
    
    //</editor-fold>
}