package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.general.SetValueIsNotWithinLegalBoundsException;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityRequiredLevelTables;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleAbilityBaseTest {

    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_createdAbilityNotNull() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_nothingIsSet_damageTypesAreEmpty() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product.getDamageTypes().size(), is(0));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_oneDamageTypeIsSet_damageTypeIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate.setDamageType(DamageTypes.GROUND)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product.getDamageTypes().size(), is(3));
        assertThat(product.getDamageTypes(), hasItems(DamageTypes.INFECTION, DamageTypes.SQUASH, DamageTypes.WIND));
    }    
    
    @Test
    public void testBattleAbilityBuilderBuild_noLearningRequirementsAreSet_learningRequirementsAreEmpty() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product.getLearningRequirements().size(), is(0));
    }  
    
    @Test
    public void testBattleAbilityBuilderBuild_oneLearningAbilityIsSet_learningAbilityIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product.getDelayMultiplier(), is(equalTo(1.0f)));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_setName_nameIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
       assertThat(product.getName(), is(equalTo("Foosh")));
    }
    
    @Test
    public void testBattleAbilityBuilderBuild_setRequiredPowerPointsForStageTwo_requiredPowerPointsAreInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredPowerPointsForStageTwo(2)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();     
        
        assertThat(product.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
    }  
    
    @Test
    public void testBuilderBuild_setAbilityLevel_abilityLevelIsInProduct() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setAbilityLevel(7)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        assertThat(product.getAbilityLevel(), is(equalTo(7)));
    }
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testBuilderBuild_setAbilityLevelIsOutsideLegalBounds_throwsException() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setAbilityLevel(21)
            .build();        
    }
    
    @Test(expected = SetValueIsNotWithinLegalBoundsException.class)
    public void testBuilderBuild_setAbilityLevelIsNegative_throwsException() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setAbilityLevel(0)
            .build();        
    }   
    
    @Test
    public void testBuilderBuild_setLevelRequirementByAbilityLevel_getMinimumRequiredLevelReturnsCorrectRequirements() {
        Map<Integer, Integer> requiredLevelByAbilityLevel = BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility();
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredLevelByAbilityLevel(requiredLevelByAbilityLevel)
            .setAbilityLevel(1)
            .build(); 
        
        assertThat(product.getMinimumLevelRequired(), is(equalTo(requiredLevelByAbilityLevel.get(1))));
    }
    
    @Test
    public void testGetMinimumLevelRequired_basicLevelRequirementTableIsSet_valueIsTakenFromTable() {
        Map<Integer, Integer> requiredLevelByAbilityLevel = BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility();
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
        
        BattleAbilityBase product = testCandidate
            .setRequiredLevelByAbilityLevel(requiredLevelByAbilityLevel)
            .setAbilityLevel(3)
            .build();
        
        assertThat(product.getMinimumLevelRequired(), is(equalTo(requiredLevelByAbilityLevel.get(3))));
    }  
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_abilityLevelRequirementTableIsNotSet_throwsException() {
        BattleAbilityBase.Builder testCandidate = new BattleAbilityBase.Builder();
       
        BattleAbilityBase product = testCandidate
            .setAbilityLevel(3)
            .build();        
    }
}