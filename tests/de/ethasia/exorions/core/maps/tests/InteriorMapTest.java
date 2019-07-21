package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.general.SetValueIsNotWithinLegalBoundsException;
import de.ethasia.exorions.core.maps.InteriorMap;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class InteriorMapTest {

    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testCreate_withNegativeDimensionX_throwsException() {
        InteriorMap testCandidate = new InteriorMap((short)-1, (short)49);
    }
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testCreate_withNegativeDimensionZ_throwsException() {
        InteriorMap testCandidate = new InteriorMap((short)35, (short)-1);
    }
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testCreate_withTooBigDimensionX_throwsException() {
        InteriorMap testCandidate = new InteriorMap((short)51, (short)7);
    }
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testCreate_withTooBigDimensionZ_throwsException() {
        InteriorMap testCandidate = new InteriorMap((short)43, (short)51);
    }
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfBoundsX_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)50, (short)50);
        
        boolean result = testCandidate.tileAtIsColliding((short)51, (short)1, (short)49);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfBoundsXNegative_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)50, (short)50);
        
        boolean result = testCandidate.tileAtIsColliding((short)-1, (short)1, (short)49);
        
        assertThat(result, is(true));
    }    
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfDimensionBoundsInX_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        boolean result = testCandidate.tileAtIsColliding((short)26, (short)17, (short)12);
        
        assertThat(result, is(true));
    }     
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfBoundsY_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)50, (short)50);
        
        boolean result = testCandidate.tileAtIsColliding((short)37, (short)4, (short)51);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfBoundsYInNegative_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)50, (short)50);
        
        boolean result = testCandidate.tileAtIsColliding((short)24, (short)11, (short)-1);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testTileAtIsColliding_tileIsOutsideOfDimensionBoundsInY_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        boolean result = testCandidate.tileAtIsColliding((short)24, (short)5, (short)26);
        
        assertThat(result, is(true));
    }    
    
    @Test
    public void testTileAtIsColliding_tileIsAboveMaxHeight_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)10, (short)10);
        
        boolean result = testCandidate.tileAtIsColliding((short)10, (short)26, (short)2);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testTileAtIsColliding_tileIsBelowGround_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)10, (short)10);
        
        boolean result = testCandidate.tileAtIsColliding((short)4, (short)-1, (short)6);
        
        assertThat(result, is(true));
    }   
    
    @Test
    public void testTileAtIsColliding_tileIsWithinBounds_returnsTrue() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        boolean result = testCandidate.tileAtIsColliding((short)25, (short)25, (short)25);
        
        assertThat(result, is(false));
    }
}
