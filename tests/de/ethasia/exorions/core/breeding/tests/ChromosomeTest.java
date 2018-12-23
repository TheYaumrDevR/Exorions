package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.breeding.Chromosome;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ChromosomeTest {

    @Test
    public void testBuilderBuild_emptyChromosomeIsCreated_isNotNull() {
        Chromosome product = new Chromosome.Builder().build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBuilderBuild_setAllelesOnAllStats_allelesAreInProduct() {
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
    public void testCreateClone_clonedChromosomeIsDifferentButHasSameAlleleProperties() {
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
}
