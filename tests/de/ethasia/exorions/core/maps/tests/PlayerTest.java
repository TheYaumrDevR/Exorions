package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.maps.AddHoloWatchMessageTileTrigger;
import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.core.maps.MoveableMapObject;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.core.maps.TriggerTileType;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testThatPlayerIsAMoveableMapObject() {
        Player testCandidate = Player.getInstance();
        
        boolean playerIsInstanceOfMoveableMapObject = testCandidate instanceof MoveableMapObject;
        assertThat(playerIsInstanceOfMoveableMapObject, is(true));
    }
    
    @Test
    public void testThatPlayerIsASingleton() {
        Player testCandidateOne = Player.getInstance();
        Player testCandidateTwo = Player.getInstance();
        
        assertThat(testCandidateOne, is(sameInstance(testCandidateTwo)));
    }
    
    @Test
    public void testReceiveHoloWatchMessage_getAllMessages_messageIsContainedInMessageBox() {
        Player testCandidate = Player.getInstance();
        
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        
        testCandidate.clearHoloWatchMessages();
        testCandidate.receiveHoloWatchMessage(message);
        List<HoloWatchMessage> playerMessages = testCandidate.getAllMessages();
        
        assertThat(playerMessages, is(not(nullValue())));
        assertThat(playerMessages.size(), is(1));
        assertThat(playerMessages, hasItems(message));
    }
    
    @Test
    public void testReceiveHoloWatchMessage_getAllMessages_messagesAreSortedWithNewestFirst() {
        Player testCandidate = Player.getInstance();
        
        Date now = new Date();
        Date oneDayAgo = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24);
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        HoloWatchMessage oldMessage = new HoloWatchMessage.Builder()
            .setTitle("From Yesterday")
            .setMessageText("A message from yesterday.")
            .setSender("Random Guy")
            .setDateTimeReceived(oneDayAgo)
            .build();  
        
        testCandidate.clearHoloWatchMessages();
        testCandidate.receiveHoloWatchMessage(oldMessage);
        testCandidate.receiveHoloWatchMessage(message);
        List<HoloWatchMessage> playerMessages = testCandidate.getAllMessages();   
        
        assertThat(playerMessages.size(), is(2));
        assertThat(playerMessages.get(0), is(message));        
    }
   
    @Test
    public void testMoveTo_movesIntoTriggerTile_triggerIsExecuted() throws InterruptedException {
        // Arrange
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        AddHoloWatchMessageTileTrigger tileTrigger = new AddHoloWatchMessageTileTrigger(message);
        TriggerTileType triggerTile = new TriggerTileType(tileTrigger);   
        
        InteriorMap map = new InteriorMap((short)4, (short)4);
        map.setTileTypeAt(triggerTile, (short)1, (short)0, (short)0);
        Player testCandidate = Player.getInstance();
        testCandidate.clearHoloWatchMessages();
        
        // Act
        Thread.sleep(400);
        testCandidate.placeOnMapWithPosition(map, (short)0, (short)0, (short)0);
        testCandidate.moveTo(MoveDirections.RIGHT);

        // Assert
        List<HoloWatchMessage> holoWatchMessages = testCandidate.getAllMessages();
        assertThat(holoWatchMessages, hasItems(message));        
    }
    
    @Test
    public void testPlaceOnMapWithPosition_placedIntoAirAndFallsOnTriggerTile_triggerIsExecuted() {
        // Arrange
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();
        AddHoloWatchMessageTileTrigger tileTrigger = new AddHoloWatchMessageTileTrigger(message);
        TriggerTileType triggerTile = new TriggerTileType(tileTrigger);   
        
        InteriorMap map = new InteriorMap((short)4, (short)4);
        map.setTileTypeAt(triggerTile, (short)2, (short)0, (short)2);
        Player testCandidate = Player.getInstance();
        testCandidate.clearHoloWatchMessages(); 
        
        // Act
        testCandidate.placeOnMapWithPosition(map, (short)2, (short)5, (short)2);
        
        // Assert
        List<HoloWatchMessage> holoWatchMessages = testCandidate.getAllMessages();
        assertThat(holoWatchMessages, hasItems(message));         
    }    
    
    @Test
    public void testMoveTo_playerIsBusy_moveToHasNoEffect() throws InterruptedException {
        Player testCandidate = Player.getInstance();
        testCandidate.setIsBusy(true);
        InteriorMap map = new InteriorMap((short)4, (short)4);
        testCandidate.placeOnMapWithPosition(map, (short)1, (short)0, (short)3);
        
        Thread.sleep(400);
        testCandidate.moveTo(MoveDirections.UP);
        
        assertThat(testCandidate.getPositionX(), is((short)1));
        assertThat(testCandidate.getPositionY(), is((short)0));
        assertThat(testCandidate.getPositionZ(), is((short)3));
    }
    
    @Test
    public void testWillMoveTo_playerIsBusy_returnsFalse() throws InterruptedException {
        Player testCandidate = Player.getInstance();
        testCandidate.setIsBusy(true);
        InteriorMap map = new InteriorMap((short)4, (short)4);
        testCandidate.placeOnMapWithPosition(map, (short)1, (short)0, (short)0);

        Thread.sleep(400);
        boolean playerWillMoveTo = testCandidate.willMoveTo(MoveDirections.DOWN);
        
        assertThat(playerWillMoveTo, is(false));
    }
    
    @Test
    public void testSetIsBusy_busyStatusIsAddedAndRemoved_canMoveAgain() throws InterruptedException {
        Player testCandidate = Player.getInstance();
        testCandidate.setIsBusy(true);
        testCandidate.setIsBusy(false);
        InteriorMap map = new InteriorMap((short)4, (short)4);
        testCandidate.placeOnMapWithPosition(map, (short)2, (short)0, (short)1);
        
        Thread.sleep(400);
        testCandidate.moveTo(MoveDirections.RIGHT);
        
        assertThat(testCandidate.getPositionX(), is((short)3));
        assertThat(testCandidate.getPositionY(), is((short)0));
        assertThat(testCandidate.getPositionZ(), is((short)1));        
    }
}