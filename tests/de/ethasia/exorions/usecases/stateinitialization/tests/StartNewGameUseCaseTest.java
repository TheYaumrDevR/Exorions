package de.ethasia.exorions.usecases.stateinitialization.tests;

import de.ethasia.exorions.usecases.interfaces.PresentersFactory;
import de.ethasia.exorions.usecases.mocks.MockPresentersFactory;
import de.ethasia.exorions.usecases.mocks.OverworldStatePresenterMock;
import de.ethasia.exorions.usecases.stateinitialization.StartNewGameUseCase;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

public class StartNewGameUseCaseTest {
    
    @BeforeClass
    public static void initDependencies() {
        PresentersFactory.setInstance(new MockPresentersFactory());
    }  
    
    @Before
    public void resetSharedState() {
        OverworldStatePresenterMock.emptyLastSetFields();
    }

    @Test
    public void testStartNewGame_initOverworldStateWithMapIsCalled() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();   
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(0));
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(1));
    }
    
    @Test
    public void testStartNewGame_errorHappensWhenTryingToLoadMap_errorWindowIsShownAndStateIsNotChanged() {
        
    }    
}