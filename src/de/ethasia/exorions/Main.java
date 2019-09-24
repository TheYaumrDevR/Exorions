package de.ethasia.exorions;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PointLightShadowRenderer;
import de.ethasia.exorions.gui.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.ioadapters.presenters.GuiScreens;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Dependencies.injectDependencies();
        
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
        
        NiftyGuiScreens.gotoScreen(GuiScreens.START);
        
        flyCam.setEnabled(false);
    }

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void loadTestModel() {
        Spatial room = assetManager.loadModel("Scenes/Player Room/PlayerRoom.j3o");
        
        PointLight lamp = new PointLight(new Vector3f(2.45f, 2.4f, 2.6f));
        room.addLight(lamp);
        
        PointLightShadowRenderer pointLightShadow = new PointLightShadowRenderer(assetManager, 1024);
        pointLightShadow.setLight(lamp);
        viewPort.addProcessor(pointLightShadow);        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.5f, -0.7f, -0.3f));
        room.addLight(sun);
        
        DirectionalLightShadowRenderer sunShadow = new DirectionalLightShadowRenderer(assetManager, 4096, 4);
        sunShadow.setLight(sun);
        viewPort.addProcessor(sunShadow);          
        
        rootNode.attachChild(room);          
    }
    
    //</editor-fold>
}