package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbility;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndividualExorionTest {
    
    @Test
    public void testBuilderBuild_speciesIsSet_speciesIsContainedInProduct() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        IndividualExorion product = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        ExorionSpecies speciesInProduct = product.getSpecies();
        
        assertThat(speciesInProduct, is(equalTo(species)));
    }

    @Test
    public void testCanLearnAbility_abilityWithoutRequirements_canLearn() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility.Builder battleAbilityBuilder = new BattleAbility.Builder();
        BattleAbility abilityToLearn = battleAbilityBuilder.build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testCanLearnAbility_abilityWithOneRequirement_cannotLearnBecauseExorionHasNoTail() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    }  
    
    @Test
    public void testCanLearnAbility_abilityWithOneRequirement_canLearnBecauseExorionHasRequirements() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    }   
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_cannotLearnBecauseOneIsNotSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();        
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_canLearnBothAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.CLAWS)
            .build();          
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_canLearnWhenMoreAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TEETH)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TENTACLES)    
            .build();  
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_cannotLearnAsDifferentAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.HARD_BODY)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)   
            .build();        
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBuilderBuild_baseStatsAreSet_createdExorionContainsStats() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder().build();
        
        IndividualExorionBaseStats baseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(baseStats)
            .build();
        
        assertThat(testCandidate.getBaseStats(), is(equalTo(baseStats)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_noPropertiesAreSet_throwsException() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .build();        
    }
}