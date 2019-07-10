package de.ethasia.exorions.core.battle.tests;

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

public class StaggerTest {
    
    @Test
    public void testApplyTo_getModifiedAccuracy_accuracyIsReducedByTenPercent() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
    }
    
    @Test
    public void testTick_ticksTwice_getsInactivated() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(null, testCandidate);
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(null, testCandidate);
        assertThat(testCandidate.isActive(), is(false));        
    }
    
    @Test 
    public void testTick_ticksTwice_staggerDoesNotApplyDebuffAnymore() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
        
        testCandidate.tick(null, testCandidate);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
        
        testCandidate.tick(null, testCandidate);
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(63)));        
    }
    
    @Test 
    public void testTakeDamage_fiveDamageIsSet_decoratedTakesFiveDamage() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        testCandidate.applyTo(victim);
        
        testCandidate.takeDamage(5);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test
    public void testTakeDamage_victimIsAffectedByPoisonAndStagger_victimTakesSetDamage() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        Poison poison = new Poison();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        poison.applyTo(victim);
        testCandidate.applyTo(poison);
        
        testCandidate.takeDamage(6);
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(62)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAccuracy_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAccuracy();
    }
    
    @Test
    public void testGetModifiedAttackPower_decoratesBleed_attackPowerComesFromVictim() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        Bleed bleed = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        bleed.applyTo(victim);
        testCandidate.applyTo(bleed);
        
        assertThat(testCandidate.getModifiedAttackPower(), is(equalTo(victim.getModifiedAttackPower())));
    }     
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAttackPower_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAttackPower();
    } 
    
    @Test
    public void testGetModifiedSpecialDefense_decoratesBleed_specialDefenseComesFromVictim() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        Bleed bleed = new Bleed();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        bleed.applyTo(victim);
        testCandidate.applyTo(bleed);
        
        assertThat(testCandidate.getModifiedSpecialDefense(), is(equalTo(victim.getModifiedSpecialDefense())));
    }     
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedDefense_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedDefense();
    }    
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedSpecialDefense();
    }
    
    @Test
    public void testGetModifiedDefense_decoratesExorion_defenseComesFromExorion() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        
        assertThat(victim.getModifiedDefense(), is(equalTo(testCandidate.getModifiedDefense())));
    }

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTakeDamage_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.takeDamage(5);
    } 
    
    @Test
    public void testApplyTo_staggerIsAlreadyPresentButInactive_getsReactivated() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        Stagger secondStagger = new Stagger();
        Poison firstDecorator = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0); 
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);
        firstDecorator.applyTo(testCandidate);
        
        firstDecorator.tick(attacker, firstDecorator);
        firstDecorator.tick(attacker, firstDecorator);
        
        assertThat(testCandidate.isActive(), is(equalTo(false)));
        
        secondStagger.applyTo(firstDecorator);
        assertThat(testCandidate.isActive(), is(equalTo(true)));
    } 
}