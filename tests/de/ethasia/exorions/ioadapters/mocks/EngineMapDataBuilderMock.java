package de.ethasia.exorions.ioadapters.mocks;

import com.jme3.scene.Spatial;
import de.ethasia.exorions.interactors.crosslayer.EngineMapDataBuilder;
import de.ethasia.exorions.technical.engine.EngineMapData;

public class EngineMapDataBuilderMock implements EngineMapDataBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static int lastSetFloorWidth;
    public static int getLastSetFloorWidth() {
        return lastSetFloorWidth;
    }
    
    private static int lastSetFloorDepth;
    public static int getLastSetFloorDepth() {
        return lastSetFloorDepth;
    }    
    
    private static int lastSetFloorX;
    public static int getLastSetFloorX() {
        return lastSetFloorX;
    }
    
    private static int lastSetFloorY;
    public static int getLastSetFloorY() {
        return lastSetFloorY;
    }
    
    private static int lastSetFloorZ;
    public static int getLastSetFloorZ() {
        return lastSetFloorZ;
    }
    
    private static int newFloorCallCount;
    public static int getNewFloorCallCount() {
        return newFloorCallCount;
    }
    
    public static void resetMockCounters() {
        lastSetFloorWidth = 0;
        lastSetFloorDepth = 0;
        lastSetFloorX = 0;
        lastSetFloorY = 0;
        lastSetFloorZ = 0;
        newFloorCallCount = 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EngineMapDataBuilder Overrides">
    
    @Override
    public EngineMapDataBuilder setFloorWidth(int value) {
        lastSetFloorWidth = value;
        return this;
    }
        
    @Override
    public EngineMapDataBuilder setFloorDepth(int value) {
        lastSetFloorDepth = value;
        return this;
    }
        
    @Override
    public EngineMapDataBuilder setFloorX(int value) {
        lastSetFloorX = value;
        return this;
    }

    @Override
    public EngineMapDataBuilder setFloorY(int value) {
        lastSetFloorY = value;
        return this;
    }    
        
    @Override
    public EngineMapDataBuilder setFloorZ(int value) {
        lastSetFloorZ = value;
        return this;
    }        
    
    @Override
    public EngineMapDataBuilder newFloor() {
        newFloorCallCount++;
        return this;
    }
        
    @Override
    public EngineMapDataBuilder setMapVisuals(Spatial value) {
        return this;
    }
        
    @Override
    public EngineMapData build() {
        return null;
    }    
    
    //</editor-fold>
}