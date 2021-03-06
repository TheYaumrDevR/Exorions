package de.ethasia.exorions.technical;

import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;
import de.ethasia.exorions.ioadapters.crosslayer.CharacterSprites;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.SoundEffects;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.technical.engine.EngineMapData;
import de.ethasia.exorions.technical.engine.SoundEffectsImpl;
import de.ethasia.exorions.technical.fileaccess.CharacterSpritesImpl;
import de.ethasia.exorions.technical.fileaccess.MapsImpl;

public class RealTechnicalsFactory extends TechnicalsFactory {

    //<editor-fold defaultstate="collapsed" desc="TechnicalsFactory Overrides">
    
    @Override
    public Maps createMaps() {
        return new MapsImpl();
    }    
    
    @Override
    public CharacterSprites createCharacterSprites() {
        return new CharacterSpritesImpl();
    }    
    
    @Override
    public EngineMapDataBuilder createEngineMapDataBuilder() {
        return new EngineMapData.Builder();
    }    
    
    @Override
    public SoundEffects createSoundEffects() {
        return new SoundEffectsImpl();
    }
    
    //</editor-fold>    
}