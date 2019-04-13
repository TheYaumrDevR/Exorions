package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbilityBase;
import de.ethasia.exorions.core.DamageTypes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleAbilityBaseTest {

    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_createdAbilityNotNull() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate.build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_damageTypesAreEmpty() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate.build();
        
        assertThat(product.getDamageTypes().size(), is(0));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_oneDamageTypeIsSet_damageTypeIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate.setDamageType(DamageTypes.GROUND)
            .build();
        
        assertThat(product.getDamageTypes().size(), is(1));
        assertThat(product.getDamageTypes(), hasItems(DamageTypes.GROUND));
    } 

    @Test
    public void testBattleAbilityBuilderBuild_threeDamageTypesAreSet_damageTypesAreInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setDamageType(DamageTypes.INFECTION)
            .setDamageType(DamageTypes.SQUASH)
            .setDamageType(DamageTypes.WIND)
            .build();
        
        assertThat(product.getDamageTypes().size(), is(3));
        assertThat(product.getDamageTypes(), hasItems(DamageTypes.INFECTION, DamageTypes.SQUASH, DamageTypes.WIND));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_noLearningRequirementsAreSet_learningRequirementsAreEmpty() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate.build();
        
        assertThat(product.getLearningRequirements().size(), is(0));
    }  
    
    @Test
    public void testBattleAbilityBuilderBuild_oneLearningAbilityIsSet_learningAbilityIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        assertThat(product.getLearningRequirements().size(), is(1));
        assertThat(product.getLearningRequirements(), hasItems(AbilityLearningRequirements.TAIL));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_fourLearningRequirementsAreSet_requirementsAreInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
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

    @Test
    public void testBattleAbilityBuilderBuild_delayMultiplierIsSet_valueIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setDelayMultiplier(1.0f)
            .build();
        
        assertThat(product.getDelayMultiplier(), is(equalTo(1.0f)));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_setName_nameIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setName("Foosh")
            .build();
        
       assertThat(product.getName(), is(equalTo("Foosh")));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_setRequiredPowerPointsForStageTwo_requiredPowerPointsAreInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredPowerPointsForStageTwo(2)
            .build();     
        
        assertThat(product.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
    }
}