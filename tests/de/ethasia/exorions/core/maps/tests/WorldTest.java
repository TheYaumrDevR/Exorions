package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.core.maps.World;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void testSetActiveMap_activeMapIsRetrieved_retrievedMapIsTheSameAsTheSetMap() {
        World testCandidate = new World();
        InteriorMap toPlace = new InteriorMap((short)5, (short)5);
        
        testCandidate.placeInteriorMapWithIdentifier("MapOne", toPlace);
        testCandidate.setActiveMap("MapOne");
        InteriorMap activeMap = testCandidate.getActiveMap();
        
        assertThat(activeMap, is(sameInstance(toPlace)));
    }
}
