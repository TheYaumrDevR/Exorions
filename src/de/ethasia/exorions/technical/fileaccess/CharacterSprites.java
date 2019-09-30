package de.ethasia.exorions.technical.fileaccess;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import de.ethasia.exorions.ioadapters.presenters.DebugWarningLog;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;

public class CharacterSprites {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static AssetManager assetManager;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters">
    
    public static void setAssetManager(AssetManager value) {
        assetManager = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static CharacterSpriteAtlas loadSpritesFrom(String path) {
        CharacterSpriteAtlas.Builder spriteAtlasBuilder = new CharacterSpriteAtlas.Builder();
        
        for (int i = 0; i < 12; i++) {
            try {
                Texture sprite = assetManager.loadTexture(path + "/" + i + ".png");
                spriteAtlasBuilder.setSpriteOn(sprite, i);      
            } catch (Exception ex) {
                DebugWarningLog.addLogEntry(System.currentTimeMillis(), "Could not load character sprite from path: " + path);
            }
        }   
        
        return spriteAtlasBuilder.build();
    }
    
    //</editor-fold>
}