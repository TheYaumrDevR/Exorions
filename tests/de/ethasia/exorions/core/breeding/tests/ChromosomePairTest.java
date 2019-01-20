package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Chromosome;
import de.ethasia.exorions.core.breeding.ChromosomePair;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChromosomePairTest {
    
    @BeforeClass
    public static void initDependencies() {
        CoreClassesFactory.setInstance(new MockCoreClassesFactory());
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -1, -1, 1, 0, 0, -1, -1, 0, 1, -1, 1, 1, -1}); 
    }
    
    @Before
    public void resetSharedStates() {
        MockCoreClassesFactory mockClassesFactory = new MockCoreClassesFactory();
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)mockClassesFactory.getRandomNumberGeneratorSingletonInstance();
        rngMock.reset();
    }    
    
    @Test
    public void testBuilderBuild_setChromosomes_theyAreInProduct() throws NotAllPropertiesAreSetException {
        Chromosome paternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        Chromosome maternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .setMaternalChromosome(maternalChromosome)
            .build();
        
        assertThat(product.getPaternalChromosome(), is(sameInstance(paternalChromosome)));
        assertThat(product.getMaternalChromosome(), is(sameInstance(maternalChromosome)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_doNotSetPaternalChromosome_throwsException() throws NotAllPropertiesAreSetException {
        Chromosome maternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setMaternalChromosome(maternalChromosome)
            .build();
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_doNotSetMaternalChromosome_throwsException() throws NotAllPropertiesAreSetException {       
        Chromosome paternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .build();
    }  
    
    @Test
    public void testCloneWithCrossover_createdClonesContainNewChromosomes() throws NotAllPropertiesAreSetException {
        Chromosome paternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        Chromosome maternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .setMaternalChromosome(maternalChromosome)
            .build();     
        
        ChromosomePair clone = product.cloneWithCrossover();
        
        assertThat(clone.getMaternalChromosome(), is(not(sameInstance(maternalChromosome))));
        assertThat(clone.getPaternalChromosome(), is(not(sameInstance(paternalChromosome))));
        assertThat(clone.getMaternalChromosome(), is(not(equalTo(maternalChromosome))));
        assertThat(clone.getPaternalChromosome(), is(not(equalTo(paternalChromosome))));
    }
}