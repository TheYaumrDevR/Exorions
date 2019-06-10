package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.mocks.MockGenome;
import de.ethasia.exorions.core.tests.IndividualExorionTest;

import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DirectDamageAbilityEffectTest {
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetName_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        String name = testCandidate.getName();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetDamageTypes_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<DamageTypes> damageTypes = testCandidate.getDamageTypes();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetLearningRequirements_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        Set<AbilityLearningRequirements> learningRequirements = testCandidate.getLearningRequirements();          
    }   
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetDelayMultiplier_doesNotDecorateAnything_throwsException() {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        
        float delayMultiplier = testCandidate.getDelayMultiplier();      
    } 

    @Test(expected = DecoratorMustDecorateSomethingException.class)
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
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testUse_decoratedAbilityIsNotSet_throwsException() throws NotAllPropertiesAreSetException {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        IndividualExorion attacker = createIndividualExorionForTesting();
        IndividualExorion defender = createIndividualExorionForTesting();
        
        testCandidate.use(attacker, defender);
    }
    
    @Test
    public void testUse_decoratedAbilityIsSet_damageIsDoneToDefender() throws NotAllPropertiesAreSetException {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        IndividualExorion attacker = createIndividualExorionForTesting();
        IndividualExorion defender = createIndividualExorionForTesting();
        
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .build();
        testCandidate.decorate(decoratedAbility);
        
        testCandidate.use(attacker, defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(32)));
    }   
    
    @Test
    public void testUse_decoratedAbilityIsDamageAbility_damageIsDoneTwice() throws NotAllPropertiesAreSetException {
        DirectDamageAbilityEffect testCandidate = new DirectDamageAbilityEffect();
        DirectDamageAbilityEffect decorated = new DirectDamageAbilityEffect();
        IndividualExorion attacker = createIndividualExorionForTesting();
        IndividualExorion defender = createIndividualExorionForTesting();
        
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .build();
        decorated.decorate(decoratedAbility);
        testCandidate.decorate(decorated);
        
        testCandidate.use(attacker, defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(1)));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private IndividualExorion createIndividualExorionForTesting() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(IndividualExorionTest.createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion result = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setGenome(new MockGenome(0))
            .setLevel(49)
            .build(); 

        result.levelUpBy(1);
        
        return result;
    }
    
    //</editor-fold>
}