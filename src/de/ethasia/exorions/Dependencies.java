package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;

import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;
import de.ethasia.exorions.technical.fileaccess.MapsImpl;
import de.ethasia.exorions.ioadapters.presenters.RealPresentersFactory;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.technical.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.technical.RealTechnicalsFactory;
import de.ethasia.exorions.technical.fileaccess.CharacterSprites;

public class Dependencies {
    
    //<editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public static void inject() {
        injectCoreDependencies();
        injectIoAdapterDependencies();
        injectTechnicalDependencies();
    }
    
    public static void injectEngineGlobals(SimpleApplication provider) {
        new NiftyGuiScreens.With()
            .assetManager(provider.getAssetManager())
            .audioRenderer(provider.getAudioRenderer())
            .inputManager(provider.getInputManager())
            .guiViewPort(provider.getGuiViewPort())
            .initialize();
        
        EvocriGameState.setStateManager(provider.getStateManager());      
        MapsImpl.setAssetManager(provider.getAssetManager());
        CharacterSprites.setAssetManager(provider.getAssetManager());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    private static void injectIoAdapterDependencies() {
        PresentersFactory.setInstance(new RealPresentersFactory());
    }
    
    private static void injectTechnicalDependencies() {
        TechnicalsFactory.setInstance(new RealTechnicalsFactory());
    }
    
    //</editor-fold>
}