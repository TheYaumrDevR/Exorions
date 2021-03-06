package de.ethasia.exorions.ioadapters.crosslayer;

import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;

public abstract class TechnicalsFactory {
  
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static TechnicalsFactory instance;
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void setInstance(TechnicalsFactory value) {
        instance = value;
    }
    
    public static TechnicalsFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Abstract methods">    
    
    public abstract Maps createMaps();
    public abstract CharacterSprites createCharacterSprites();
    public abstract EngineMapDataBuilder createEngineMapDataBuilder();
    public abstract SoundEffects createSoundEffects();
    
    //</editor-fold>     
}