package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.core.maps.World;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void testSetActiveMapWithPlayerPosition_activeMapIsRetrieved_retrievedMapIsTheSameAsTheSetMap() {
        World testCandidate = new World();
        InteriorMap toPlace = new InteriorMap((short)5, (short)5);
        
        testCandidate.placeInteriorMapWithIdentifier("MapOne", toPlace);
        testCandidate.setActiveMapWithPlayerPosition("MapOne", (short)0, (short)0, (short)0);
        InteriorMap activeMap = testCandidate.getActiveMap();
        
        assertThat(activeMap, is(sameInstance(toPlace)));
    }
    
    @Test
    public void testSetActiveMapWithPlayerPosition_positionAndMapAreValid_playerIsPlacedOnMapAndPosition() {
        World testCandidate = new World();
        InteriorMap toPlace = new InteriorMap((short)10, (short)7);
        
        testCandidate.placeInteriorMapWithIdentifier("MapOne", toPlace);
        testCandidate.setActiveMapWithPlayerPosition("MapOne", (short)1, (short)0, (short)2);
        
        Player player = Player.getInstance();
        
        short posX = player.getPositionX();
        short posY = player.getPositionY();
        short posZ = player.getPositionZ();
        
        assertThat(posX, is(equalTo((short)1)));
        assertThat(posY, is(equalTo((short)0)));
        assertThat(posZ, is(equalTo((short)2)));
    }
    
    @Test
    public void testSetActiveMapWithPlayerPosition_positionIsColliding_mapIsNotSwitched() {
        World testCandidate = new World();
        InteriorMap toPlace = new InteriorMap((short)10, (short)7);
        toPlace.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)3);
        
        testCandidate.placeInteriorMapWithIdentifier("MapOne", toPlace);
        testCandidate.setActiveMapWithPlayerPosition("MapOne", (short)3, (short)0, (short)3);
        
        InteriorMap activeMap = testCandidate.getActiveMap();
        
        assertThat(activeMap, is(nullValue()));
    }
}
