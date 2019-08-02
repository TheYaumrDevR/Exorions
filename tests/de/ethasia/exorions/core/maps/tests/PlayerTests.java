package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.MoveableMapObject;
import de.ethasia.exorions.core.maps.Player;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PlayerTests {

    @Test
    public void testThatPlayerIsAMoveableMapObject() {
        Player testCandidate = Player.getInstance();
        
        boolean playerIsInstanceOfMoveableMapObject = testCandidate instanceof MoveableMapObject;
        assertThat(playerIsInstanceOfMoveableMapObject, is(true));
    }
    
    @Test
    public void testThatPlayerIsASingleton() {
        Player testCandidateOne = Player.getInstance();
        Player testCandidateTwo = Player.getInstance();
        
        assertThat(testCandidateOne, is(sameInstance(testCandidateTwo)));
    }
}