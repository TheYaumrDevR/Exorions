package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.breeding.AllelePairGeneValueCalculatorImpl;
import de.ethasia.exorions.core.breeding.Chromosome;
import de.ethasia.exorions.core.breeding.ChromosomePair;
import de.ethasia.exorions.core.interfaces.AllelePairGeneValueCalculator;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.AllelePairGeneValueCalculatorMock;
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
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false, true, true, false, false, true, false, true, false, true, true, false, false, false, true, true, true});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -1, -1, 1, 0, 0, -1, -1, 0, 1, -1, 1, 1, -1, -1, 1, 0, -1, 1, 0, 1, 0, 0, 1, 0, 1}); 
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
    
    @Test
    public void testRandomBuilder_rngIsCalledExpectedAmountOfTimes() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.reset();
        
        ChromosomePair product = new ChromosomePair.Random().build();
        
        assertThat(product, is(notNullValue()));
        assertThat(rngMock.getCallCount("createRandomIntegerBetweenAnd"), is(20));
        assertThat(rngMock.getCallCount("createRandomBoolean"), is(20));
    }
    
    @Test
    public void testGetMaximumHealthGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getMaximumHealthGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getMaximumHealthAllele(), 
            testCandidate.getPaternalChromosome().getMaximumHealthAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }
    
    @Test
    public void testGetAttackGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getAttackGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getAttackAllele(), 
            testCandidate.getPaternalChromosome().getAttackAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }
    
    @Test
    public void testGetDefenseGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getDefenseGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getDefenseAllele(), 
            testCandidate.getPaternalChromosome().getDefenseAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }   
    
    @Test
    public void testGetSpecialAttackGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getSpecialAttackGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getSpecialAttackAllele(), 
            testCandidate.getPaternalChromosome().getSpecialAttackAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }    
    
    @Test
    public void testGetSpecialDefenseGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getSpecialDefenseGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getSpecialDefenseAllele(), 
            testCandidate.getPaternalChromosome().getSpecialDefenseAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    } 
    
    @Test
    public void testGetAccuracyGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getAccuracyGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getAccuracyAllele(), 
            testCandidate.getPaternalChromosome().getAccuracyAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    } 
    
    @Test
    public void testGetEvasivenessGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getEvasivenessGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getEvasivenessAllele(), 
            testCandidate.getPaternalChromosome().getEvasivenessAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }   
    
    @Test
    public void testGetCriticalHitFrequencyGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getCriticalHitFrequencyGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getCriticalHitFrequencyAllele(), 
            testCandidate.getPaternalChromosome().getCriticalHitFrequencyAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    } 
    
    @Test
    public void testGetCriticalHitAvoidanceGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getCriticalHitAvoidanceGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getCriticalHitAvoidanceAllele(), 
            testCandidate.getPaternalChromosome().getCriticalHitAvoidanceAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
    }     
    
    @Test
    public void testGetSwiftnessGeneValue_allelePairGeneValueCalculatorIsCalled() {
        AllelePairGeneValueCalculatorMock gvCalculatorMock = (AllelePairGeneValueCalculatorMock)(new MockCoreClassesFactory().getAllelePairGeneValueCalculatorSingletonInstance());
        AllelePairGeneValueCalculator gvCalculator = new AllelePairGeneValueCalculatorImpl();
        gvCalculatorMock.reset();
        
        ChromosomePair testCandidate = new ChromosomePair.Random().build();
        int result = testCandidate.getSwiftnessGeneValue();
        int expected = gvCalculator.calculateCombinedGeneValue(testCandidate.getMaternalChromosome().getSwiftnessAllele(), 
            testCandidate.getPaternalChromosome().getSwiftnessAllele());
        
        assertThat(gvCalculatorMock.getCallCount("calculateCombinedGeneValue"), is(1));
        assertThat(result, is(equalTo(expected)));
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