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
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(testCandidate);
        assertThat(testCandidate.isActive(), is(false));        
    }
    
    @Test 
    public void testTick_ticksTwice_staggerDoesNotApplyDebuffAnymore() throws NotAllPropertiesAreSetException {
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
    public void testTakeDamage_fiveDamageIsSet_decoratedTakesFiveDamage() throws NotAllPropertiesAreSetException {
        Stagger testCandidate = new Stagger();
        IndividualExorion victim = TestExorions.findExorionById(0);
        testCandidate.applyTo(victim);
        
        testCandidate.takeDamage(5);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAccuracy_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAccuracy();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAttackPower_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAttackPower();
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
        firstDecorator.setAttackerBaseStats(attacker.getBaseStats());
        
        firstDecorator.tick(firstDecorator);
        firstDecorator.tick(firstDecorator);
        
        assertThat(testCandidate.isActive(), is(equalTo(false)));
        
        secondStagger.applyTo(firstDecorator);
        assertThat(testCandidate.isActive(), is(equalTo(true)));
    }    
}