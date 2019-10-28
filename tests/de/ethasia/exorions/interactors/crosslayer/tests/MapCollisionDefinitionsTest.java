package de.ethasia.exorions.interactors.crosslayer.tests;

import de.ethasia.exorions.interactors.crosslayer.MapCollisionDefinitions;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class MapCollisionDefinitionsTest {
    
    @Test
    public void testHasNextCollisionDefinition_noDefinitionsAdded_returnsFalse() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        
        boolean result = testCandidate.hasNextCollisionDefinition();
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testAddNewCollisionDefinitionWidthLengthHeightXyz_hasNextCoordinate_returnsTrue() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        boolean result = testCandidate.hasNextCollisionDefinition();
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testMoveToNextCollisionDefinition_oneDefinitionPresentMovesOnce_hasNextReturnsFalse() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.moveToNextCollisionDefinition();
        
        boolean result = testCandidate.hasNextCollisionDefinition();
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testMoveToNextCollisionDefinition_twoDefinitionsPresentMovesOnce_hasNextReturnsTrue() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        
        boolean result = testCandidate.hasNextCollisionDefinition();
        
        assertThat(result, is(true)); 
    }
    
    @Test
    public void testGetCurrentDefinitionWidth_twoDefinitionsPresentMovesTwice_returnsCorrectWidth() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionWidth();
        
        assertThat(result, is(1));
    }
    
    @Test
    public void testGetCurrentDefinitionLength_twoDefinitionsPresentMovesTwice_returnsCorrectLength() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 0, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionLength();
        
        assertThat(result, is(1));
    }

    @Test
    public void testGetCurrentDefinitionHeight_twoDefinitionsPresentMovesTwice_returnsCorrectHeight() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionHeight();
        
        assertThat(result, is(5));
    }
    
    @Test
    public void testGetCurrentDefinitionX_twoDefinitionsPresentMovesTwice_returnsCorrectX() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionX();
        
        assertThat(result, is(3));
    } 

    @Test
    public void testGetCurrentDefinitionY_twoDefinitionsPresentMovesTwice_returnsCorrectY() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionY();
        
        assertThat(result, is(0));
    }

    @Test
    public void testGetCurrentDefinitionZ_twoDefinitionsPresentMovesTwice_returnsCorrectZ() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        testCandidate.moveToNextCollisionDefinition();
        testCandidate.moveToNextCollisionDefinition();
        
        int result = testCandidate.getCurrentDefinitionZ();
        
        assertThat(result, is(2));
    }   
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionWidth_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionWidth();
    }    
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionLength_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionLength();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionHeight_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionHeight();
    }

    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionX_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionX();
    }   
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionY_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionY();
    }  
    
    @Test(expected = IllegalStateException.class)
    public void testGetCurrentDefinitionZ_moveToNextDefinitionWasNotCalled_throwsException() {
        MapCollisionDefinitions testCandidate = new MapCollisionDefinitions();
        testCandidate.addNewCollisionDefinitionWidthLengthHeightXyz(3, 1, 0, 1, 0, 1);
        
        testCandidate.getCurrentDefinitionZ();
    }    
}