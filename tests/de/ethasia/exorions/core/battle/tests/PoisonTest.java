package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.battle.Stagger;
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
        
        testCandidate.tick(attacker, testCandidate);
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(63)));
    }
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testTick_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.tick(null, testCandidate);
    }  
    
    @Test
    public void testGetModifiedAccuracy_decoratesStagger_accuracyIsTakenFromStagger() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        Stagger stagger = new Stagger();
        
        IndividualExorion victim = TestExorions.findExorionById(1); 
        stagger.applyTo(victim);
        testCandidate.applyTo(stagger);
        
        assertThat(testCandidate.getModifiedAccuracy(), is(equalTo(50)));
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
    
    @Test
    public void testGetModifiedSpecialDefense_decoratesStagger_specialDefenseComesFromVictim() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        Stagger stagger = new Stagger();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        stagger.applyTo(victim);
        testCandidate.applyTo(stagger);
        
        assertThat(testCandidate.getModifiedSpecialDefense(), is(equalTo(victim.getModifiedSpecialDefense())));
    }    

    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testGetModifiedSpecialDefense_decoratesNothing_throwsException() {
        Poison testCandidate = new Poison();
        
        testCandidate.getModifiedSpecialDefense();
    } 
    
    @Test
    public void testGetModifiedAttackPower_decoratesStagger_attackPowerComesFromVictim() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        Stagger stagger = new Stagger();
        
        IndividualExorion victim = TestExorions.findExorionById(0);
        
        stagger.applyTo(victim);
        testCandidate.applyTo(stagger);
        
        assertThat(testCandidate.getModifiedAttackPower(), is(equalTo(victim.getModifiedAttackPower())));
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
        
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(attacker, testCandidate);
        assertThat(testCandidate.isActive(), is(true));
        
        testCandidate.tick(attacker, testCandidate);
        assertThat(testCandidate.isActive(), is(false));        
    } 
    
    @Test
    public void testTick_ticksThrice_damageIsAppliedOnlyTwice() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim);  
        
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);
        
        assertThat(victim.getBaseStats().getCurrentHealthPoints(), is(equalTo(58)));
    }
    
    @Test
    public void testApplyTo_poisonIsAlreadyPresentButInactive_getsReactivated() throws NotAllPropertiesAreSetException {
        Poison testCandidate = new Poison();
        Poison secondPoison = new Poison();
        IndividualExorion victim = TestExorions.findExorionById(0);
        IndividualExorion attacker = TestExorions.findExorionById(1);
        
        testCandidate.applyTo(victim); 
        
        testCandidate.tick(attacker, testCandidate);
        testCandidate.tick(attacker, testCandidate);

        assertThat(testCandidate.isActive(), is(equalTo(false)));
        
        secondPoison.applyTo(testCandidate);
        assertThat(testCandidate.isActive(), is(equalTo(true)));
    }   
}