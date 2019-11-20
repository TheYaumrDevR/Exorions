package de.ethasia.exorions.ioadapters.mocks;

import de.ethasia.exorions.ioadapters.crosslayer.SoundEffects;

public class SoundEffectsMock implements SoundEffects {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static int playCollisionSoundEffectCallCount;
    public static int getPlayCollisionSoundEffectCallCount() {
        return playCollisionSoundEffectCallCount;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void resetMock() {
        playCollisionSoundEffectCallCount = 0;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SoundEffects Overrides">
    
    @Override
    public void playCollisionSoundEffect() {
        playCollisionSoundEffectCallCount++;
    }    
    
    //</editor-fold>    
}