package de.ethasia.exorions.interactors.overworld.tests;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.core.dialogs.DialogEndnode;
import de.ethasia.exorions.core.dialogs.ContinueDialogDialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.dialogs.StartBattleSimulatorDialogOption;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.overworld.DialogInteractionUseCase;
import de.ethasia.exorions.interactors.overworld.DialogOptionTextWithHandler;
import de.ethasia.exorions.interactors.mocks.DialogWindowPresenterMock;
import de.ethasia.exorions.interactors.mocks.MockPresentersFactory;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DialogInteractionUseCaseTest {
    
    @BeforeClass
    public static void initDependencies() {
        PresentersFactory.setInstance(new MockPresentersFactory());
    }   
    
    @Before
    public void resetSharedState() {
        DialogWindowPresenterMock.emptyLastSetFields();
    }

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
        DialogInteractionUseCase testCandidate = new DialogInteractionUseCase(createDialogWithNormalOptionAndStartBattleSimulatorOption());
        
        testCandidate.startInteraction();
        
        String presenterDialogText = DialogWindowPresenterMock.getLastSetDialogText();
        List<DialogOptionTextWithHandler> optionTextsWithHandlers = DialogWindowPresenterMock.getLastSetOptionTextWithHandlers();
        
        String optionTextOne = optionTextsWithHandlers.get(0).getText();
        String optionTextTwo = optionTextsWithHandlers.get(1).getText();
        
        assertThat(presenterDialogText, is(equalTo("Welcome. What would you like to do?")));
        assertThat(optionTextsWithHandlers.size(), is(2));
        assertThat(optionTextOne, is(equalTo("Browse the interplanet")));
        assertThat(optionTextTwo, is(equalTo("Start battle simulator")));
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
    
    private DialogWithOptions createDialogWithNormalOptionAndStartBattleSimulatorOption() {
        DialogEndnode endnode = new DialogEndnode("Cannot connect to Interplanet right now.");
        
        ContinueDialogDialogOption followUpOptionOne = new ContinueDialogDialogOption.Builder()
            .setFollowUpNode(endnode)
            .setText("Browse the interplanet")
            .build(); 
        
        StartBattleSimulatorDialogOption startBattleSimDialogOption = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();
        
        DialogWithOptions result = new DialogWithOptions.Builder()
            .setText("Welcome. What would you like to do?")
            .addDialogOption(followUpOptionOne)
            .addDialogOption(startBattleSimDialogOption)
            .build();
        
        return result;        
    }
    
    //</editor-fold>
}