package de.ethasia.exorions.technical.engine;

import de.ethasia.exorions.technical.engine.MovementStopRunnable;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;

public class PlayerCharacterAvatar {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final Vector3f WALKING_VECTOR_DOWN = new Vector3f(0.f, 0.f, 0.04f);
    private static final Vector3f WALKING_VECTOR_RIGHT = new Vector3f(0.04f, 0.f, 0.f);
    private static final Vector3f WALKING_VECTOR_UP = new Vector3f(0.f, 0.f, -0.04f);
    private static final Vector3f WALKING_VECTOR_LEFT = new Vector3f(-0.04f, 0.f, 0.f);
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final SimpleApplication gameInstance;
    private final Node rootNode;
    private final ChaseCamera chaseCam;
    private final CharacterControl characterPhysics;
    private BillboardControl screenFacer;
    private Spatial characterSpriteHolder;
    private boolean isMoving;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private PlayerCharacterAvatar(Builder source) {
        rootNode = new Node();
        chaseCam = new ChaseCamera(source.chaseCamBase);
        characterPhysics = new CharacterControl(new CapsuleCollisionShape(0.4f, 0.f), 0.1f);
        gameInstance = source.gameInstance;
        setupChaseCam(source);
        setupPhysics();
        setupSpriteHolder(source);
        setupBillboardControl();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void attachTo(Node parentNode) {
        parentNode.attachChild(rootNode);
    }
    
    public void moveDown() {
        if (!isMoving) {
            isMoving = true;
            characterPhysics.setWalkDirection(WALKING_VECTOR_DOWN);
            MovementStopRunnable.startMovementStopTimer(333, this);
        }
    }
    
    public void moveRight() {
        if (!isMoving) {
            isMoving = true;
            characterPhysics.setWalkDirection(WALKING_VECTOR_RIGHT);
            MovementStopRunnable.startMovementStopTimer(333, this);
        }
    } 
    
    public void moveUp() {
        if (!isMoving) {
            isMoving = true;
            characterPhysics.setWalkDirection(WALKING_VECTOR_UP);
            MovementStopRunnable.startMovementStopTimer(333, this);
        }
    }

    public void moveLeft() {
        if (!isMoving) {
            isMoving = true;
            characterPhysics.setWalkDirection(WALKING_VECTOR_LEFT);
            MovementStopRunnable.startMovementStopTimer(333, this);
        }
    }     
    
    public void stopMoving() {
        isMoving = false;
        characterPhysics.setWalkDirection(Vector3f.ZERO);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void setupChaseCam(Builder source) {
        chaseCam.setDefaultDistance(source.cameraDistanceToAvatar);
        chaseCam.setDefaultHorizontalRotation(3.14f / 2.f);
        chaseCam.setDefaultVerticalRotation(3.14f / 6.f);
    }
    
    private void setupPhysics() {
        rootNode.addControl(characterPhysics);
        characterPhysics.setPhysicsLocation(new Vector3f(0.1f + 1.6f, 0.f, 0.4f + 2.4f));
        characterPhysics.setJumpSpeed(20);
        characterPhysics.setFallSpeed(20);
        characterPhysics.setGravity(new Vector3f(0.f, -9.8f, 0.f));
    }
    
    private void setupSpriteHolder(Builder source) {
        Texture testSprite = gameInstance.getAssetManager().loadTexture("CharacterSprites/StandardMale/Bases/0.png");
        testSprite.setMagFilter(Texture.MagFilter.Nearest);
        
        Material textureMat = new Material(gameInstance.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        textureMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        textureMat.setFloat("AlphaDiscardThreshold", 1.f);
        textureMat.setTexture("ColorMap", testSprite);
        
        characterSpriteHolder = new Geometry("characterSpriteHolder", new Quad(0.6f, 1.2f));   
        characterSpriteHolder.setQueueBucket(RenderQueue.Bucket.Transparent);
        characterSpriteHolder.addControl(chaseCam);
        characterSpriteHolder.setMaterial(textureMat);
        rootNode.attachChild(characterSpriteHolder);
    }
    
    private void setupBillboardControl() {
        screenFacer = new BillboardControl();
        screenFacer.setAlignment(BillboardControl.Alignment.Screen);
        rootNode.addControl(screenFacer);        
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