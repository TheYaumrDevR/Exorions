package de.ethasia.exorions.interactors.crosslayer.tests;

import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class MapCollisionDefinitionsTest {
    
    @Test
    public void testHasNextDefinition_noDefinitionsAdded_returnsFalse() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        
        boolean result = testCandidate.hasNextDefinition();
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testAddNewDefinitionWidthLengthHeightXyz_hasNextCoordinate_returnsTrue() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        boolean result = testCandidate.hasNextDefinition();
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testMoveToNextDefinition_oneDefinitionPresentMovesOnce_hasNextReturnsFalse() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.moveToNextDefinition();
        
        boolean result = testCandidate.hasNextDefinition();
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testMoveToNextDefinition_twoDefinitionsPresentMovesOnce_hasNextReturnsTrue() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        
        boolean result = testCandidate.hasNextDefinition();
        
        assertThat(result, is(true)); 
    }
    
    @Test
    public void testGetCurrentDefinitionWidth_twoDefinitionsPresentMovesTwice_returnsCorrectWidth() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionWidth();
        
        assertThat(result, is(1));
    }
    
    @Test
    public void testGetCurrentDefinitionLength_twoDefinitionsPresentMovesTwice_returnsCorrectLength() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionLength();
        
        assertThat(result, is(1));
    }

    @Test
    public void testGetCurrentDefinitionHeight_twoDefinitionsPresentMovesTwice_returnsCorrectHeight() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionHeight();
        
        assertThat(result, is(5));
    }
    
    @Test
    public void testGetCurrentDefinitionX_twoDefinitionsPresentMovesTwice_returnsCorrectX() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionX();
        
        assertThat(result, is(3));
    } 

    @Test
    public void testGetCurrentDefinitionY_twoDefinitionsPresentMovesTwice_returnsCorrectY() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionY();
        
        assertThat(result, is(0));
    }

    @Test
    public void testGetCurrentDefinitionZ_twoDefinitionsPresentMovesTwice_returnsCorrectZ() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextDefinition();
        testCandidate.moveToNextDefinition();
        
        int result = testCandidate.getCurrentDefinitionZ();
        
        assertThat(result, is(2));
    }   
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionWidth_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionWidth();
    }    
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionLength_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionLength();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionHeight_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionHeight();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionX_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionX();
    }   
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionY_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionY();
    }  
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionZ_moveToNextDefinitionWasNotCalled_throwsException() {
        DefinitionsForUndistinguishableMapTiles testCandidate = new DefinitionsForUndistinguishableMapTiles();
        testCandidate.addNewDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionZ();
    }    
}