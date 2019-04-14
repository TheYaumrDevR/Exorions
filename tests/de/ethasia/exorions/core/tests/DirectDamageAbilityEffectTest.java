package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbilityBase;
import de.ethasia.exorions.core.BattleAbilityEffectMustDecorateBattleAbilityException;
import de.ethasia.exorions.core.DamageTypes;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import de.ethasia.exorions.core.DirectDamageAbilityEffect;
import java.util.Set;

public class DirectDamageAbilityEffectTest {
    
    @Test(expected = BattleAbilityEffectMustDecorateBattleAbilityException.class)
    public void testGetName_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        String name = testCandidate.getName();
    }
    
    @Test(expected = BattleAbilityEffectMustDecorateBattleAbilityException.class)
    public void testGetDamageTypes_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<DamageTypes> damageTypes = testCandidate.getDamageTypes();
    }
    
    @Test(expected = BattleAbilityEffectMustDecorateBattleAbilityException.class)
    public void testGetLearningRequirements_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<AbilityLearningRequirements> learningRequirements = testCandidate.getLearningRequirements();          
    }   
    
    @Test(expected = BattleAbilityEffectMustDecorateBattleAbilityException.class)
    public void testGetDelayMultiplier_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        float delayMultiplier = testCandidate.getDelayMultiplier();      
    } 

    @Test(expected = BattleAbilityEffectMustDecorateBattleAbilityException.class)
    public void testGetRequiredPowerPointsForStageTwo_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        int powerPointsRequiredForStageTwo = testCandidate.getRequiredPowerPointsForStageTwo();         
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
    
    @Test
    public void testDecorate_decoratesBaseAbility_damageTypesAreTakenFromBaseAbility() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setDamageType(DamageTypes.DRYING)
            .setDamageType(DamageTypes.RIP)
            .setDamageType(DamageTypes.SHATTER)
            .build();
        
        testCandidate.decorate(decoratedAbility);
        
        Set<DamageTypes> damageTypes = testCandidate.getDamageTypes();
        assertThat(damageTypes.size(), is(3));
        assertThat(damageTypes, hasItems(DamageTypes.DRYING, DamageTypes.RIP, DamageTypes.SHATTER));        
    }    
    
    @Test
    public void testDecorate_decoratesBaseAbility_learningRequirementsAreTakenFromBaseAbility() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .build();
        
        testCandidate.decorate(decoratedAbility);
        
        Set<AbilityLearningRequirements> learningRequirements = testCandidate.getLearningRequirements();  
        assertThat(learningRequirements.size(), is(1));
        assertThat(learningRequirements, hasItems(AbilityLearningRequirements.TEETH));        
    }   
    
    @Test
    public void testDecorate_decoratesBaseAbility_delayMultiplierIsTakenFromBaseAbility() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setDelayMultiplier(1.2f)
            .build();
        
        testCandidate.decorate(decoratedAbility);
        
        float delayMultiplier = testCandidate.getDelayMultiplier(); 
        assertThat(delayMultiplier, is(equalTo(1.2f)));
    }   
    
    @Test
    public void testDecorate_decoratesBaseAbility_requiredPowerPointsForStageTwoAreTakenFromBaseAbility() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setRequiredPowerPointsForStageTwo(2)
            .build();
        
        testCandidate.decorate(decoratedAbility);
        
        int powerPointsRequiredForStageTwo = testCandidate.getRequiredPowerPointsForStageTwo();       
        assertThat(powerPointsRequiredForStageTwo, is(equalTo(2)));
    }     
}