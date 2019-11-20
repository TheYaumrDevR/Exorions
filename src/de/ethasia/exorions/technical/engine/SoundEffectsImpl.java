package de.ethasia.exorions.technical.engine;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioData;
import com.jme3.audio.AudioNode;
import de.ethasia.exorions.ioadapters.crosslayer.SoundEffects;

public class SoundEffectsImpl implements SoundEffects {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static AssetManager assetManager;
    public static void setAssetManager(AssetManager value) {
        assetManager = value;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private AudioNode collisionSound;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SoundEffectsImpl() {
        if (null != assetManager) {
            initAudioNodes();
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="SoundEffects Overrides">
    
    @Override
    public void playCollisionSoundEffect() {
        if (null != collisionSound) {
            collisionSound.playInstance();
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void initAudioNodes() {
        collisionSound = new AudioNode(assetManager, "Sounds/Effects/Collision.wav", AudioData.DataType.Buffer);
        
        collisionSound.setPositional(false);
        collisionSound.setReverbEnabled(false);
        collisionSound.setLooping(false);        
    }
    
    //</editor-fold>
}