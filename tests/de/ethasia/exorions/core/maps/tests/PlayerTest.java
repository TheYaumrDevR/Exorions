package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.maps.MoveableMapObject;
import de.ethasia.exorions.core.maps.Player;
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
}