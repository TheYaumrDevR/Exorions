package de.ethasia.exorions.ioadapters.mocks;

import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;
import de.ethasia.exorions.ioadapters.crosslayer.CharacterSprites;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.SoundEffects;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;

public class MockTechnicalsFactory extends TechnicalsFactory {

    //<editor-fold defaultstate="collapsed" desc="TechnicalsFactory Overrides">
    
    @Override
    public Maps createMaps() {
        return new MapsMock();
    }

    @Override
    public CharacterSprites createCharacterSprites() {
        return new CharacterSpritesMock();
    }    
    
    @Override
    public EngineMapDataBuilder createEngineMapDataBuilder() {
        return new EngineMapDataBuilderMock();
    }    
    
    @Override
    public SoundEffects createSoundEffects() {
        return new SoundEffectsMock();
    }    
    
    //</editor-fold>    
}