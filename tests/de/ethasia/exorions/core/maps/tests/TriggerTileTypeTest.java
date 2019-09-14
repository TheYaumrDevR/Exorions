package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.maps.AddHoloWatchMessageTileTrigger;
import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.core.maps.ShowNotificationBoxTileTrigger;
import de.ethasia.exorions.core.maps.TriggerTile;
import de.ethasia.exorions.core.maps.World;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TriggerTileTypeTest {
        
    @Test
    public void testIsGround_returnsFalse() {
        TriggerTile testCandidate = new TriggerTile();
        boolean isGround = testCandidate.isGround();
        
        assertThat(isGround, is(false));
    }
    
    @Test
    public void testIsColliding_returnsFalse() {
        TriggerTile testCandidate = new TriggerTile();
        boolean isCollidingTile = testCandidate.isCollidingTile();
        
        assertThat(isCollidingTile, is(false));
    }
    
    @Test
    public void testOnSteppedOn_addHoloWatchMessageTileTriggerIsAdded_holoWatchMessageIsAdded() {
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        AddHoloWatchMessageTileTrigger tileTrigger = new AddHoloWatchMessageTileTrigger(message);
        
        TriggerTile testCandidate = new TriggerTile(tileTrigger);

        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        testCandidate.onSteppedOn();
        
        List<HoloWatchMessage> holoWatchMessages = player.getAllMessages();
        assertThat(holoWatchMessages, hasItems(message));
    }
    
    @Test
    public void testOnSteppedOn_noTileTriggerIsPresent_nothingHappens() {
        TriggerTile testCandidate = new TriggerTile();
        
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        testCandidate.onSteppedOn();
        
        List<HoloWatchMessage> holoWatchMessages = player.getAllMessages();
        assertThat(holoWatchMessages.size(), is(0));
    }    
    
    @Test
    public void testCreateWithAddMessageTrigger_playerWalksOver_tileTriggerIsExecuted() {
        // Allocate
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        AddHoloWatchMessageTileTrigger tileTrigger = new AddHoloWatchMessageTileTrigger(message);
        
        TriggerTile testCandidate = new TriggerTile(tileTrigger);         
        
        World world = new World();
        InteriorMap map = new InteriorMap((short)10, (short)10);
        map.setTileTypeAt(testCandidate, (short)3, (short)0, (short)2);
        
        world.placeInteriorMapWithIdentifier("MapOne", map);
        world.setActiveMapWithPlayerPosition("MapOne", (short)3, (short)0, (short)1);

        // Act
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        player.moveTo(MoveDirections.DOWN);
        
        // Assert
        List<HoloWatchMessage> holoWatchMessages = player.getAllMessages();
        assertThat(holoWatchMessages, hasItems(message));
    }
    
    @Test
    public void testParameterlessConstructor_playerWalksOver_nothingHappens() {
        TriggerTile testCandidate = new TriggerTile();         
        
        World world = new World();
        InteriorMap map = new InteriorMap((short)10, (short)10);
        map.setTileTypeAt(testCandidate, (short)4, (short)0, (short)3);
        
        world.placeInteriorMapWithIdentifier("MapOne", map);
        world.setActiveMapWithPlayerPosition("MapOne", (short)4, (short)0, (short)2);

        // Act
        Player player = Player.getInstance();
        player.clearHoloWatchMessages();
        player.moveTo(MoveDirections.DOWN);
        
        // Assert
        List<HoloWatchMessage> holoWatchMessages = player.getAllMessages();
        assertThat(holoWatchMessages.size(), is(0));        
    }
    
    @Test
    public void testConstructor_canAddShowNotificationBoxTileTrigger() {
        ShowNotificationBoxTileTrigger tileTrigger = new ShowNotificationBoxTileTrigger();
        TriggerTile testCandidate = new TriggerTile(tileTrigger);
    }
}