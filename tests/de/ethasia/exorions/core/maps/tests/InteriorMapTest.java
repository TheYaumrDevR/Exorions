package de.ethasia.exorions.core.maps.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class InteriorMapTest {

    @Test
    public void testCreate_withDimensions_throwsNoException() {
        InteriorMap testCandidate = new InteriorMap((short)50, (short)50);
    }
}
