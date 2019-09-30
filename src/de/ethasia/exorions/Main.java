package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;
import de.ethasia.exorions.technical.jmegamestates.StartGameState;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Dependencies.inject();
        
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Dependencies.injectEngineGlobals(this);
        EvocriGameState.setGameState(new StartGameState());
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}