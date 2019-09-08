package de.ethasia.exorions.core.dialogs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import static org.junit.Assert.*;

public class DialogEndnodeTest {

    @Test
    public void testThatItImplementsDialogNode() {
        DialogEndnode testCandidate = new DialogEndnode("Logging off...");
        boolean testCandidateIsDialogNode = testCandidate instanceof DialogNode;
        
        assertThat(testCandidateIsDialogNode, is(true));
    }
    
    @Test
    public void testGetText_returnsTextCreatedWith() {
        DialogEndnode testCandidate = new DialogEndnode("Good bye, lil boy.");
        
        String text = testCandidate.getText();
        
        assertThat(text, is(equalTo("Good bye, lil boy.")));
    }
    
    @Test
    public void testIsLeaf_returnsTrue() {
        DialogEndnode testCandidate = new DialogEndnode("Make your time.");
        
        assertThat(testCandidate.isLeaf(), is(true));
    }
}
