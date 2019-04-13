package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbilityBase;
import de.ethasia.exorions.core.DamageTypes;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import de.ethasia.exorions.core.DirectDamageAbilityEffect;
import java.util.Set;

public class DirectDamageAbilityEffectTest {
    
    @Test
    public void testGetName_doesNotDecorateAnything_nameIsEmpty() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        String name = testCandidate.getName();
        
        assertThat(name, is(equalTo("")));
    }
    
    @Test
    public void testGetDamageTypes_doesNotDecorateAnything_damageTypesAreEmpty() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<DamageTypes> damageTypes = testCandidate.getDamageTypes();
        
        assertThat(damageTypes.isEmpty(), is(true));  
    }
    
    @Test
    public void testGetLearningRequirements_doesNotDecorateAnything_learningRequirementsAreEmpty() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<AbilityLearningRequirements> learningRequirements = testCandidate.getLearningRequirements();
        
        assertThat(learningRequirements.isEmpty(), is(true));          
    }   
    
    @Test
    public void testGetDelayMultiplier_doesNotDecorateAnything_multiplierIsOne() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        float delayMultiplier = testCandidate.getDelayMultiplier();
        
        assertThat(delayMultiplier, is(equalTo(1.0f)));          
    } 

    @Test
    public void testGetRequiredPowerPointsForStageTwo_doesNotDecorateAnything_requiredPowerPointsAreZero() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        int powerPointsRequiredForStageTwo = testCandidate.getRequiredPowerPointsForStageTwo();
        
        assertThat(powerPointsRequiredForStageTwo, is(equalTo(0)));          
    } 

    @Test
    public void testDecorate_decoratesBaseAbility_nameIsTakenFromBaseAbility() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .build();
        
        testCandidate.decorate(decoratedAbility);
        
        String name = testCandidate.getName();
        
        assertThat(name, is(equalTo("Foosh")));
    }
}