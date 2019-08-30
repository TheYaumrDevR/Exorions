package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Dependencies.injectDependencies();
        
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        Spatial teapot = assetManager.loadModel("Models/Player Room/Computer/Computer.j3o");
        rootNode.attachChild(teapot);        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}