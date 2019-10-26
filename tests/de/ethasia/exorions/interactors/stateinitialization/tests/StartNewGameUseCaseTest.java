package de.ethasia.exorions.interactors.stateinitialization.tests;

import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.mocks.FatalErrorPresenterMock;
import de.ethasia.exorions.interactors.mocks.MapMetaDataGatewayMock;
import de.ethasia.exorions.interactors.mocks.MockGatewaysFactory;
import de.ethasia.exorions.interactors.mocks.MockPresentersFactory;
import de.ethasia.exorions.interactors.mocks.OverworldStatePresenterMock;
import de.ethasia.exorions.interactors.stateinitialization.StartNewGameUseCase;
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
        GatewaysFactory.setInstance(new MockGatewaysFactory());
    }  
    
    @Before
    public void resetSharedState() {
        OverworldStatePresenterMock.emptyLastSetFields();
        FatalErrorPresenterMock.emptyLastSetFields();
    }

    @Test
    public void testStartNewGame_initOverworldStateWithMapIsCalled() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();   
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithMapFromMetaDataCallCounter(), is(0));
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithMapFromMetaDataCallCounter(), is(1));
    }
    
    @Test
    public void testStartNewGame_errorHappensWhenTryingToLoadMapsMetadata_errorWindowIsShownAndStateIsNotChanged() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();  
        InformationForMapsCouldNotBeLoadedException internalException = new InformationForMapsCouldNotBeLoadedException(
            "Parsing the XML failed. It might be corrupted.", 
            "Stacktrace blabla");  
        
        OverworldStatePresenterMock.setNextExceptionThrownOnMethodCall(internalException);
        
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithMapFromMetaDataCallCounter(), is(0));
        assertThat(FatalErrorPresenterMock.getLastShownError(), is(equalTo(internalException.getErrorMessage())));
        assertThat(FatalErrorPresenterMock.getLastShownErrorCause(), is(equalTo(internalException.getErrorCause())));
        assertThat(FatalErrorPresenterMock.getLastShownStackTrace(), is(equalTo(internalException.getStackTraceString())));
    }

    @Test
    public void testStartNewGame_errorHappensWhenTryingToLoadFirstMap_errorWindowIsShownAndStateIsNotChanged() {
        StartNewGameUseCase testCandidate = new StartNewGameUseCase();  
        MapDataCouldNotBeLoadedException internalException = new MapDataCouldNotBeLoadedException(
                "Loading the file failed. It might not exist or the game might not have access. Affected map: None", 
                "Stracktrace with methods"); 
        
        MapMetaDataGatewayMock.setNextExceptionToThrow(internalException);
        
        testCandidate.startNewGame();
        
        assertThat(OverworldStatePresenterMock.getPresentOverworldWithMapFromMetaDataCallCounter(), is(0));
        assertThat(FatalErrorPresenterMock.getLastShownError(), is(equalTo(internalException.getErrorMessage())));
        assertThat(FatalErrorPresenterMock.getLastShownErrorCause(), is(equalTo(internalException.getErrorCause())));
        assertThat(FatalErrorPresenterMock.getLastShownStackTrace(), is(equalTo(internalException.getStackTraceString())));
    }     
}