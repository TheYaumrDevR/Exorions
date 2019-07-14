package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.breeding.Chromosome;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChromosomeTest {
    
    @BeforeClass
    public static void initDependencies() {
        CoreClassesFactory.setInstance(new MockCoreClassesFactory());
    }
    
    @Before
    public void resetSharedStates() {
        MockCoreClassesFactory mockClassesFactory = new MockCoreClassesFactory();
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)mockClassesFactory.getRandomNumberGeneratorSingletonInstance();
        rngMock.reset();
    }
    
    @Test
    public void testBuilderBuild_setAllelesOnAllStats_allelesAreInProduct() throws NotAllPropertiesAreSetException {
        Allele alleleMaxHealth = new Allele.Builder()
            .setStatModifier(1)
            .build();    
        Allele alleleAttack = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleDefense = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleSpecialAttack = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleSpecialDefense = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleSwiftness = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleAccuracy = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleEvasiveness = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleCriticalHitAvoidance = new Allele.Builder()
            .setStatModifier(1)
            .build();
        Allele alleleCriticalHitFrequency = new Allele.Builder()
            .setStatModifier(1)
            .build();
        
        Chromosome product = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealth)
            .setAttackAllele(alleleAttack)
            .setDefenseAllele(alleleDefense)
            .setSpecialAttackAllele(alleleSpecialAttack)
            .setSpecialDefenseAllele(alleleSpecialDefense)
            .setSwiftnessAllele(alleleSwiftness)
            .setAccuracyAllele(alleleAccuracy)
            .setEvasivenessAllele(alleleEvasiveness)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidance)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequency)
            .build();
        
        assertThat(product.getMaximumHealthAllele(), is(equalTo(alleleMaxHealth)));
        assertThat(product.getAttackAllele(), is(equalTo(alleleAttack)));
        assertThat(product.getDefenseAllele(), is(equalTo(alleleDefense)));
        assertThat(product.getSpecialAttackAllele(), is(equalTo(alleleSpecialAttack)));
        assertThat(product.getSpecialDefenseAllele(), is(equalTo(alleleSpecialDefense)));
        assertThat(product.getSwiftnessAllele(), is(equalTo(alleleSwiftness)));
        assertThat(product.getAccuracyAllele(), is(equalTo(alleleAccuracy)));
        assertThat(product.getEvasivenessAllele(), is(equalTo(alleleEvasiveness)));
        assertThat(product.getCriticalHitAvoidanceAllele(), is(equalTo(alleleCriticalHitAvoidance)));
        assertThat(product.getCriticalHitFrequencyAllele(), is(equalTo(alleleCriticalHitFrequency)));
    }
    
    @Test
    public void testCreateClone_clonedChromosomeIsDifferentButHasSameAlleleProperties() throws NotAllPropertiesAreSetException {
        Allele alleleMaxHealth = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();    
        Allele alleleAttack = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(false)
            .build();
        Allele alleleDefense = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialAttack = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialDefense = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleSwiftness = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(true)
            .build();
        Allele alleleAccuracy = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();
        Allele alleleEvasiveness = new Allele.Builder()
            .setStatModifier(1)
            .setIsDominant(true)
            .build();
        Allele alleleCriticalHitAvoidance = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(true)
            .build();
        Allele alleleCriticalHitFrequency = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        
        Chromosome testCandidate = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealth)
            .setAttackAllele(alleleAttack)
            .setDefenseAllele(alleleDefense)
            .setSpecialAttackAllele(alleleSpecialAttack)
            .setSpecialDefenseAllele(alleleSpecialDefense)
            .setSwiftnessAllele(alleleSwiftness)
            .setAccuracyAllele(alleleAccuracy)
            .setEvasivenessAllele(alleleEvasiveness)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidance)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequency)
            .build();    
        
        Chromosome clone = testCandidate.createClone();
        
        assertThat(clone, is(notNullValue()));
        assertThat(clone, is(not(sameInstance(testCandidate))));
        assertThat(clone, is(equalTo(testCandidate)));
        assertThat(clone.hashCode(), is(equalTo(testCandidate.hashCode())));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_throwsExceptionIfAlleleIsNotSet() throws NotAllPropertiesAreSetException {
        Chromosome testCandidate = new Chromosome.Builder().build();
    }
    
    @Test
    public void testBuilderBuild_randomizeUndefinedAllelesIsSet_allelesAreSetToSpecifiedRandomSequence() throws NotAllPropertiesAreSetException {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -1, -1, 1, 0, 0, -1, -1, 0});
       
        Chromosome testCandidate = new Chromosome.Builder()
            .randomizeUndefinedAlleles()
            .build();
        
        Chromosome expected = createExpectedChromosomeForRandomlyCreatedAlleleInRandomCreationTest();
        assertThat(expected, is(equalTo(testCandidate)));
    }
    
    @Test
    public void testBuilderBuild_statRangeForRandomizationIsSet_allelesHaveGivenStatRange() throws NotAllPropertiesAreSetException {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false});
        rngMock.setIntegerSequenceToUse(new int[] {0, 0, -2, -2, 2, 0, 0, -1, -1, 0});
       
        Chromosome testCandidate = new Chromosome.Builder()
            .randomizeUndefinedAlleles()
            .withRange(2)
            .build();
        
        Chromosome expected = this.createExpectedChromosomeForRandomlyCreatedAlleleInRandomCreationWithRangeTest();
        assertThat(expected, is(equalTo(testCandidate)));
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private Chromosome createExpectedChromosomeForRandomlyCreatedAlleleInRandomCreationTest() throws NotAllPropertiesAreSetException {
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
        
        Chromosome result = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealth)
            .setAttackAllele(alleleAttack)
            .setDefenseAllele(alleleDefense)
            .setSpecialAttackAllele(alleleSpecialAttack)
            .setSpecialDefenseAllele(alleleSpecialDefense)
            .setSwiftnessAllele(alleleSwiftness)
            .setAccuracyAllele(alleleAccuracy)
            .setEvasivenessAllele(alleleEvasiveness)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidance)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequency)
            .build();  
        
        return result;
    }
    
    private Chromosome createExpectedChromosomeForRandomlyCreatedAlleleInRandomCreationWithRangeTest() throws NotAllPropertiesAreSetException {
        Allele alleleMaxHealth = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();    
        Allele alleleAttack = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(true)
            .build();
        Allele alleleDefense = new Allele.Builder()
            .setStatModifier(-2)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialAttack = new Allele.Builder()
            .setStatModifier(-2)
            .setIsDominant(false)
            .build();
        Allele alleleSpecialDefense = new Allele.Builder()
            .setStatModifier(2)
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
        
        Chromosome result = new Chromosome.Builder()
            .setMaximumHealthAllele(alleleMaxHealth)
            .setAttackAllele(alleleAttack)
            .setDefenseAllele(alleleDefense)
            .setSpecialAttackAllele(alleleSpecialAttack)
            .setSpecialDefenseAllele(alleleSpecialDefense)
            .setSwiftnessAllele(alleleSwiftness)
            .setAccuracyAllele(alleleAccuracy)
            .setEvasivenessAllele(alleleEvasiveness)
            .setCriticalHitAvoidanceAllele(alleleCriticalHitAvoidance)
            .setCriticalHitFrequencyAllele(alleleCriticalHitFrequency)
            .build();  
        
        return result;
    }    
    
    //</editor-fold>
}
