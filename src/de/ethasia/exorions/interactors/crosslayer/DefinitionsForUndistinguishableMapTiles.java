package de.ethasia.exorions.interactors.crosslayer;

import java.util.ArrayList;
import java.util.List;

public class DefinitionsForUndistinguishableMapTiles {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION = 6;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final List<Integer> definitions;
    private int currentDefinitionPointer;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DefinitionsForUndistinguishableMapTiles() {
        definitions = new ArrayList<>();
        currentDefinitionPointer = -1;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void addNewDefinitionWidthLengthHeightXyz(int width, int length, int height, int x, int y, int z) {
        definitions.add(width);
        definitions.add(length);
        definitions.add(height);
        definitions.add(x);
        definitions.add(y);
        definitions.add(z);
    }
    
    public void moveToNextDefinition() {
        if (hasNextDefinition()) {
            currentDefinitionPointer++;            
        }
    }
    
    public boolean hasNextDefinition() {
        int minimumSizeRequired = (currentDefinitionPointer + 1) * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION;
        return definitions.size() > minimumSizeRequired;
    }
    
    public int getCurrentDefinitionWidth() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION;
        return definitions.get(index);
    }
    
    public int getCurrentDefinitionLength() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION + 1;
        return definitions.get(index);
    }
    
    public int getCurrentDefinitionHeight() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION + 2;
        return definitions.get(index);
    }
    
    public int getCurrentDefinitionX() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION + 3;
        return definitions.get(index);
    } 
    
    public int getCurrentDefinitionY() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION + 4;
        return definitions.get(index);
    } 
    
    public int getCurrentDefinitionZ() {
        throwExceptionIfRetrievalPointerIsAtNegativePosition();
        
        int index = currentDefinitionPointer * SUM_OF_COORDINATES_AND_DIMENSIONS_PER_DEFINITION + 5;
        return definitions.get(index);
    }       
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    public void throwExceptionIfRetrievalPointerIsAtNegativePosition() {
        if (currentDefinitionPointer < 0) {
            throw new IllegalStateException("You need to move the data retrieval pointer of DefinitionsForUndistinguishableMapTiles to at least the first position before calling the data accessors.");
        }        
    }
    
    //</editor-fold>
}