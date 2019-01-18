package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AlleleTest {
    
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
    public void testBuilderBuild_nothingIsSet_alleleIsCreated() {
        Allele testCandidate = new Allele.Builder().build();
        assertThat(testCandidate, is(notNullValue()));
    }
    
    @Test
    public void testBuilderBuild_setStatModifier_modifierIsInAllele() {
        Allele testCandidate = new Allele.Builder()
            .setStatModifier(1)
            .build();
        
        int statModifierInAllele = testCandidate.getStatModifier();
        
        assertThat(statModifierInAllele, is(equalTo(1)));
    }
    
    @Test
    public void testBuilderBuild_setIsDominantFalse_alleleIsNotDominant() {
        Allele testCandidate = new Allele.Builder()
            .setIsDominant(false)
            .build();
        
        boolean alleleIsDominant = testCandidate.isDominant();
        
        assertThat(alleleIsDominant, is(false));
    }
    
    @Test
    public void testCreateClone_clonedAlleleIsDifferentButHasSameProperties() {
        Allele testCandidate = new Allele.Builder()
            .setStatModifier(-1)
            .setIsDominant(false)
            .build();
        
        Allele clone = testCandidate.createClone();
        
        assertThat(clone, is(not(sameInstance(testCandidate))));
        assertThat(clone.getStatModifier(), is(equalTo(testCandidate.getStatModifier())));
        assertThat(clone.isDominant(), is(testCandidate.isDominant()));
    }
    
    @Test
    public void testEquals_allelesWithEqualValuesAreEqual() {
        Allele testCandidate = new Allele.Builder()
            .setStatModifier(0)
            .setIsDominant(false)
            .build();  
        
        Allele clone = testCandidate.createClone();
        
        assertThat(clone, is(equalTo(testCandidate)));
        assertThat(clone.hashCode(), is(equalTo(testCandidate.hashCode())));
    }
    
    @Test
    public void testRandomAlleleBuilder_randomizationRangeIsNotSet_defaultRangeIsTaken() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {true, false, true});
        rngMock.setIntegerSequenceToUse(new int[] {-1, 0, 0});
        
        Allele testCandidate = new Allele.Random()
            .build();
        
        assertThat(testCandidate.getStatModifier(), is(equalTo(-1)));
        assertThat(testCandidate.isDominant(), is(true));
    }
    
    @Test
    public void testRandomAlleleBuilder_randomizationRangeIsSet_setRangeIsTaken() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false});
        rngMock.setIntegerSequenceToUse(new int[] {2, -2, -1});        
        
        Allele testCandidate = new Allele.Random()
            .setMaximumModifierValueTo(2)
            .build();
        
        assertThat(testCandidate.getStatModifier(), is(equalTo(2)));
        assertThat(testCandidate.isDominant(), is(false));
    }
    
    @Test
    public void testRandomAlleleBuilder_createTwoAllelesWithDefaultRange_valuesAreFromRandomSequence() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, true});
        rngMock.setIntegerSequenceToUse(new int[] {1, 0, 1});
        
        Allele testCandidate = new Allele.Random()
            .build();
        Allele testCandidate2 = new Allele.Random()
            .build();
        
        assertThat(testCandidate.getStatModifier(), is(equalTo(1)));
        assertThat(testCandidate.isDominant(), is(false));
        
        assertThat(testCandidate2.getStatModifier(), is(equalTo(0)));
        assertThat(testCandidate2.isDominant(), is(true));
    } 
    
    @Test(expected = RuntimeException.class)
    public void testRandomAlleleBuilder_randomSequenceHasValuesAboveRange_throwsException() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false});
        rngMock.setIntegerSequenceToUse(new int[] {-3});
        
        Allele testCandidate = new Allele.Random()
            .setMaximumModifierValueTo(2)
            .build();
    }    
}
