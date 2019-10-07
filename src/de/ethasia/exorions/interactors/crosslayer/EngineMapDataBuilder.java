package de.ethasia.exorions.interactors.crosslayer;

import com.jme3.scene.Spatial;
import de.ethasia.exorions.technical.engine.EngineMapData;

public interface EngineMapDataBuilder {
   
        public EngineMapDataBuilder setFloorWidth(int value); 
        public EngineMapDataBuilder setFloorDepth(int value);
        public EngineMapDataBuilder setFloorX(int value);
        public EngineMapDataBuilder setFloorY(int value);   
        public EngineMapDataBuilder setFloorZ(int value);       
        public EngineMapDataBuilder newFloor();
        public EngineMapDataBuilder setMapVisuals(Spatial value);
        public EngineMapData build();  
}