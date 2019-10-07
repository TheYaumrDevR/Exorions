package de.ethasia.exorions.technical.engine;

import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;

public class EngineMapData {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Node rootNode;
    private final RigidBodyControl floorPhysics;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private EngineMapData(Builder builder) {
        rootNode = new Node();
        rootNode.attachChild(builder.mapVisuals);
        floorPhysics = new RigidBodyControl(builder.floors, 0);
        rootNode.addControl(floorPhysics);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void attachTo(Node parentNode) {
        parentNode.attachChild(rootNode);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder implements EngineMapDataBuilder {
        
        private final CompoundCollisionShape floors;
        
        private int floorX, floorY, floorZ;
        private int floorWidth, floorDepth;
        private Spatial mapVisuals;
        
        public Builder() {
            floors = new CompoundCollisionShape();
        }
        
        @Override
        public Builder setFloorWidth(int value) {
            floorWidth = value;
            return this;
        }
        
        @Override
        public Builder setFloorDepth(int value) {
            floorDepth = value;
            return this;
        }
        
        @Override
        public Builder setFloorX(int value) {
            floorX = value;
            return this;
        }

        @Override
        public Builder setFloorY(int value) {
            floorY = value;
            return this;
        }    
        
        @Override
        public Builder setFloorZ(int value) {
            floorZ = value;
            return this;
        }        
    
        @Override
        public Builder newFloor() {
            if (floorWidth > 0 && floorDepth > 0) {
                CollisionShape floor = new BoxCollisionShape(new Vector3f(0.4f * floorWidth, 0.4f, 0.4f * floorDepth));
                floors.addChildShape(floor, new Vector3f(0.8f * floorX + 0.8f * (floorWidth / 2.f), 0.8f * floorY - 0.8f, 0.8f * floorZ + 0.8f * (floorDepth / 2.f)));
            }
            
            return this;
        }
        
        @Override
        public Builder setMapVisuals(Spatial value) {
            mapVisuals = value;
            return this;
        }
        
        @Override
        public EngineMapData build() {
            if (null == mapVisuals) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new EngineMapData(this);
        }
    }
    
    //</editor-fold>
}