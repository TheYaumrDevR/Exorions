package de.ethasia.exorions.core.holowatch.tests;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.holowatch.HoloWatchMessageBox;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HoloWatchMessageBoxTest {

    @Test
    public void testPutMessage_messagesAreRetrieved_messageIsContainedInInbox() {
        HoloWatchMessageBox testCandidate = new HoloWatchMessageBox();
        Date dateTimeReceived = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        testCandidate.putMessage(message);
        
        List<HoloWatchMessage> messages = testCandidate.getMessages();
        
        assertThat(messages.size(), is(equalTo(1)));
        assertThat(messages.get(0), is(message));
    }
    
    @Test
    public void testPutMessage_multipleMessagesArePutInRandomOrder_retrievedMessagesAreSorted() {
        Date now = new Date();
        Date oneDayAgo = new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24);
        HoloWatchMessage newMessage = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .setDateTimeReceived(now)
            .build();  
      
        HoloWatchMessage oldMessage = new HoloWatchMessage.Builder()
            .setTitle("From Yesterday")
            .setMessageText("A message from yesterday.")
            .setSender("Random Guy")
            .setDateTimeReceived(oneDayAgo)
            .build();     

        HoloWatchMessageBox testCandidate = new HoloWatchMessageBox();
        testCandidate.putMessage(oldMessage);
        testCandidate.putMessage(newMessage);
        
        List<HoloWatchMessage> messages = testCandidate.getMessages();
        
        assertThat(messages.size(), is(equalTo(2)));
        assertThat(messages.get(0), is(newMessage));
        assertThat(messages.get(1), is(oldMessage));
    }
}