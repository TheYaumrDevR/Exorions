package de.ethasia.exorions.core.dialogs.tests;

import de.ethasia.exorions.core.dialogs.DialogEndnode;
import de.ethasia.exorions.core.dialogs.DialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DialogOptionTest {

    @Test
    public void testBuilder_getFollowUpNode_followUpDialogIsSameAsSet() {    
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        DialogOption testCandidate = new DialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Exorions")
            .build();
        
        assertThat(testCandidate.getFollowUpNode(), is(sameInstance(endnode)));
    }
    
    @Test
    public void testBuilder_getText_textIsSameAsSet() {
        DialogEndnode endnode = new DialogEndnode("??? Profit!");
        
        DialogOption testCandidate = new DialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Suriver City")
            .build();
        
        assertThat(testCandidate.getText(), is(equalTo("About Suriver City")));        
    }
    
    @Test
    public void testBuilder_setFollowUpNodeAsDialogWithOptions_works() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        DialogOption followUpOptionOne = new DialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        DialogOption followUpOptionTwo = new DialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions followUpNode = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();
        
        DialogOption testCandidate = new DialogOption.Builder()
            .setFollowUpNode(followUpNode)
            .setText("About Suriver City")
            .build();         
        
        assertThat(testCandidate.getFollowUpNode(), is(sameInstance(followUpNode)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_followUpNodeIsNotSet_throwsException() {
        DialogOption.Builder testCandidate = new DialogOption.Builder();
        
        DialogOption product = testCandidate.setText("About this planet").build();
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_textIsNotSet_throwsException() {
        DialogOption.Builder testCandidate = new DialogOption.Builder();
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        DialogOption product = testCandidate
            .setFollowUpNode(endnode)
            .build();
    }    
}