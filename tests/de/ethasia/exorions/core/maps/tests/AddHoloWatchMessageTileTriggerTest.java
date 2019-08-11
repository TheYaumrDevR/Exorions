package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.maps.AddHoloWatchMessageTileTrigger;
import de.ethasia.exorions.core.maps.Player;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AddHoloWatchMessageTileTriggerTest {

    @Test
    public void testExecute_holoWatchMessageIsSet_holoWatchMessageIsAddedToPlayerInbox() {
        Date now = new Date();
        HoloWatchMessage message = new HoloWatchMessage.Builder()
            .setTitle("Welcome to the world of Po... nevermind")
            .setMessageText("Dear Citizen, please come to the townhall asap. Your Government.")
            .setSender("Suriver City Governor")
            .setDateTimeReceived(now)
            .build();    
        
        AddHoloWatchMessageTileTrigger testCandidate = new AddHoloWatchMessageTileTrigger(message);
        testCandidate.execute();
        
        List<HoloWatchMessage> playerMessages = Player.getInstance().getAllMessages();
        
        assertThat(playerMessages.size(), is(equalTo(1)));
        assertThat(playerMessages, hasItems(message));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testExecute_noMessageIsSet_throwsException() {
        AddHoloWatchMessageTileTrigger testCandidate = new AddHoloWatchMessageTileTrigger(null);
        testCandidate.execute();        
    }
}
