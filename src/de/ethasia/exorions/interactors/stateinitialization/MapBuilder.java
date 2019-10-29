package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.interactors.crosslayer.MapCollisionDefinitions;

public class MapBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private InteriorMap product;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public MapBuilder withDimensions(short valueX, short valueZ) {
        product = new InteriorMap(valueX, valueZ);
        return this;
    }
    
    public MapBuilder withCollisionDefinitions(MapCollisionDefinitions value) {
        while (value.hasNextCollisionDefinition()) {
            value.moveToNextCollisionDefinition();

            int i = value.getCurrentDefinitionX();
            int upperBoundX = i + value.getCurrentDefinitionWidth();
            while (i < upperBoundX) {         
                int j = value.getCurrentDefinitionY();
                int upperBoundY = j + value.getCurrentDefinitionHeight();
                
                while (j < upperBoundY) {
                    int k = value.getCurrentDefinitionZ();
                    int upperBoundZ = k + value.getCurrentDefinitionLength();
                    
                    while (k < upperBoundZ) {
                        product.setTileTypeAt(MapTileTypes.COLLISION, (short)i, (short)j, (short)k);
                        k++;
                    }
                    j++;
                }
                i++;
            }
        }
        
        return this;
    }
    
    public InteriorMap build() {
        return product;
    }
    
    //</editor-fold>
}