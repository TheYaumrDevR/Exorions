package de.ethasia.exorions.ui.gamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PointLightShadowRenderer;
import de.ethasia.exorions.ioadapters.presenters.GuiScreens;
import de.ethasia.exorions.ui.niftygui.NiftyGuiScreens;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class OverworldGameState extends EvocriGameState {
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.OVERWORLD);
        flyCam.setEnabled(true);
        flyCam.setDragToRotate(false);
        flyCam.setMoveSpeed(3);
        loadTestScene();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ScreenController Overrides">
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }    
    
    //</editor-fold>  
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void loadTestScene() {
        AssetManager assetManager = mainGameState.getAssetManager();
        ViewPort viewPort = mainGameState.getViewPort();
        
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
        
        stateRootnode.attachChild(room);          
    }
    
    //</editor-fold>
}