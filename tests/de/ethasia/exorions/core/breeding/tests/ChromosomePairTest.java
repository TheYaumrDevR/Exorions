package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Chromosome;
import de.ethasia.exorions.core.breeding.ChromosomePair;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChromosomePairTest {
    
    @Test
    public void testBuilderBuild_setChromosomes_theyAreInProduct() throws NotAllPropertiesAreSetException {
        Chromosome paternalChromosome = new Chromosome.Builder().build();
        Chromosome maternalChromosome = new Chromosome.Builder().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .setMaternalChromosome(maternalChromosome)
            .build();
        
        assertThat(product.getPaternalChromosome(), is(sameInstance(paternalChromosome)));
        assertThat(product.getMaternalChromosome(), is(sameInstance(maternalChromosome)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_doNotSetPaternalChromosome_throwsException() throws NotAllPropertiesAreSetException {
        Chromosome maternalChromosome = new Chromosome.Builder().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setMaternalChromosome(maternalChromosome)
            .build();
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_doNotSetMaternalChromosome_throwsException() throws NotAllPropertiesAreSetException {
        Chromosome paternalChromosome = new Chromosome.Builder().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .build();
    }  
    
    @Test
    public void testCloneWithCrossover_createdClonesContainNewChromosomes() throws NotAllPropertiesAreSetException {
        Chromosome paternalChromosome = new Chromosome.Builder().build();
        Chromosome maternalChromosome = new Chromosome.Builder().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .setMaternalChromosome(maternalChromosome)
            .build();     
        
        ChromosomePair clone = product.cloneWithCrossover();
        
        assertThat(clone.getMaternalChromosome(), is(not(sameInstance(maternalChromosome))));
        assertThat(clone.getPaternalChromosome(), is(not(sameInstance(paternalChromosome))));
    }
}