package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.mocks.TestExorions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PoisonTest {
    
    @Test 
    public void testTick_ticksOnce_exorionLosesHealth() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0); 
        IndividualExorion attacker = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        testCandidate.setAttackerBaseStats(attacker.getBaseStats());
        
        testCandidate.tick(testCandidate);
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTick_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.tick(testCandidate);
    }  
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAccuracy_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedAccuracy();
    } 
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedDefense_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedDefense();
    }

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedSpecialDefense();
    } 

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedAttackPower_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        testCandidate.getModifiedAttackPower();
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTakeDamage_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.takeDamage(7);
    }   
    
    @Test
    public void testGetModifiedDefense_decoratesExorion_defenseComesFromExorion() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(1);
        testCandidate.applyTo(victim);
        
        assertThat(victim.getModifiedDefense(), is(equalTo(testCandidate.getModifiedDefense())));
    }
    
    @Test
    public void testTakeDamage_damageIsFive_decoratedExorionHasFiveLessHealth() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        testCandidate.applyTo(victim);
        testCandidate.takeDamage(5);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }     
    
    @Test
    public void testTick_ticksTwice_getsInactivated() throws NotAllPropertiesAreSetException {
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
    public void testTick_ticksThrice_damageIsAppliedOnlyTwice() throws NotAllPropertiesAreSetException {
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
    public void testApplyTo_poisonIsAlreadyPresentButInactive_getsReactivated() throws NotAllPropertiesAreSetException {
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