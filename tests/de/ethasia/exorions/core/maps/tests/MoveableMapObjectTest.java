package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.core.maps.MoveableMapObject;
import de.ethasia.exorions.core.maps.NoMapToMoveOnException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class MoveableMapObjectTest {
    
    @Test
    public void testPlaceOnMapWithPosition_getPosition_positionIsSameAsSetOn() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)4, (short)3, (short)5);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(4));
        assertThat(posY, is(3));
        assertThat(posZ, is(5));        
    }
    
    @Test
    public void testPlaceOnMapWithPosition_positionIsColliding_doesNotPlace() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)2, (short)1, (short)5);
        
        testCandidate.placeOnMapWithPosition(map, (short)2, (short)1, (short)5);
        InteriorMap mapPlacedOn = testCandidate.getMapPlacedOn();
        
        assertThat(mapPlacedOn, is(nullValue()));
    }    

    @Test(expected = NoMapToMoveOnException.class)
    public void testMoveTo_noMapIsSet_throwsException() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        
        testCandidate.moveTo(MoveDirections.RIGHT);
    }
    
    @Test
    public void testMoveTo_mapIsSetAndCanMoveUp_movesOneTileUp() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)0, (short)0, (short)3);
        testCandidate.moveTo(MoveDirections.UP);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(0));
        assertThat(posY, is(0));
        assertThat(posZ, is(2));
    }    
    
    @Test
    public void testMoveTo_mapIsSetAndCanMoveToRight_movesOneTileToRight() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)0, (short)0, (short)0);
        testCandidate.moveTo(MoveDirections.RIGHT);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(1));
        assertThat(posY, is(0));
        assertThat(posZ, is(0));
    }
    
    @Test
    public void testMoveTo_mapIsSetAndCanMoveDown_movesOneTileDown() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)0, (short)0, (short)0);
        testCandidate.moveTo(MoveDirections.DOWN);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(0));
        assertThat(posY, is(0));
        assertThat(posZ, is(1));
    }

    @Test
    public void testMoveTo_mapIsSetAndCanMoveLeft_movesOneTileLeft() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)2, (short)0, (short)0);
        testCandidate.moveTo(MoveDirections.LEFT);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(1));
        assertThat(posY, is(0));
        assertThat(posZ, is(0));
    }  
    
    @Test
    public void testMoveTo_mapIsSetAndCannotMoveUp_staysInPlace() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)2);
        
        testCandidate.placeOnMapWithPosition(map, (short)3, (short)0, (short)3);
        testCandidate.moveTo(MoveDirections.UP);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(3));
        assertThat(posY, is(0));
        assertThat(posZ, is(3));
    }
    
    @Test
    public void testMoveTo_mapIsSetAndCannotMoveRight_staysInPlace() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)6, (short)0, (short)4);
        
        testCandidate.placeOnMapWithPosition(map, (short)5, (short)0, (short)4);
        testCandidate.moveTo(MoveDirections.RIGHT);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(5));
        assertThat(posY, is(0));
        assertThat(posZ, is(4));
    }  
    
    @Test
    public void testMoveTo_mapIsSetAndCannotMoveDown_staysInPlace() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        
        testCandidate.placeOnMapWithPosition(map, (short)1, (short)0, (short)5);
        testCandidate.moveTo(MoveDirections.DOWN);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(1));
        assertThat(posY, is(0));
        assertThat(posZ, is(5));
    } 
    
    @Test
    public void testMoveTo_mapIsSetAndCannotMoveLeft_staysInPlace() {
        MoveableMapObject testCandidate = new MoveableMapObject();
        InteriorMap map = new InteriorMap((short)8, (short)6);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)7, (short)2);
        
        testCandidate.placeOnMapWithPosition(map, (short)1, (short)7, (short)2);
        testCandidate.moveTo(MoveDirections.LEFT);
        
        int posX = testCandidate.getPositionX();
        int posY = testCandidate.getPositionY();
        int posZ = testCandidate.getPositionZ();
        
        assertThat(posX, is(1));
        assertThat(posY, is(7));
        assertThat(posZ, is(2));
    }    
}