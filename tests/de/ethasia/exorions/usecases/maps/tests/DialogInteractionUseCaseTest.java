package de.ethasia.exorions.usecases.maps.tests;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.core.dialogs.DialogEndnode;
import de.ethasia.exorions.core.dialogs.ContinueDialogDialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.usecases.maps.DialogInteractionUseCase;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class DialogInteractionUseCaseTest {

    @Test
    public void testImplementsInteractionTileUseCase() {
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(createDialogWithOptionsForTesting());
        
        boolean implementsInteractionTileUseCase = testCandidate instanceof InteractionTileUseCase;
        
        assertThat(implementsInteractionTileUseCase, is(true));
    }
    
    @Test
    public void testStartInteraction_blocksPlayer() {
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(createDialogWithOptionsForTesting());
        
        testCandidate.startInteraction();
        
        boolean playerIsBusy = Player.getInstance().isBusy();
        assertThat(playerIsBusy, is(true));
    }
    
    @Test
    public void testCancelInteraction_playerIsUnblocked() {
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(createDialogWithOptionsForTesting());
        
        testCandidate.cancelInteraction();
        
        boolean playerIsBusy = Player.getInstance().isBusy();
        assertThat(playerIsBusy, is(false));
    }

    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testStartInteraction_dialogIsNotSet_throwsException() {
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(null);
        
        testCandidate.startInteraction();
    }
    
    @Test
    public void testStartInteraction_showsDialogWindowAndInitializesButtonsWithHandlers() {
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(createDialogWithOptionsForTesting());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private DialogWithOptions createDialogWithOptionsForTesting() {
        DialogEndnode endnode = new DialogEndnode("Make your time.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About the Planet")
            .build(); 
        
        ContinueDialogDialogOption followUpOptionTwo = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("About Controllers")
            .build();          
        
        DialogWithOptions result = new DialogWithOptions.Builder()
            .setText("I really don't feel like talking.")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(followUpOptionTwo)
            .build();
        
        return result;
    }
    
    //</editor-fold>
}