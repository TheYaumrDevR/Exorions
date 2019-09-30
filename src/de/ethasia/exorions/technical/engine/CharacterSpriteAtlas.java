package de.ethasia.exorions.technical.engine;

import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;

public class CharacterSpriteAtlas {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER = 12;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Texture[] sprites;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public Texture getSpriteOn(int position) {
        if (position > -1 && position < NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER) {
            return sprites[position];
        }        
        
        return new Texture2D();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private CharacterSpriteAtlas(Builder builder) {
        sprites = builder.sprites;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private final Texture[] sprites;
        
        public Builder() {
            sprites = new Texture[NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER];
            
            for (int i = 0; i < NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER; i++) {
                sprites[i] = new Texture2D();
            }            
        }
        
        public Builder setSpriteOn(Texture sprite, int position) {
            if (position > -1 && position < NUMBER_OF_ANIMATION_FRAMES_PER_CHARACTER) {
                sprites[position] = sprite;
            }
            
            return this;
        }
        
        public CharacterSpriteAtlas build() {
            return new CharacterSpriteAtlas(this);
        }
    }
    
    //</editor-fold>
}