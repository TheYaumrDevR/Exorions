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
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testStaggerGetModifiedAccuracy_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAccuracy();
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
}