package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.battle.Stagger;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.mocks.TestExorions;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndividualExorionBattleModifiersTest {
    
    @Test
    public void testStaggerApplyTo_getModifiedAccuracy_accuracyIsReducedByTenPercent() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
    }
    
    @Test
    public void testStaggerTick_ticksTwice_getsInactivated() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(false));        
    }
    
    @Test 
    public void testStaggerTick_ticksTwice_staggerDoesNotApplyDebuffAnymore() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(63)));        
    }
    
    @Test 
    public void testStaggerTakeDamage_fiveDamageIsSet_decoratedTakesFiveDamage() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        testCandidate.applyTo(victim);
        
        testCandidate.takeDamage(5);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testStaggerGetModifiedAccuracy_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAccuracy();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testStaggerGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedSpecialDefense();
    }

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testStaggerTakeDamage_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.takeDamage(5);
    } 
    
    @Test
    public void testStaggerApplyTo_staggerIsAlreadyPresentButInactive_getsReactivated() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        Stagger secondStagger = new Stagger();
        Poison firstDecorator = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0); 
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);
        firstDecorator.applyTo(testCandidate);
        firstDecorator.setAttackerBaseStats(attacker.getBaseStats());
        
        firstDecorator.tick(firstDecorator);
        firstDecorator.tick(firstDecorator);
        
        assertThat(testCandidate.isActive(), is(equalTo(false)));
        
        secondStagger.applyTo(firstDecorator);
        assertThat(testCandidate.isActive(), is(equalTo(true)));
    }
    
    @Test 
    public void testPoisonTick_ticksOnce_exorionLosesHealth() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0); 
        IndividualExorion attacker = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        testCandidate.setAttackerBaseStats(attacker.getBaseStats());
        
        testCandidate.tick(testCandidate);
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testPoisonTick_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.tick(testCandidate);
    }  
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testPoisonGetModifiedAccuracy_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedAccuracy();
    } 

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testPoisonGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedSpecialDefense();
    }     
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testPoisonTakeDamage_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.takeDamage(7);
    }   
    
    @Test
    public void testPoisonTakeDamage_damageIsFive_decoratedExorionHasFiveLessHealth() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        testCandidate.takeDamage(5);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }     
    
    @Test
    public void testPoisonTick_ticksTwice_getsInactivated() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);
        testCandidate.setAttackerBaseStats(attacker.getBaseStats());
        
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(false));        
    } 
    
    @Test
    public void testPoisonTick_ticksThrice_damageIsAppliedOnlyTwice() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);
        testCandidate.setAttackerBaseStats(attacker.getBaseStats());   
        
        testCandidate.tick(testCandidate);
        testCandidate.tick(testCandidate);
        testCandidate.tick(testCandidate);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(58)));
    }
    
    @Test
    public void testPoisonApplyTo_poisonIsAlreadyPresentButInactive_getsReactivated() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        Poison secondPoison = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);
        testCandidate.setAttackerBaseStats(attacker.getBaseStats());   
        
        testCandidate.tick(testCandidate);
        testCandidate.tick(testCandidate);

        assertThat(testCandidate.isActive(), is(equalTo(false)));
        
        secondPoison.applyTo(testCandidate);
        assertThat(testCandidate.isActive(), is(equalTo(true)));
    }
}