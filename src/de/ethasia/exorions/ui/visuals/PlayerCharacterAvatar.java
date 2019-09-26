package de.ethasia.exorions.ui.visuals;

import com.jme3.app.SimpleApplication;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Quad;

public class PlayerCharacterAvatar {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final SimpleApplication gameInstance;
    private final Node rootNode;
    private final ChaseCamera chaseCam;
    private Spatial characterSpriteHolder;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private PlayerCharacterAvatar(Builder source) {
        rootNode = new Node();
        chaseCam = new ChaseCamera(source.chaseCamBase);
        gameInstance = source.gameInstance;
        setupChaseCam(source);
        setupSpriteHolder(source);
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void attachTo(Node parentNode) {
        parentNode.attachChild(rootNode);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void setupChaseCam(Builder source) {
        chaseCam.setDefaultDistance(source.cameraDistanceToAvatar);
        chaseCam.setDefaultHorizontalRotation(3.14f / 2.f);
        chaseCam.setDefaultVerticalRotation(3.14f / 6.f);
    }
    
    private void setupSpriteHolder(Builder source) {
        Material textureMat = new Material(gameInstance.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        
        characterSpriteHolder = new Geometry("characterSpriteHolder", new Quad(0.8f, 0.8f));   
        characterSpriteHolder.setQueueBucket(RenderQueue.Bucket.Transparent);
        characterSpriteHolder.setLocalTranslation(1.6f, -0.4f, 0.4f + 2.4f);
        characterSpriteHolder.addControl(chaseCam);
        characterSpriteHolder.setMaterial(textureMat);
        rootNode.attachChild(characterSpriteHolder);
    }
    
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private Camera chaseCamBase;
        private float cameraDistanceToAvatar;
        private SimpleApplication gameInstance;
        
        public Builder setCamera(Camera value) {
            chaseCamBase = value;
            return this;
        }
        
        public Builder setCameraDistanceToAvatar(float value) {
            cameraDistanceToAvatar = value;
            return this;
        }
        
        public Builder setGameInstance(SimpleApplication value) {
            gameInstance = value;
            return this;
        }
        
        public PlayerCharacterAvatar build() {
            return new PlayerCharacterAvatar(this);
        }
    }
    
    //</editor-fold>
}