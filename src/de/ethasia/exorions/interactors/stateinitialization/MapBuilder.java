package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;

public class MapBuilder {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private InteriorMap product;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public MapBuilder withDimensions(short valueX, short valueZ) {
        product = new InteriorMap(valueX, valueZ);
        return this;
    }
    
    public MapBuilder withCollisionDefinitions(DefinitionsForUndistinguishableMapTiles value) {
        setTileTypesBasedOnDefinitionsTo(value, MapTileTypes.COLLISION);
        return this;
    }
    
    public MapBuilder withFloorDefinitions(DefinitionsForUndistinguishableMapTiles value) {
        setTileTypesBasedOnDefinitionsTo(value, MapTileTypes.FLOOR);
        return this;
    }
    
    public InteriorMap build() {
        return product;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void setTileTypesBasedOnDefinitionsTo(DefinitionsForUndistinguishableMapTiles value, MapTileTypes tileType) {
        while (value.hasNextDefinition()) {
            value.moveToNextDefinition();

            int i = value.getCurrentDefinitionX();
            int upperBoundX = i + value.getCurrentDefinitionWidth();
            while (i < upperBoundX) {         
                int j = value.getCurrentDefinitionY();
                int upperBoundY = j + value.getCurrentDefinitionHeight();
                
                while (j < upperBoundY) {
                    int k = value.getCurrentDefinitionZ();
                    int upperBoundZ = k + value.getCurrentDefinitionLength();
                    
                    while (k < upperBoundZ) {
                        product.setTileTypeAt(tileType, (short)i, (short)j, (short)k);
                        k++;
                    }
                    j++;
                }
                i++;
            }
        }        
    }
    
    //</editor-fold>
}