package de.ethasia.exorions.ioadapters.presenters.animations.test;

import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.ioadapters.presenters.ActionInCurrentStateNotPossibleException;
import de.ethasia.exorions.ioadapters.presenters.animations.CharacterWalkingAnimation;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterWalkAnimationTest {

    @Test
    public void testInitialize_characterIsInitializedStandingFacingDown() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(0));
    }
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingDownAndNotWalking_returnsZero() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.DOWN);
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(0));        
    }
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingRightAndNotWalking_returnsThree() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.RIGHT);
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(3));      
    } 
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingUpAndNotWalking_returnsSix() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.UP);
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(6));     
    }    
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingLeftAndNotWalking_returnsNine() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.LEFT);
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(9));
    }    
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingDownAndWalking_returnsOne() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.DOWN);
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(1));       
    }
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingRightAndWalking_returnsFour() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.RIGHT);
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(4));       
    } 
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingUpAndWalking_returnsSeven() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.UP);
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(7));     
    }    
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingLeftAndWalking_returnsTen() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.LEFT);
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(10));    
    }
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingDownAndWalkedStoppedWalked_returnsTwo() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.DOWN);
        testCandidate.startWalking();
        testCandidate.stopWalking();
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(2));     
    }
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingRightAndWalkedStoppedWalked_returnsFive() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.RIGHT);
        testCandidate.startWalking();
        testCandidate.stopWalking();
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(5));    
    } 
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingUpAndWalkedStoppedWalked_returnsEight() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.UP);
        testCandidate.startWalking();
        testCandidate.stopWalking();
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(8));     
    }    
    
    @Test
    public void testGetAnimationFrameId_characterIsFacingLeftAndWalkedStoppedWalked_returnsEleven() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.setFacingDirection(MoveDirections.LEFT);
        testCandidate.startWalking();
        testCandidate.stopWalking();
        testCandidate.startWalking();
        
        int animationFrameId = testCandidate.getAnimationFrameId();
        assertThat(animationFrameId, is(11));       
    }  
    
    @Test(expected = ActionInCurrentStateNotPossibleException.class)
    public void testTurn_isWalking_throwsException() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.startWalking();
        testCandidate.setFacingDirection(MoveDirections.LEFT);
    }
    
    @Test(expected = ActionInCurrentStateNotPossibleException.class)
    public void testStartWalking_isWalking_throwsException() {
        CharacterWalkingAnimation testCandidate = new CharacterWalkingAnimation();
        testCandidate.startWalking();
        testCandidate.startWalking();
    }    
}
