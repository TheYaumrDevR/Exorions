package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.Bleed;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.battle.Stagger;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.mocks.TestExorions;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BleedTest {
    
    @Test
    public void testGetModifiedAccuracy_decoratesStagger_accuracyIsTakenFromStagger() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        Stagger stagger = new Stagger();
        
        IndividualExorion victim = TestExorions.findExorionById(1); 
        stagger.applyTo(victim);
        testCandidate.applyTo(stagger);
        
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
    }    
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAccuracy_decoratesNothing_throwsException() {
        Bleed testCandidate = new Bleed();
        
        testCandidate.getModifiedAccuracy();
    }  
    
    @Test
    public void testGetModifiedAttackPower_decoratesPoison_attackPowerComesFromVictim() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        poison.applyTo(victim);
        testCandidate.applyTo(poison);
        
        assertThat(testCandidate.getModifiedAttackPower(), is(equalTo(victim.getModifiedAttackPower())));
    }    
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAttackPower_decoratesNothing_throwsException() {
        Bleed testCandidate = new Bleed();
        
        testCandidate.getModifiedAttackPower();
    }  
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedDefense_decoratesNothing_throwsException() {
        Bleed testCandidate = new Bleed();
        
        testCandidate.getModifiedDefense();
    }  
    
    @Test
    public void testGetModifiedSpecialDefense_decoratesPoison_specialDefenseComesFromVictim() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        poison.applyTo(victim);
        testCandidate.applyTo(poison);
        
        assertThat(testCandidate.getModifiedSpecialDefense(), is(equalTo(victim.getModifiedSpecialDefense())));
    }      
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Bleed testCandidate = new Bleed();
        
        testCandidate.getModifiedSpecialDefense();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTakeDamage_nothingIsDecorated_throwsException() {
        Bleed testCandidate = new Bleed();
        testCandidate.takeDamage(1);
    }
    
    @Test
    public void testGetModifiedDefense_decoratesExorion_defenseComesFromExorion() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        IndividualExorion victim = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        
        assertThat(victim.getModifiedDefense(), is(equalTo(testCandidate.getModifiedDefense())));
    }    
    
    @Test
    public void testTakeDamage_exorionIsDebuffed_exorionTakesDamage() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        IndividualExorion victim = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        
        testCandidate.takeDamage(2);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(71)));
    }
    
    @Test
    public void testSetAttackPower_bleedTicks_damageIsTakenBasedOnAttackPower() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);        
        
        testCandidate.applyTo(victim);
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());
        
        BattleCalculator battleCalculator = new BattleCalculator();
        int bleedAttackPower = Math.round(attacker.getBaseStats().getAttackValue() / 4.f);
        int expectedTickDamage = battleCalculator.calculateDamageFromAttackAndDefense(bleedAttackPower, victim.getBaseStats().getDefenseValue());
        int expectedHealthAfterTick = victim.getBaseStats().getMaximumHealthPoints() - expectedTickDamage;
        
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedHealthAfterTick)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTick_decoratesNothing_throwsException() {
        Bleed testCandidate = new Bleed();
        testCandidate.tick(null, testCandidate);
    }
    
    @Test
    public void testTick_ticksThrive_getsDeactivated() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);    
        
        testCandidate.applyTo(victim);
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());

        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(testCandidate.isActive(), is(equalTo(true)));
        
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(testCandidate.isActive(), is(equalTo(false)));
    }
    
    @Test
    public void testTick_ticksFourTimes_damageIsDoneThrice() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);    
        
        testCandidate.applyTo(victim);
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());

        BattleCalculator battleCalculator = new BattleCalculator();
        int bleedAttackPower = Math.round(attacker.getBaseStats().getAttackValue() / 4.f);
        int expectedTickDamage = battleCalculator.calculateDamageFromAttackAndDefense(bleedAttackPower, victim.getBaseStats().getDefenseValue());
        int expectedHealthAfterTick = victim.getBaseStats().getMaximumHealthPoints() - 3 * expectedTickDamage;        
        
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedHealthAfterTick)));        
    }    
    
    @Test
    public void testBleedAndPoisonTick_tickOnce_bothDoDamage() throws NotAllPropertiesAreSetException {
        Bleed testCandidate = new Bleed();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);   
        
        poison.applyTo(victim);
        testCandidate.applyTo(poison);
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());  
        
        BattleCalculator battleCalculator = new BattleCalculator();
        int bleedAttackPower = Math.round(attacker.getBaseStats().getAttackValue() / 4.f);
        int poisonAttackPower = Math.round(attacker.getBaseStats().getSpecialAttackValue() / 3.f);
        int expectedBleedTickDamage = battleCalculator.calculateDamageFromAttackAndDefense(bleedAttackPower, victim.getBaseStats().getDefenseValue());
        int expectedPoisonTickDamage = battleCalculator.calculateDamageFromAttackAndDefense(poisonAttackPower, victim.getBaseStats().getSpecialDefenseValue());
        int expectedHealthAfterTick = victim.getBaseStats().getMaximumHealthPoints() - expectedBleedTickDamage - expectedPoisonTickDamage;           
        
        testCandidate.tick(attacker, testCandidate);   
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedHealthAfterTick)));        
    }
    
    @Test
    public void testApplyTo_anotherBleedDebuffIsApplied_getsReapplied() throws NotAllPropertiesAreSetException {
        Bleed bleedOne = new Bleed();
        Bleed testCandidate = new Bleed();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);   
        
        bleedOne.applyTo(victim);
        poison.applyTo(bleedOne);
        
        bleedOne.setDefenseValueToBaseDamageOn(victim.getModifiedDefense()); 
        
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());          

        poison.tick(attacker, poison);
        poison.tick(attacker, poison);
        
        assertThat(bleedOne.isActive(), is(equalTo(true)));
        
        testCandidate.applyTo(poison);
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(bleedOne.isActive(), is(equalTo(true)));  
        
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(bleedOne.isActive(), is(equalTo(false)));
    }
    
    @Test
    public void testApplyTo_thirdBleedIsApplied_otherTwoBleedsAreReapplied() throws NotAllPropertiesAreSetException {
        Bleed bleedOne = new Bleed();
        Bleed bleedTwo = new Bleed();   
        Bleed testCandidate = new Bleed();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);    
        
        bleedOne.applyTo(victim);
        bleedTwo.applyTo(bleedOne);
        poison.applyTo(bleedTwo);

        bleedOne.setDefenseValueToBaseDamageOn(victim.getModifiedDefense()); 

        bleedTwo.setDefenseValueToBaseDamageOn(victim.getModifiedDefense()); 
        
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());          
        
        bleedTwo.tick(attacker, bleedTwo);
        bleedTwo.tick(attacker, bleedTwo);
        
        testCandidate.applyTo(poison);
        
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(bleedOne.isActive(), is(equalTo(true)));   
        assertThat(bleedTwo.isActive(), is(equalTo(true)));
        assertThat(testCandidate.isActive(), is(equalTo(true)));   
    }
    
    @Test
    public void testApplyTo_inActiveBleedIsPresent_doesNotGetReapplied() throws NotAllPropertiesAreSetException {
        Bleed bleedOne = new Bleed();
        Bleed testCandidate = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);     
        
        bleedOne.applyTo(victim);    
        
        bleedOne.setDefenseValueToBaseDamageOn(victim.getModifiedDefense()); 
        
        testCandidate.setDefenseValueToBaseDamageOn(victim.getModifiedDefense());     
        
        bleedOne.tick(attacker, bleedOne);   
        bleedOne.tick(attacker, bleedOne); 
        bleedOne.tick(attacker, bleedOne); 
        
        assertThat(bleedOne.isActive(), is(equalTo(false))); 
        
        testCandidate.applyTo(bleedOne); 
        
        assertThat(bleedOne.isActive(), is(equalTo(false))); 
    }    
}