package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.IndividualExorionStrengthsAndWeaknesses;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndividualExorionStrengthsAndWeaknessesTest {

    @Test
    public void testStrengthsWeaknessesBuilderBuild_productIsNotNull() {
        IndividualExorionStrengthsAndWeaknesses.Builder testCandidate = new IndividualExorionStrengthsAndWeaknesses.Builder();
        
        IndividualExorionStrengthsAndWeaknesses result = testCandidate
            .build();
        
        assertThat(result, is(notNullValue()));
    }
    
    @Test
    public void testStrengthsWeaknessesBuilderBuild_twoStrengthsAndWeaknessesEverywhere_createdProductHasSetProperties() {
        IndividualExorionStrengthsAndWeaknesses.Builder testCandidate = new IndividualExorionStrengthsAndWeaknesses.Builder();
        
        IndividualExorionStrengthsAndWeaknesses result = testCandidate
            .setResistantAgainst(DamageTypes.POISON)
            .setResistantAgainst(DamageTypes.SHATTER)
            .setDoubleStrongAgainst(DamageTypes.GROUND)
            .setDoubleStrongAgainst(DamageTypes.DROWNING)
            .setStrongAgainst(DamageTypes.STAB)
            .setStrongAgainst(DamageTypes.BLUNT)
            .setWeakAgainst(DamageTypes.WIND)
            .setWeakAgainst(DamageTypes.DRYING)
            .setDoubleWeakAgainst(DamageTypes.CUT)
            .setDoubleWeakAgainst(DamageTypes.INFECTION)
            .setDestroyedBy(DamageTypes.RADIOACTIVITY)
            .setDestroyedBy(DamageTypes.SQUEEZE)
            .build();
        
        assertThat(result.getResistantAgainst(), hasItems(DamageTypes.POISON, DamageTypes.SHATTER));
        assertThat(result.getDoubleStrongAgainst(), hasItems(DamageTypes.GROUND, DamageTypes.DROWNING));
        assertThat(result.getStrongAgainst(), hasItems(DamageTypes.STAB, DamageTypes.BLUNT));
        assertThat(result.getWeakAgainst(), hasItems(DamageTypes.WIND, DamageTypes.DRYING));
        assertThat(result.getDoubleWeakAgainst(), hasItems(DamageTypes.CUT, DamageTypes.INFECTION));
        assertThat(result.getDestroyedBy(), hasItems(DamageTypes.RADIOACTIVITY, DamageTypes.SQUEEZE));
    }  
    
    @Test
    public void testStrengthsWeaknessesBuilderBuild_oneStrengthAndWeaknessEverywhere_createdProductHasSetProperties() {
        IndividualExorionStrengthsAndWeaknesses.Builder testCandidate = new IndividualExorionStrengthsAndWeaknesses.Builder();
        
        IndividualExorionStrengthsAndWeaknesses result = testCandidate
            .setResistantAgainst(DamageTypes.SUFFOCATION)
            .setDoubleStrongAgainst(DamageTypes.SQUASH)
            .setStrongAgainst(DamageTypes.BURN)
            .setWeakAgainst(DamageTypes.DISINTEGRATION)
            .setDoubleWeakAgainst(DamageTypes.RIP)
            .setDestroyedBy(DamageTypes.ELECTROCUTION)
            .build();
        
        assertThat(result.getResistantAgainst(), hasItems(DamageTypes.SUFFOCATION));
        assertThat(result.getDoubleStrongAgainst(), hasItems(DamageTypes.SQUASH));
        assertThat(result.getStrongAgainst(), hasItems(DamageTypes.BURN));
        assertThat(result.getWeakAgainst(), hasItems(DamageTypes.DISINTEGRATION));
        assertThat(result.getDoubleWeakAgainst(), hasItems(DamageTypes.RIP));
        assertThat(result.getDestroyedBy(), hasItems(DamageTypes.ELECTROCUTION));
        
        assertThat(result.getResistantAgainst().size(), is(1));
        assertThat(result.getDoubleStrongAgainst().size(), is(1));
        assertThat(result.getStrongAgainst().size(), is(1));
        assertThat(result.getWeakAgainst().size(), is(1));
        assertThat(result.getDoubleWeakAgainst().size(), is(1));
        assertThat(result.getDestroyedBy().size(), is(1));
    }
    
    @Test
    public void testStrengthsWeaknessesBuilderBuild_nothingIsSet_setsAreEmpty() {
        IndividualExorionStrengthsAndWeaknesses.Builder testCandidate = new IndividualExorionStrengthsAndWeaknesses.Builder();
        
        IndividualExorionStrengthsAndWeaknesses result = testCandidate
            .build();
        
        assertThat(result.getResistantAgainst().size(), is(0));
        assertThat(result.getDoubleStrongAgainst().size(), is(0));
        assertThat(result.getStrongAgainst().size(), is(0));
        assertThat(result.getWeakAgainst().size(), is(0));
        assertThat(result.getDoubleWeakAgainst().size(), is(0));
        assertThat(result.getDestroyedBy().size(), is(0));
    }    
}
