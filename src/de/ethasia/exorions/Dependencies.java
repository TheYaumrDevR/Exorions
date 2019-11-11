package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;

import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.technical.fileaccess.MapsImpl;
import de.ethasia.exorions.ioadapters.presenters.RealPresentersFactory;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.technical.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.ioadapters.UseCasesFactoryIoAdapters;
import de.ethasia.exorions.ioadapters.UseCasesFactoryIoAdaptersImpl;
import de.ethasia.exorions.ioadapters.crosslayer.GameStatesFactory;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.ioadapters.presenters.RealGatewaysFactory;
import de.ethasia.exorions.technical.RealTechnicalsFactory;
import de.ethasia.exorions.technical.fileaccess.CharacterSpritesImpl;
import de.ethasia.exorions.technical.jmegamestates.RealGameStatesFactory;

public class Dependencies {
    
    //<editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public static void inject() {
        injectCoreDependencies();
        injectInteractorDependencies();
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
        CharacterSpritesImpl.setAssetManager(provider.getAssetManager());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    private static void injectInteractorDependencies() {
        UseCasesFactoryIoAdapters.setInstance(new UseCasesFactoryIoAdaptersImpl());
    }
    
    private static void injectIoAdapterDependencies() {
        PresentersFactory.setInstance(new RealPresentersFactory());
        GatewaysFactory.setInstance(new RealGatewaysFactory());
    }
    
    private static void injectTechnicalDependencies() {
        TechnicalsFactory.setInstance(new RealTechnicalsFactory());
        GameStatesFactory.setInstance(new RealGameStatesFactory());
    }
    
    //</editor-fold>
}