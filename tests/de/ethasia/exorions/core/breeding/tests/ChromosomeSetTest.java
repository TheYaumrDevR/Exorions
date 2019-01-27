package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.ChromosomeSet;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChromosomeSetTest {

    @Test
    public void testRandomBuilder_createsRandomChromosomeSetByCallingRng() {
        ChromosomeSet product = new ChromosomeSet.Random().build();
        
        assertThat(product, is(notNullValue()));
    }
}