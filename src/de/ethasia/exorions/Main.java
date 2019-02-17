package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;
import com.jme3.renderer.RenderManager;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Dependencies.injectDependencies();
        
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}