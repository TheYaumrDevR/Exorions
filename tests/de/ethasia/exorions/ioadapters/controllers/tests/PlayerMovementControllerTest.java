package de.ethasia.exorions.ioadapters.controllers.tests;

import de.ethasia.exorions.ioadapters.UseCasesFactoryIoAdapters;
import de.ethasia.exorions.ioadapters.controllers.PlayerMovementController;
import de.ethasia.exorions.ioadapters.controllers.mocks.PlayerMovementUseCaseMock;
import de.ethasia.exorions.ioadapters.controllers.mocks.UseCasesMockFactoryIoAdapters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerMovementControllerTest {
    
    @BeforeClass
    public static void initDependencies() {
        UseCasesFactoryIoAdapters.setInstance(new UseCasesMockFactoryIoAdapters());
    }
    
    @Before
    public void resetSharedStates() {
        PlayerMovementUseCaseMock.resetCallCounts();
    }
    
    @Test
    public void testMoveDown_callsPlayerMovementUseCase() {
        PlayerMovementController testCandidate = new PlayerMovementController();
        
        assertThat(PlayerMovementUseCaseMock.getMoveDownCallCount(), is(0));
        testCandidate.moveDown();
        
        assertThat(PlayerMovementUseCaseMock.getMoveDownCallCount(), is(1));
    }
    
    @Test
    public void testMoveRight_callsPlayerMovementUseCase() {
        PlayerMovementController testCandidate = new PlayerMovementController();
        
        assertThat(PlayerMovementUseCaseMock.getMoveRightCallCount(), is(0));
        testCandidate.moveRight();
        
        assertThat(PlayerMovementUseCaseMock.getMoveRightCallCount(), is(1));
    }
    
    @Test
    public void testMoveUp_callsPlayerMovementUseCase() {
        PlayerMovementController testCandidate = new PlayerMovementController();
        
        assertThat(PlayerMovementUseCaseMock.getMoveUpCallCount(), is(0));
        testCandidate.moveUp();
        
        assertThat(PlayerMovementUseCaseMock.getMoveUpCallCount(), is(1));
    }
    
    @Test
    public void testMoveLeft_callsPlayerMovementUseCase() {
        PlayerMovementController testCandidate = new PlayerMovementController();
        
        assertThat(PlayerMovementUseCaseMock.getMoveLeftCallCount(), is(0));
        testCandidate.moveLeft();
        
        assertThat(PlayerMovementUseCaseMock.getMoveLeftCallCount(), is(1));
    }
}