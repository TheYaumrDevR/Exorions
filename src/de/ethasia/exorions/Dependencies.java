package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;

import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;
import de.ethasia.exorions.technical.fileaccess.Maps;
import de.ethasia.exorions.ioadapters.presenters.RealPresentersFactory;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.technical.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;

public class Dependencies {
    
    //<editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public static void inject() {
        injectCoreDependencies();
        injectIoAdapterDependencies();
    }
    
    public static void injectEngineGlobals(SimpleApplication provider) {
        new NiftyGuiScreens.With()
            .assetManager(provider.getAssetManager())
            .audioRenderer(provider.getAudioRenderer())
            .inputManager(provider.getInputManager())
            .guiViewPort(provider.getGuiViewPort())
            .initialize();
        
        EvocriGameState.setStateManager(provider.getStateManager());      
        Maps.setAssetManager(provider.getAssetManager());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    private static void injectIoAdapterDependencies() {
        PresentersFactory.setInstance(new RealPresentersFactory());
    }
    
    //</editor-fold>
}