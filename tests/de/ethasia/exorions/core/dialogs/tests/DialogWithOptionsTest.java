package de.ethasia.exorions.core.dialogs.tests;

import de.ethasia.exorions.core.dialogs.DialogEndnode;
import de.ethasia.exorions.core.dialogs.DialogNode;
import de.ethasia.exorions.core.dialogs.ContinueDialogDialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.dialogs.StartBattleSimulatorDialogOption;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.interfaces.UseCasesFactory;
import de.ethasia.exorions.core.mocks.UseCasesMockFactory;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import static org.hamcrest.CoreMatchers.is;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DialogWithOptionsTest {
    
    @BeforeClass
    public static void initDependencies() {
        UseCasesFactory.setInstance(new UseCasesMockFactory());
    }

    @Test
    public void testBuilder_setAllProperties_productContainsAllProperties() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        ContinueDialogDialogOption followUpOptionTwo = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions testCandidate = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();  
        
        assertThat(testCandidate.getText(), is(equalTo("I really don't feel like talking.")));
        assertThat(testCandidate.getOptions().size(), is(equalTo(2)));
        assertThat(testCandidate.getOptions(), hasItems(followUpOptionOne, followUpOptionTwo));
    }
    
    @Test
    public void testThatItImplementsDialogNode() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        ContinueDialogDialogOption followUpOptionTwo = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions testCandidate = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();
        
        boolean productImplementsDialogNode = testCandidate instanceof DialogNode;
        
        assertThat(productImplementsDialogNode, is(true));
    }
    
    @Test
    public void testIsLeaf_returnsFalse() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        ContinueDialogDialogOption followUpOptionTwo = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions testCandidate = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();
        
        assertThat(testCandidate.isLeaf(), is(false));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_lessThanTwoOptionsAreGiven_throwsException() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build();         
        
        DialogWithOptions followUpNode = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .build();        
    }
    
    @Test
    public void testBuilder_addStartBattleSimulatorDialogOption_works() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build();
        
        StartBattleSimulatorDialogOption followUpOptionTwo = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();
        
        DialogWithOptions testCandidate = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();    
        
        assertThat(testCandidate.getOptions().size(), is(equalTo(2)));
        assertThat(testCandidate.getOptions(), hasItems(followUpOptionOne, followUpOptionTwo));        
    }
}