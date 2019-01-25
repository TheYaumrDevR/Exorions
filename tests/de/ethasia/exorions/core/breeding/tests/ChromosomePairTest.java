package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Allele;
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
    
    @Test 
    public void testCloneWithCrossover_resultingChromosomesHaveAllelesFromRng() throws NotAllPropertiesAreSetException {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false, false, false, false, false, true, false, true, true, false, true});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -1, -1, 1, 0, 0, -1, -1, 0}); 
        rngMock.reset();
        
        Chromosome maternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        Chromosome paternalChromosome = new Chromosome.Builder().randomizeUndefinedAlleles().build();
        
        ChromosomePair product = new ChromosomePair.Builder()
            .setPaternalChromosome(paternalChromosome)
            .setMaternalChromosome(maternalChromosome)
            .build();
        
        ChromosomePair testCandidate = product.cloneWithCrossover();
        ChromosomePair expected = getExpectedResultForCloneWithCrossoverTest();
        
        assertThat(testCandidate.getMaternalChromosome(), is(equalTo(expected.getMaternalChromosome())));
        assertThat(testCandidate.getPaternalChromosome(), is(equalTo(expected.getPaternalChromosome())));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private ChromosomePair getExpectedResultForCloneWithCrossoverTest() throws NotAllPropertiesAreSetException {        
        Allele alleleMaxHealth = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();    
        Allele alleleAttack = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleDefense = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialAttack = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialDefense = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(true)
            .build();
        Allele alleleSwiftness = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleAccuracy = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleEvasiveness = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(true)
            .build();
        Allele alleleCriticalHitAvoidance = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleCriticalHitFrequency = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();
        
        Allele alleleMaxHealthSecondChromosome = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();    
        Allele alleleAttackSecondChromosome = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();
        Allele alleleDefenseSecondChromosome = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialAttackSecondChromosome = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialDefenseSecondChromosome = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(true)
            .build();
        Allele alleleSwiftnessSecondChromosome = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();
        Allele alleleAccuracySecondChromosome = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleEvasivenessSecondChromosome = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(true)
            .build();
        Allele alleleCriticalHitAvoidanceSecondChromosome = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleCriticalHitFrequencySecondChromosome = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        
        Chromosome firstExpectedChromosome = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealthSecondChromosome)
            .setAttackAllele(alleleAttack)
            .setDefenseAllele(alleleDefenseSecondChromosome)
            .setSpecialAttackAllele(alleleSpecialAttackSecondChromosome)
            .setSpecialDefenseAllele(alleleSpecialDefense)
            .setSwiftnessAllele(alleleSwiftness)
            .setAccuracyAllele(alleleAccuracy)
            .setEvasivenessAllele(alleleEvasiveness)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidanceSecondChromosome)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequencySecondChromosome)
            .build();
        
        Chromosome secondExpectedChromosome = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealth)
            .setAttackAllele(alleleAttackSecondChromosome)
            .setDefenseAllele(alleleDefense)
            .setSpecialAttackAllele(alleleSpecialAttack)
            .setSpecialDefenseAllele(alleleSpecialDefenseSecondChromosome)
            .setSwiftnessAllele(alleleSwiftnessSecondChromosome)
            .setAccuracyAllele(alleleAccuracySecondChromosome)
            .setEvasivenessAllele(alleleEvasivenessSecondChromosome)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidance)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequency)
            .build();
        
        return new ChromosomePair.Builder()
            .setMaternalChromosome(firstExpectedChromosome)
            .setPaternalChromosome(secondExpectedChromosome)
            .build();
    }
    
    //</editor-fold>
}