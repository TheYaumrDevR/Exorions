package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.general.SetValueIsNotWithinLegalBoundsException;
import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.maps.AddHoloWatchMessageTileTrigger;
import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.core.maps.TriggerTileType;
import java.util.Date;
import java.util.List;
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
        
        boolean result = testCandidate.tileAtIsColliding((short)9, (short)26, (short)2);
        
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
        
        boolean result = testCandidate.tileAtIsColliding((short)24, (short)24, (short)24);
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testSetTileTypeAt_tileIsCollisionTile_collides() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        testCandidate.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)6, (short)22);
        
        boolean collides = testCandidate.tileAtIsColliding((short)3, (short)6, (short)22);
        
        assertThat(collides, is(true));
    }
    
    @Test
    public void testSetTileTypeAt_tileIsWarpTile_doesNotCollide() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        testCandidate.setTileTypeAt(MapTileTypes.WARP, (short)11, (short)0, (short)9);
        
        boolean collides = testCandidate.tileAtIsColliding((short)11, (short)0, (short)9);
        
        assertThat(collides, is(false));
    }   
    
    @Test
    public void testSetTileTypeAt_tileIsTriggerTile_doesNotCollide() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        testCandidate.setTileTypeAt(new TriggerTileType(), (short)17, (short)15, (short)23);
        
        boolean collides = testCandidate.tileAtIsColliding((short)17, (short)15, (short)23);
        
        assertThat(collides, is(false));
    }    
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testSetTileTypeAt_dimensionsAreOutsideBounds_throwsException() {
        InteriorMap testCandidate = new InteriorMap((short)25, (short)25);
        
        testCandidate.setTileTypeAt(MapTileTypes.COLLISION, (short)-1, (short)25, (short)29);        
    }
    
    @Test
    public void stepOnTileAt_triggerTileWithHoloWatchMessageIsPresent_sendsMessage() {
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        AddHoloWatchMessageTileTrigger tileTrigger = new AddHoloWatchMessageTileTrigger(message);
        TriggerTileType triggerTile = new TriggerTileType(tileTrigger);    
        
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        
        InteriorMap testCandidate = new InteriorMap((short)4, (short)4);
       
        testCandidate.setTileTypeAt(triggerTile, (short)2, (short)0, (short)3);   
        testCandidate.stepOnTileAt((short)2, (short)0, (short)3);
        
        List<HoloWatchMessage> holoWatchMessages = player.getAllMessages();
        
        assertThat(holoWatchMessages, hasItems(message));
    }
    
    @Test
    public void stepOnTileAt_tileIsNull_nothingHappens() {  
        InteriorMap testCandidate = new InteriorMap((short)4, (short)4);
        
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        
        testCandidate.stepOnTileAt((short)2, (short)0, (short)3);      
        
        assertThat(player.getAllMessages().size(), is(0));
    }
    
    @Test
    public void stepOnTileAt_positionIsOutsideBounds_nothingHappens() {
        InteriorMap testCandidate = new InteriorMap((short)4, (short)4);
        
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        
        testCandidate.stepOnTileAt((short)5, (short)0, (short)0);   
        testCandidate.stepOnTileAt((short)0, (short)1000, (short)0);  
        testCandidate.stepOnTileAt((short)0, (short)0, (short)5);  
        testCandidate.stepOnTileAt((short)-1, (short)0, (short)0);  
        testCandidate.stepOnTileAt((short)0, (short)-2, (short)0);  
        testCandidate.stepOnTileAt((short)0, (short)0, (short)-3);  
        
        assertThat(player.getAllMessages().size(), is(0));        
    }
}