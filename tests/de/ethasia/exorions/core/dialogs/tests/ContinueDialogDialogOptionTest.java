package de.ethasia.exorions.core.dialogs.tests;

import de.ethasia.exorions.core.dialogs.DialogEndnode;
import de.ethasia.exorions.core.dialogs.ContinueDialogDialogOption;
import de.ethasia.exorions.core.dialogs.DialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContinueDialogDialogOptionTest {

    @Test
    public void testBuilder_getFollowUpNode_followUpDialogIsSameAsSet() {    
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption testCandidate = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Exorions")
            .build();
        
        assertThat(testCandidate.getFollowUpNode(), is(sameInstance(endnode)));
    }
    
    @Test
    public void testBuilder_getText_textIsSameAsSet() {
        DialogEndnode endnode = new DialogEndnode("??? Profit!");
        
        ContinueDialogDialogOption testCandidate = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Suriver City")
            .build();
        
        assertThat(testCandidate.getText(), is(equalTo("About Suriver City")));        
    }
    
    @Test
    public void testBuilder_setFollowUpNodeAsDialogWithOptions_works() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        ContinueDialogDialogOption followUpOptionTwo = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions followUpNode = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();
        
        ContinueDialogDialogOption testCandidate = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(followUpNode)
            .setText("About Suriver City")
            .build();         
        
        assertThat(testCandidate.getFollowUpNode(), is(sameInstance(followUpNode)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_followUpNodeIsNotSet_throwsException() {
        ContinueDialogDialogOption.Builder testCandidate = new ContinueDialogDialogOption.Builder();
        
        ContinueDialogDialogOption product = testCandidate.setText("About this planet").build();
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_textIsNotSet_throwsException() {
        ContinueDialogDialogOption.Builder testCandidate = new ContinueDialogDialogOption.Builder();
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption product = testCandidate
            .setFollowUpNode(endnode)
            .build();
    } 
    
    @Test
    public void testImplementsDialogOption() {
        DialogEndnode endnode = new DialogEndnode("See you again");
        
        ContinueDialogDialogOption testCandidate = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("I have to go")
            .build(); 
        
        boolean candidateImplementsDialogNode = testCandidate instanceof DialogOption;
        
        assertThat(candidateImplementsDialogNode, is(true));
    }
    
    @Test
    public void testEndsCurrentDialog_returnsFalse() {
        DialogEndnode endnode = new DialogEndnode("See you again");
        
        ContinueDialogDialogOption testCandidate = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("I have to go")
            .build(); 

        assertThat(testCandidate.endsCurrentDialog(), is(false)); 
    }    
}