package de.ethasia.exorions.interactors.overworld.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.MapTileTypes;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.interactors.crosslayer.PlayerMovementUseCase;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.mocks.MockPresentersFactory;
import de.ethasia.exorions.interactors.overworld.PlayerMovementUseCaseImpl;
import de.ethasia.exorions.interactors.mocks.PlayerAvatarMovementPresenterMock;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerMovementUseCaseImplTest {
    
    private static InteriorMap map;
    
    @BeforeClass
    public static void initDependencies() {
        map = new InteriorMap((short)6, (short)6);
        
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)0);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)1, (short)0, (short)0);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)2, (short)0, (short)0);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)0);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)4, (short)0, (short)0);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)0);
        
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)1);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)1, (short)0, (short)1);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)2, (short)0, (short)1);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)1);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)4, (short)0, (short)1);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)1);

        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)2);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)2);   
        
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)3);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)4, (short)0, (short)3);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)3);
        
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)4);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)4);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)4, (short)0, (short)4);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)4);

        map.setTileTypeAt(MapTileTypes.COLLISION, (short)0, (short)0, (short)5);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)2, (short)0, (short)5);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)3, (short)0, (short)5);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)4, (short)0, (short)5);
        map.setTileTypeAt(MapTileTypes.COLLISION, (short)5, (short)0, (short)5);
        
        PresentersFactory.setInstance(new MockPresentersFactory());
    }
    
    @Before
    public void resetSharedState() {
        PlayerAvatarMovementPresenterMock.resetCallData();
    }
    
    @Test
    public void testImplementsPlayerMovementUseCase() {
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        
        boolean isPlayerMovementUseCase = testCandidate instanceof PlayerMovementUseCase;
        
        assertThat(isPlayerMovementUseCase, is(true));
    }
    
    @Test
    public void testMoveDown_playerIsOnMap_movesPlayerDownAndUpdatesVisualState() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)2, (short)0, (short)3);   
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveDown();
        
        assertThat(player.getPositionZ(), is((short)4));
        assertThat(PlayerAvatarMovementPresenterMock.getMoveDownCallCount(), is(1));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosX(), is(2));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosY(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosZ(), is(4));
    }
    
    @Test
    public void testMoveRight_playerIsOnMap_movesPlayerRightAndUpdatesVisualState() throws InterruptedException {        
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)2, (short)0, (short)3);   
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveRight();
        
        assertThat(player.getPositionX(), is((short)3));
        assertThat(PlayerAvatarMovementPresenterMock.getMoveRightCallCount(), is(1));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosX(), is(3));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosY(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosZ(), is(3));        
    }
    
    @Test
    public void testMoveUp_playerIsOnMap_movesPlayerUpAndUpdatesVisualState() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)2, (short)0, (short)3);   
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveUp();
        
        assertThat(player.getPositionZ(), is((short)2));
        assertThat(PlayerAvatarMovementPresenterMock.getMoveUpCallCount(), is(1));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosX(), is(2));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosY(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosZ(), is(2));         
    }
    
    @Test
    public void testMoveLeft_playerIsOnMap_movesPlayerLeftAndUpdatesVisualState() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)2, (short)0, (short)3);   
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveLeft();
        
        assertThat(player.getPositionX(), is((short)1));
        assertThat(PlayerAvatarMovementPresenterMock.getMoveLeftCallCount(), is(1));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosX(), is(1));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosY(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getLastPosZ(), is(3));          
    }
    
    @Test
    public void testMoveDown_playerCollides_presentsStepAnimationButPlayerDoesNotMove() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)3, (short)0, (short)3);   
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveDown();
        
        assertThat(player.getPositionZ(), is((short)3));
        assertThat(PlayerAvatarMovementPresenterMock.getStepDownWithoutMovingCallCount(), is(1));
    }
    
    @Test
    public void testMoveRight_playerCollides_presentsStepAnimationButPlayerDoesNotMove() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)3, (short)0, (short)3);   

        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveRight();
        
        assertThat(player.getPositionX(), is((short)3));
        assertThat(PlayerAvatarMovementPresenterMock.getStepRightWithoutMovingCallCount(), is(1));
    }
    
    @Test
    public void testMoveUp_playerCollides_presentsStepAnimationButPlayerDoesNotMove() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)2, (short)0, (short)2);       
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveUp();
        
        assertThat(player.getPositionZ(), is((short)2));
        assertThat(PlayerAvatarMovementPresenterMock.getStepUpWithoutMovingCallCount(), is(1));
    }
    
    @Test
    public void testMoveLeft_playerCollides_presentsStepAnimationButPlayerDoesNotMove() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)1, (short)0, (short)2);       
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveLeft();
        
        assertThat(player.getPositionX(), is((short)1));
        assertThat(PlayerAvatarMovementPresenterMock.getStepLeftWithoutMovingCallCount(), is(1));
    }
    
    @Test
    public void testMoveDown_playerIsBusy_visualStateIsNotUpdated() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)1, (short)0, (short)2);     
        player.setIsBusy(true);
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveDown();
        
        player.setIsBusy(false);
        
        assertThat(PlayerAvatarMovementPresenterMock.getMoveDownCallCount(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getStepDownWithoutMovingCallCount(), is(0));
    }
    
    @Test
    public void testMoveRight_playerIsBusy_visualStateIsNotUpdated() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)1, (short)0, (short)2);     
        player.setIsBusy(true);
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveRight();
        
        player.setIsBusy(false);        
        
        assertThat(PlayerAvatarMovementPresenterMock.getMoveRightCallCount(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getStepRightWithoutMovingCallCount(), is(0));     
    }
    
    @Test
    public void testMoveUp_playerIsBusy_visualStateIsNotUpdated() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)1, (short)0, (short)2);     
        player.setIsBusy(true);
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveUp();
        
        player.setIsBusy(false);        
        
        assertThat(PlayerAvatarMovementPresenterMock.getMoveUpCallCount(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getStepUpWithoutMovingCallCount(), is(0));         
    }
    
    @Test
    public void testMoveLeft_playerIsBusy_visualStateIsNotUpdated() throws InterruptedException {
        Thread.sleep(350);
        
        Player player = Player.getInstance();
        player.placeOnMapWithPosition(map, (short)1, (short)0, (short)2);     
        player.setIsBusy(true);
        
        PlayerMovementUseCaseImpl testCandidate = new PlayerMovementUseCaseImpl();
        testCandidate.moveLeft();
        
        player.setIsBusy(false);        
        
        assertThat(PlayerAvatarMovementPresenterMock.getMoveLeftCallCount(), is(0));
        assertThat(PlayerAvatarMovementPresenterMock.getStepLeftWithoutMovingCallCount(), is(0));         
    }
}