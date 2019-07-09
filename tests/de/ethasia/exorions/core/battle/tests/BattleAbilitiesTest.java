package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.BattleAbilities;
import de.ethasia.exorions.core.battle.BattleAbility;
import de.ethasia.exorions.core.battle.BattleModifiedIndividualExorion;
import de.ethasia.exorions.core.general.PersistedEntityNotFoundException;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;
import de.ethasia.exorions.core.mocks.TestExorions;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class BattleAbilitiesTest {
    
    @BeforeClass
    public static void initDependencies() {
        CoreClassesFactory.setInstance(new MockCoreClassesFactory());
    }   
    
    @Before
    public void resetSharedStates() {
        MockCoreClassesFactory mockClassesFactory = new MockCoreClassesFactory();
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)mockClassesFactory.getRandomNumberGeneratorSingletonInstance();
        rngMock.reset();
    }    

    @Test(expected = PersistedEntityNotFoundException.class)
    public void testFindAbilityById_idIsInvalid_throwsException() {
        BattleAbility invalid = BattleAbilities.findAbilityById(-1);
    }
    
    @Test
    public void testFindAbilityById_getsRamAndRngIsSetToNotApplyStagger_dealsDamageOnly() throws NotAllPropertiesAreSetException {
        BattleAbility ram = BattleAbilities.findAbilityById(0);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {2001, 2000});         
        
        BattleModifiedIndividualExorion modifiedDefender = ram.use(attacker, defender);       
        
        assertThat(ram.getName(), is(equalTo("Ram")));
        assertThat(ram.getDamageTypes(), hasItems(DamageTypes.BLUNT, DamageTypes.SHATTER));
        assertThat(ram.getLearningRequirements(), hasItems(AbilityLearningRequirements.LOCOMOTION, AbilityLearningRequirements.HORNS));
        assertThat(ram.getDelayMultiplier(), is(equalTo(1.2f)));
        assertThat(ram.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(47)));
        assertThat(modifiedDefender.getModifiedAccuracy(), is(equalTo(63)));
    }
    
    @Test
    public void testFindAbilityById_getsRamAndRngIsSetToApplyStagger_dealsDamageAndAppliesStagger() throws NotAllPropertiesAreSetException {
        BattleAbility ram = BattleAbilities.findAbilityById(0);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {1009, 2000});         
        
        BattleModifiedIndividualExorion modifiedDefender = ram.use(attacker, defender);       
        
        assertThat(ram.getName(), is(equalTo("Ram")));
        assertThat(ram.getDamageTypes(), hasItems(DamageTypes.BLUNT, DamageTypes.SHATTER));
        assertThat(ram.getLearningRequirements(), hasItems(AbilityLearningRequirements.LOCOMOTION, AbilityLearningRequirements.HORNS));
        assertThat(ram.getDelayMultiplier(), is(equalTo(1.2f)));
        assertThat(ram.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(47)));
        assertThat(modifiedDefender.getModifiedAccuracy(), is(equalTo(50)));
    }   
    
    @Test
    public void testFindAbilityById_getsBiteAndRngIsNotSetToApplyPoison_dealsDamageOnly() throws NotAllPropertiesAreSetException {
        BattleAbility bite = BattleAbilities.findAbilityById(1);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {2004, 2000});         
        
        BattleModifiedIndividualExorion modifiedDefender = bite.use(attacker, defender);
        modifiedDefender.tick(attacker, modifiedDefender);
        
        assertThat(bite.getName(), is(equalTo("Bite")));
        assertThat(bite.getDamageTypes(), hasItems(DamageTypes.SQUEEZE, DamageTypes.RIP, DamageTypes.INFECTION));
        assertThat(bite.getLearningRequirements(), hasItems(AbilityLearningRequirements.TEETH));
        assertThat(bite.getDelayMultiplier(), is(equalTo(1.1f)));
        assertThat(bite.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(47)));
    }
    
    @Test
    public void testFindAbilityById_getsBiteAndRngIsSetToApplyPoison_dealsDamageAndTicksPoisonDamage() throws NotAllPropertiesAreSetException {
        BattleAbility bite = BattleAbilities.findAbilityById(1);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {2000, 199});         
        
        BattleModifiedIndividualExorion modifiedDefender = bite.use(attacker, defender);
        modifiedDefender.tick(attacker, modifiedDefender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(42)));
    }    
    
    @Test
    public void testFindAbilityById_getsClawSwipeAndRngIsSetToApplyBleed_dealsDirectAndBleedDamage() throws NotAllPropertiesAreSetException {
        BattleAbility clawSwipe = BattleAbilities.findAbilityById(2);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);      
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {573, 199}); 
        
        BattleModifiedIndividualExorion modifiedDefender = clawSwipe.use(attacker, defender);
        modifiedDefender.tick(attacker, modifiedDefender);
        
        assertThat(clawSwipe.getName(), is(equalTo("Claw Swipe")));
        assertThat(clawSwipe.getDamageTypes(), hasItems(DamageTypes.CUT));
        assertThat(clawSwipe.getLearningRequirements(), hasItems(AbilityLearningRequirements.CLAWS));
        assertThat(clawSwipe.getDelayMultiplier(), is(equalTo(1.0f)));
        assertThat(clawSwipe.getRequiredPowerPointsForStageTwo(), is(equalTo(2)));
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(31)));
    }
    
    @Test
    public void testFindAbilityById_getsClawSwipeAndRngIsNotSetToApplyBleed_dealsDirectOnly() throws NotAllPropertiesAreSetException {
        BattleAbility clawSwipe = BattleAbilities.findAbilityById(2);
        
        IndividualExorion attacker = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);      
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {1501, 199}); 
        
        BattleModifiedIndividualExorion modifiedDefender = clawSwipe.use(attacker, defender);
        modifiedDefender.tick(attacker, modifiedDefender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(47)));
    }    
}