package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbility;
import de.ethasia.exorions.core.DamageTypes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleAbilityTest {

    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_createdAbilityNotNull() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate.build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_damageTypesAreEmpty() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate.build();
        
        assertThat(product.getDamageTypes().size(), is(0));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_oneDamageTypeIsSet_damageTypeIsInProduct() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate.setDamageType(DamageTypes.GROUND)
            .build();
        
        assertThat(product.getDamageTypes().size(), is(1));
        assertThat(product.getDamageTypes(), hasItems(DamageTypes.GROUND));
    } 

    @Test
    public void testBattleAbilityBuilderBuild_threeDamageTypesAreSet_damageTypesAreInProduct() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate
            .setDamageType(DamageTypes.INFECTION)
            .setDamageType(DamageTypes.SQUASH)
            .setDamageType(DamageTypes.WIND)
            .build();
        
        assertThat(product.getDamageTypes().size(), is(3));
        assertThat(product.getDamageTypes(), hasItems(DamageTypes.INFECTION, DamageTypes.SQUASH, DamageTypes.WIND));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_noLearningRequirementsAreSet_learningRequirementsAreEmpty() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate.build();
        
        assertThat(product.getLearningRequirements().size(), is(0));
    }  
    
    @Test
    public void testBattleAbilityBuilderBuild_oneLearningAbilityIsSet_learningAbilityIsInProduct() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        assertThat(product.getLearningRequirements().size(), is(1));
        assertThat(product.getLearningRequirements(), hasItems(AbilityLearningRequirements.TAIL));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_fourLearningRequirementsAreSet_requirementsAreInProduct() {
        BattleAbility.Builder testCandidate = new BattleAbility.Builder();
        
        BattleAbility product = testCandidate
            .setLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .build();
        
        assertThat(product.getLearningRequirements().size(), is(4));
        assertThat(product.getLearningRequirements(), hasItems(AbilityLearningRequirements.LOCOMOTION,
            AbilityLearningRequirements.TENTACLES,
            AbilityLearningRequirements.CLAWS,
            AbilityLearningRequirements.NEEDLES));
    }     
}