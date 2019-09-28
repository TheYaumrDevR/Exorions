package de.ethasia.exorions.ui.gamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PointLightShadowRenderer;
import de.ethasia.exorions.ioadapters.presenters.GuiScreens;
import de.ethasia.exorions.ui.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.ui.visuals.PlayerCharacterAvatar;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class OverworldGameState extends EvocriGameState {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private AnalogListener analogKeyInputListener;
    private PlayerCharacterAvatar playerAvatar;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.OVERWORLD);
        initKeys();
        
        playerAvatar = new PlayerCharacterAvatar.Builder()
            .setCamera(mainGameState.getCamera())
            .setCameraDistanceToAvatar(9.5f)
            .setGameInstance(mainGameState)
            .build();
        playerAvatar.attachTo(stateRootnode);

        flyCam.setDragToRotate(false);
        loadTestScene();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        detachKeys();
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
    
    private void initKeys() {
        analogKeyInputListener = createAnalogListenerForMovements();
        
        mainGameState.getInputManager().addMapping("MoveLeft", new KeyTrigger(KeyInput.KEY_A));
        mainGameState.getInputManager().addMapping("MoveUp", new KeyTrigger(KeyInput.KEY_W));
        mainGameState.getInputManager().addMapping("MoveDown", new KeyTrigger(KeyInput.KEY_S));
        mainGameState.getInputManager().addMapping("MoveRight", new KeyTrigger(KeyInput.KEY_D));
        
        mainGameState.getInputManager().addListener(analogKeyInputListener, 
                new String[]{"MoveLeft", "MoveUp", "MoveDown", "MoveRight"});        
    }
    
    private void detachKeys() {
        mainGameState.getInputManager().deleteMapping("MoveLeft");
        mainGameState.getInputManager().deleteMapping("MoveUp");
        mainGameState.getInputManager().deleteMapping("MoveDown");
        mainGameState.getInputManager().deleteMapping("MoveRight");      
        
        mainGameState.getInputManager().removeListener(analogKeyInputListener);
    }
    
    private AnalogListener createAnalogListenerForMovements() {
        AnalogListener result = new AnalogListener() {
            private float timeSinceLastAction = 0.333f;
            
            @Override
            public void onAnalog(String name, float value, float tpf) {
                if (timeSinceLastAction < 0.333f) {
                    timeSinceLastAction += tpf;
                    return;
                }
                
                timeSinceLastAction -= 0.333f;
                
                switch (name) {
                    case "MoveDown":
                        playerAvatar.moveDown();
                        break;
                    case "MoveRight":
                        playerAvatar.moveRight();
                        break;
                    case "MoveUp":
                        playerAvatar.moveUp();
                        break;
                    case "MoveLeft":
                        playerAvatar.moveLeft();
                        break;
                    default:
                        break;
                }
            }
        };
        
        return result;
    }
    
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