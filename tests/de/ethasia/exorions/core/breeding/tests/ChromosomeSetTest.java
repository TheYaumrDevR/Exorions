package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.ChromosomeSet;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChromosomeSetTest {
    
    @BeforeClass
    public static void initDependencies() {
        CoreClassesFactory.setInstance(new MockCoreClassesFactory());
    }

    @Test
    public void testRandomBuilder_createsRandomChromosomeSetByCallingRng() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false, false, false, false, false, true, false, true, true, false, true});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -1, -1, 1, 0, 0, -1, -1, 0});        
        rngMock.reset();        
        
        ChromosomeSet product = new ChromosomeSet.Random().build();
        
        int allelesPerChromosomePair = 20;
        int chromosomePairs = 25;
        int expectedRngCallCount = allelesPerChromosomePair * chromosomePairs;
        
        assertThat(product, is(notNullValue()));
        assertThat(rngMock.getCallCount("createRandomIntegerBetweenAnd"), is(expectedRngCallCount));
        assertThat(rngMock.getCallCount("createRandomBoolean"), is(expectedRngCallCount));        
    }
}