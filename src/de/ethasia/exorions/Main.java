package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import de.ethasia.exorions.ui.gamestates.EvocriGameState;
import de.ethasia.exorions.ui.gamestates.StartGameState;
import de.ethasia.exorions.ui.niftygui.NiftyGuiScreens;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Dependencies.inject();
        
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        new NiftyGuiScreens.With()
            .assetManager(assetManager)
            .audioRenderer(audioRenderer)
            .inputManager(inputManager)
            .guiViewPort(guiViewPort)
            .initialize();
        
        EvocriGameState.setStateManager(stateManager);
        EvocriGameState.setGameState(new StartGameState());
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}