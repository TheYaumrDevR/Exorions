package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.Allele;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AlleleTest {

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
        
        assertThat(clone, is(not(equalTo(testCandidate))));
        assertThat(clone.getStatModifier(), is(equalTo(testCandidate.getStatModifier())));
        assertThat(clone.isDominant(), is(testCandidate.isDominant()));
    }
}
