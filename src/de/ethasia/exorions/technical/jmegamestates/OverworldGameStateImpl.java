package de.ethasia.exorions.technical.jmegamestates;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.light.PointLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.ViewPort;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PointLightShadowRenderer;
import de.ethasia.exorions.ioadapters.controllers.PlayerMovementController;
import de.ethasia.exorions.ioadapters.crosslayer.OverworldGameState;
import de.ethasia.exorions.ioadapters.presenters.GuiScreens;
import de.ethasia.exorions.technical.engine.CharacterSpriteAtlas;
import de.ethasia.exorions.technical.engine.EngineMapData;
import de.ethasia.exorions.technical.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.technical.engine.PlayerCharacterAvatar;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class OverworldGameStateImpl extends OverworldGameState {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BulletAppState physics;
    private AnalogListener analogKeyInputListener;
    private PlayerMovementController playerMovementController;
    private final EngineMapData mapToShow;
    private final CharacterSpriteAtlas playerSpriteAtlas;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public OverworldGameStateImpl(EngineMapData mapDataToShow, CharacterSpriteAtlas playerSpriteAtlas) {
        mapToShow = mapDataToShow;
        this.playerSpriteAtlas = playerSpriteAtlas;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Implementations">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        NiftyGuiScreens.gotoScreen(GuiScreens.OVERWORLD);
        initKeys();
        
        PlayerCharacterAvatar playerAvatar = new PlayerCharacterAvatar.Builder()
            .setCamera(mainGameState.getCamera())
            .setCameraDistanceToAvatar(9.5f)
            .setGameInstance(mainGameState)
            .setCharacterSpriteAtlas(playerSpriteAtlas)
            .build();
        playerAvatar.attachTo(stateRootnode);
        
        flyCam.setDragToRotate(false);
        loadTestScene(mapToShow);    
        initPhysics();
        playerMovementController = new PlayerMovementController();
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        physics.getPhysicsSpace().removeAll(stateRootnode);
        mainGameState.getStateManager().detach(physics);
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
        AnalogListener result = (String name, float value, float tpf) -> {
            switch (name) {
                case "MoveDown":
                    playerMovementController.moveDown();
                    break;
                case "MoveRight":
                    playerMovementController.moveRight();
                    break;
                case "MoveUp":
                    playerMovementController.moveUp();
                    break;
                case "MoveLeft":
                    playerMovementController.moveLeft();
                    break;
                default:
                    break;
            }
        };
        
        return result;
    }
    
    private void loadTestScene(EngineMapData mapDataToShow) {
        AssetManager assetManager = mainGameState.getAssetManager();
        ViewPort viewPort = mainGameState.getViewPort();
        
        mapDataToShow.attachTo(stateRootnode);
        
        PointLight lamp = new PointLight(new Vector3f(2.45f, 2.4f, 2.6f));
        stateRootnode.addLight(lamp);
        
        PointLightShadowRenderer pointLightShadow = new PointLightShadowRenderer(assetManager, 1024);
        pointLightShadow.setLight(lamp);
        viewPort.addProcessor(pointLightShadow);        
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0.5f, -0.7f, -0.3f));
        stateRootnode.addLight(sun);
        
        DirectionalLightShadowRenderer sunShadow = new DirectionalLightShadowRenderer(assetManager, 4096, 4);
        sunShadow.setLight(sun);
        viewPort.addProcessor(sunShadow);                
    }
    
    private void initPhysics() {
        physics = new BulletAppState();
        mainGameState.getStateManager().attach(physics);
        physics.getPhysicsSpace().addAll(stateRootnode);          
    }
    
    //</editor-fold>
}