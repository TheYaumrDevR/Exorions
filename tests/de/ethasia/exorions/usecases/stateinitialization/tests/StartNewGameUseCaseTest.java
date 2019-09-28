package de.ethasia.exorions.usecases.stateinitialization.tests;

import de.ethasia.exorions.usecases.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.usecases.crosslayer.MapLogicCouldNotBeLoadedException;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;
import de.ethasia.exorions.usecases.mocks.FatalErrorPresenterMock;
import de.ethasia.exorions.usecases.mocks.MockPresentersFactory;
import de.ethasia.exorions.usecases.mocks.OverworldStatePresenterMock;
import de.ethasia.exorions.usecases.stateinitialization.StartNewGameUseCase;
import static org.hamcrest.CoreMatchers.equalTo;

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
        FatalErrorPresenterMock.emptyLastSetFields();
    }

    @Test
    public void testStartNewGame_initOverworldStateWithMapIsCalled() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();   
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(0));
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(1));
    }
    
    @Test
    public void testStartNewGame_errorHappensWhenTryingToLoadMapsMetadata_errorWindowIsShownAndStateIsNotChanged() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();  
        InformationForMapsCouldNotBeLoadedException internalException = new InformationForMapsCouldNotBeLoadedException(
            "Parsing the XML failed. It might be corrupted.", 
            "Stacktrace blabla");  
        
        OverworldStatePresenterMock.setNextExceptionThrownOnMethodCall(internalException);
        
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(0));
        assertThat(FatalErrorPresenterMock.getLastShownError(), is(equalTo(internalException.getErrorMessage())));
        assertThat(FatalErrorPresenterMock.getLastShownErrorCause(), is(equalTo(internalException.getErrorCause())));
        assertThat(FatalErrorPresenterMock.getLastShownStackTrace(), is(equalTo(internalException.getStackTraceString())));
    }

    @Test
    public void testStartNewGame_errorHappensWhenTryingToLoadFirstMap_errorWindowIsShownAndStateIsNotChanged() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();  
        MapLogicCouldNotBeLoadedException internalException = new MapLogicCouldNotBeLoadedException(
                "Loading the file failed. It might not exist or the game might not have access. Affected map: None", 
                "Stracktrace with methods"); 
        
        OverworldStatePresenterMock.setNextExceptionThrownOnMethodCall(internalException);
        
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithNewGameMapCallCounter(), is(0));
        assertThat(FatalErrorPresenterMock.getLastShownError(), is(equalTo(internalException.getErrorMessage())));
        assertThat(FatalErrorPresenterMock.getLastShownErrorCause(), is(equalTo(internalException.getErrorCause())));
        assertThat(FatalErrorPresenterMock.getLastShownStackTrace(), is(equalTo(internalException.getStackTraceString())));
    }     
}