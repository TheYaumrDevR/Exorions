package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExorionSpeciesTest {

    @Test
    public void testBuilderBuild_nothingIsSet_emptySpeciesIsCreated() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate.build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBuilderBuild_exorionNameIsSet_nameIsContainedInProduct() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate.setName("Fookachu").build();
        
        assertThat(product.getName(), is(equalTo("Fookachu")));
    }
    
    @Test
    public void testBuilderBuild_learningRequirementsAreSet_theyAreContainedInProduct() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .build();
        Set<AbilityLearningRequirements> fulfilledLearningRequirements = product.getFulfilledLearningRequirements();
        
        assertThat(fulfilledLearningRequirements, hasItems(AbilityLearningRequirements.TAIL, AbilityLearningRequirements.LOCOMOTION));
    }  
    
    @Test
    public void testBuilderBuild_baseStatsAreSet_theyAreContainedInProduct() {
        ExorionSpeciesBaseStatsAtMaximumLevel speciesBaseStats = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(50)
            .setAttackBaseStat(49)
            .setDefenseBaseStat(48)
            .setSpecialAttackBaseStat(47)
            .setSpecialDefenseBaseStat(46)
            .setAccuracyBaseStat(45)
            .setEvasivenessBaseStat(44)
            .setCriticalHitFrequencyBaseStat(43)
            .setCriticalHitAvoidanceBaseStat(42)
            .setSwiftnessBaseStat(41)
            .build();
        
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate
            .setSpeciesBaseStats(speciesBaseStats)
            .build();
        
        assertThat(product.getSpeciesBaseStats(), is(notNullValue()));
        assertThat(product.getSpeciesBaseStats(), is(equalTo(speciesBaseStats)));
    }
}
