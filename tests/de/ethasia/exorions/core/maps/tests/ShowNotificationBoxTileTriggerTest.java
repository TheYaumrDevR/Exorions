package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.core.maps.ShowNotificationBoxTileTrigger;
import de.ethasia.exorions.core.maps.TileTrigger;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShowNotificationBoxTileTriggerTest {

    @Test
    public void testExecute_blocksPlayerMovementWhileMessageIsShown() {
        ShowNotificationBoxTileTrigger testCandidate = new ShowNotificationBoxTileTrigger();
        testCandidate.execute();
        
        assertThat(Player.getInstance().isBusy(), is(true));
    }
    
    @Test
    public void testIsSubclassOfTileTrigger_isTrue() {
        ShowNotificationBoxTileTrigger testCandidate = new ShowNotificationBoxTileTrigger();
        boolean isSubclassOfTileTrigger = testCandidate instanceof TileTrigger;
        
        assertThat(isSubclassOfTileTrigger, is(true));
    }
}