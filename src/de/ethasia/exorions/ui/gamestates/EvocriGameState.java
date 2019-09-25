package de.ethasia.exorions.ui.gamestates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.FlyByCamera;
import com.jme3.scene.Node;
import de.lessvoid.nifty.screen.ScreenController;

public abstract class EvocriGameState extends AbstractAppState implements ScreenController {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static AppStateManager stateManager;    
    private static EvocriGameState currentGameState;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    protected FlyByCamera flyCam;
    protected Node stateRootnode;
    protected SimpleApplication mainGameState;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setStateManager(AppStateManager value) {
        stateManager = value;
    }
    
    public static void setGameState(EvocriGameState gameState) {
        if (null != currentGameState) {
            stateManager.detach(currentGameState);
        }
        
        stateManager.attach(gameState);
        currentGameState = gameState;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AbstractAppState Overrides">
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        mainGameState = (SimpleApplication)app;
        
        flyCam = mainGameState.getFlyByCamera();
        
        stateRootnode = new Node();
        mainGameState.getRootNode().attachChild(stateRootnode);
    }
    
    @Override
    public void stateDetached(AppStateManager stateManager) {
        super.stateDetached(stateManager);
        mainGameState.getRootNode().detachAllChildren();
    }
    
    //</editor-fold>
}