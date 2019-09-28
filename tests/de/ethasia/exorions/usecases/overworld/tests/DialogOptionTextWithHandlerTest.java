package de.ethasia.exorions.usecases.overworld.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.usecases.overworld.DialogOptionSelectionHandler;
import de.ethasia.exorions.usecases.overworld.DialogOptionTextWithHandler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import static org.junit.Assert.*;

public class DialogOptionTextWithHandlerTest {

    @Test
    public void testBuilder_setTextAndHandler_areContainedInProduct() {
        DialogOptionSelectionHandler handler = () -> {};
        
        DialogOptionTextWithHandler testCandidate = new DialogOptionTextWithHandler.Builder()
            .setText("This is the first option")
            .setSelectionHandler(handler)
            .build();
        
        assertThat(testCandidate.getText(), is(equalTo("This is the first option")));
        assertThat(testCandidate.getSelectionHandler(), is(handler));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_textIsNotSet_throwsException() {
        DialogOptionSelectionHandler handler = () -> {};
        
        DialogOptionTextWithHandler testCandidate = new DialogOptionTextWithHandler.Builder()
            .setSelectionHandler(handler)
            .build();       
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_handlerIsNotSet_throwsException() {
        DialogOptionSelectionHandler handler = () -> {};
        
        DialogOptionTextWithHandler testCandidate = new DialogOptionTextWithHandler.Builder()
            .setText("This is the first option")
            .build();        
    }
}