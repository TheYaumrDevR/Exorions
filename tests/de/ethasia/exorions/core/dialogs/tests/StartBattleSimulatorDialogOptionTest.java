package de.ethasia.exorions.core.dialogs.tests;

import de.ethasia.exorions.core.dialogs.DialogOption;
import de.ethasia.exorions.core.dialogs.StartBattleSimulatorDialogOption;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.interfaces.UseCasesFactory;
import de.ethasia.exorions.core.mocks.StartBattleSimulatorUseCaseMock;
import de.ethasia.exorions.core.mocks.UseCasesMockFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StartBattleSimulatorDialogOptionTest {
    
    @BeforeClass
    public static void initDependencies() {
        UseCasesFactory.setInstance(new UseCasesMockFactory());
    }
    
    @Before
    public void resetSharedState() {
        StartBattleSimulatorUseCaseMock.resetMethodCallCounters();
    }

    @Test
    public void testBuilder_getText_textIsSameAsSet() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();
        
        assertThat(testCandidate.getText(), is(equalTo("Start battle simulator")));
    } 
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_textIsSetToEmpty_throwsException() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText("")
            .build();        
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilder_textIsSetToNull_throwsException() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText(null)
            .build();        
    }
    
    @Test
    public void testImplementsDialogOption() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();
        
        boolean candidateImplementsDialogOption = testCandidate instanceof DialogOption;
        
        assertThat(candidateImplementsDialogOption, is(true));
    }
    
    @Test
    public void testEndsCurrentDialog_returnsTrue() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();

        assertThat(testCandidate.endsCurrentDialog(), is(true));
    }
    
    @Test
    public void testExecuteAnyCustomUseCase_startBattleSimulatorUseCaseIsCalled() {
        StartBattleSimulatorDialogOption testCandidate = new StartBattleSimulatorDialogOption.Builder()
            .setText("Start battle simulator")
            .build();
        
        assertThat(StartBattleSimulatorUseCaseMock.getStartInteractionCallCountSinceLastReset(), is(0));

        testCandidate.executeAnyCustomUseCase();
        
        assertThat(StartBattleSimulatorUseCaseMock.getStartInteractionCallCountSinceLastReset(), is(1));
    }
}