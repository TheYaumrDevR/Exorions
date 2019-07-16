package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.ApplyBattleModifierAbilityEffect;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.BattleModifiedIndividualExorion;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.mocks.TestExorions;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityPowerByLevelTables;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityRequiredLevelTables;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ApplyBattleModifierAbilityEffectTest {

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testUse_nothingIsDecorated_throwsException() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(poison);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        testCandidate.use(attacker, defender);
    }
    
    @Test
    public void testUse_decoratesDummyAbility_throwsNoException() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(poison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        testCandidate.decorate(decoratedAbility);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);     
        
        testCandidate.use(attacker, defender);
    }
    
    @Test
    public void testUse_appliesPoison_poisonIsPutOnVictim() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();        
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(poison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        testCandidate.decorate(decoratedAbility); 
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        BattleModifiedIndividualExorion modifiedTarget = testCandidate.use(attacker, defender);
        
        int defenderOldHP = defender.getBaseStats().getCurrentHealthPoints();
        modifiedTarget.tick(attacker, modifiedTarget);
        int defenderNewHP = defender.getBaseStats().getCurrentHealthPoints();
        
        assertThat(modifiedTarget, is(sameInstance(poison)));
        assertThat(defenderOldHP, is(not(defenderNewHP)));
    }
    
    @Test
    public void testUse_poisonIsAlreadyApplied_returnsAppliedPoison() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        Poison newPoison = new Poison();
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(newPoison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        testCandidate.decorate(decoratedAbility); 
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        poison.applyTo(defender);
        
        BattleModifiedIndividualExorion modifiedTarget = testCandidate.use(attacker, poison);
        
        assertThat(modifiedTarget, is(sameInstance(poison)));
    }

    @Test
    public void testUse_decoratesDirectDamageEffect_bothGetUsed() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        
        DirectDamageAbilityEffect directDamageAbility = new DirectDamageAbilityEffect();
        directDamageAbility.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(poison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        directDamageAbility.decorate(decoratedAbility);
        testCandidate.decorate(directDamageAbility); 
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);  
        
        BattleModifiedIndividualExorion modifiedTarget = testCandidate.use(attacker, defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(51)));
        assertThat(modifiedTarget, is(sameInstance(poison)));
    }     
    
    @Test
    public void testUseDecoratedAbilitiesOnly_decoratedAbilityIsPoisonAndPoisonIsAlreadyApplied_returnsAppliedPoison() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        Poison newPoison = new Poison();
        
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(newPoison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        testCandidate.decorate(decoratedAbility); 
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        poison.applyTo(defender);     
        
        BattleModifiedIndividualExorion modifiedTarget = testCandidate.useDecoratedAbilitiesOnly(attacker, poison);
        
        assertThat(modifiedTarget, is(sameInstance(poison)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetMinimumLevelRequired_decoratesNothing_throwsException() {
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(null);
        
        testCandidate.getMinimumLevelRequired();
    }    
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetAbilityLevel_decoratesNothing_throwsException() {
        ApplyBattleModifierAbilityEffect testCandidate = new ApplyBattleModifierAbilityEffect(null);
        
        testCandidate.getAbilityLevel();
    }
}