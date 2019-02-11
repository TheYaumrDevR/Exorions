package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.breeding.AllelePairGeneValueCalculator;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AllelePairGeneValueCalculatorTest {

    @Test
    public void testCalculateCombinedGeneValue_bothAreDominantAndHaveSameValue_sameValueIsReturned() {
        AllelePairGeneValueCalculator testCandidate = new AllelePairGeneValueCalculator();
        
        Allele firstAllele = new Allele.Builder()
            .setIsDominant(true)
            .setStatModifier(1)
            .build();
        
        Allele secondAllele = new Allele.Builder()
            .setIsDominant(true)
            .setStatModifier(1)
            .build();
        
        int actualValue = testCandidate.calculateCombinedGeneValue(firstAllele, secondAllele);
        
        assertThat(actualValue, is(1));
    }
    
    @Test
    public void testCalculateCombinedGeneValue_onlyOneIsDominant_dominantValueIsReturned() {
        AllelePairGeneValueCalculator testCandidate = new AllelePairGeneValueCalculator();
        
        Allele firstAllele = new Allele.Builder()
            .setIsDominant(false)
            .setStatModifier(1)
            .build();
        
        Allele secondAllele = new Allele.Builder()
            .setIsDominant(true)
            .setStatModifier(-1)
            .build();
        
        int actualValue = testCandidate.calculateCombinedGeneValue(firstAllele, secondAllele);
        
        assertThat(actualValue, is(-1));
    }  
    
    @Test
    public void testCalculateCombinedGeneValue_bothAreRecessiveAndDiffer_roundedAverageIsReturned() {
        AllelePairGeneValueCalculator testCandidate = new AllelePairGeneValueCalculator();
        
        Allele firstAllele = new Allele.Builder()
            .setIsDominant(false)
            .setStatModifier(-1)
            .build();
        
        Allele secondAllele = new Allele.Builder()
            .setIsDominant(false)
            .setStatModifier(0)
            .build();        
        
        int actualValue = testCandidate.calculateCombinedGeneValue(firstAllele, secondAllele);
        
        assertThat(actualValue, is(0));
    }
    
    @Test
    public void testCalculateCombinedGeneValue_bothAreDominantAndDiffer_roundedAverageIsReturned() {
        AllelePairGeneValueCalculator testCandidate = new AllelePairGeneValueCalculator();
        
        Allele firstAllele = new Allele.Builder()
            .setIsDominant(false)
            .setStatModifier(-4)
            .build();
        
        Allele secondAllele = new Allele.Builder()
            .setIsDominant(false)
            .setStatModifier(-3)
            .build();        
        
        int actualValue = testCandidate.calculateCombinedGeneValue(firstAllele, secondAllele);
        
        assertThat(actualValue, is(-3));
    }    
}
