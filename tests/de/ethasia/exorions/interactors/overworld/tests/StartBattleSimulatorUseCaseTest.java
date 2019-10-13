package de.ethasia.exorions.interactors.overworld.tests;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.interactors.overworld.StartBattleSimulatorUseCase;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import static org.junit.Assert.*;

public class StartBattleSimulatorUseCaseTest {

    @Test
    public void testImplementsInteractionTileUseCase() {
        StartBattleSimulatorUseCase testCandidate = new StartBattleSimulatorUseCase();
        
        boolean testCandidateImplementsInteractionTileUseCase = testCandidate instanceof InteractionTileUseCase;
        
        assertThat(testCandidateImplementsInteractionTileUseCase, is(true));
    }
}