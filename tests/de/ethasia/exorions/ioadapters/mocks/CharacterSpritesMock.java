package de.ethasia.exorions.ioadapters.mocks;

import de.ethasia.exorions.ioadapters.crosslayer.CharacterSprites;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;

public class CharacterSpritesMock implements CharacterSprites {

    //<editor-fold defaultstate="collapsed" desc="CharacterSprites Overrides">
    
    @Override
    public CharacterSpriteAtlas loadSpritesFrom(String path) {
        return new CharacterSpriteAtlas.Builder().build();
    }    
    
    //</editor-fold>
}