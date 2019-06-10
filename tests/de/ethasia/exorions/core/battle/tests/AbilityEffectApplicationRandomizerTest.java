package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.battle.AbilityEffectApplicationRandomizer;
import de.ethasia.exorions.core.battle.ApplyBattleModifierAbilityEffect;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.BattleModifiedIndividualExorion;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;
import de.ethasia.exorions.core.mocks.TestExorions;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;

public class AbilityEffectApplicationRandomizerTest {
    
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
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testUse_nothingIsDecorated_throwsException() throws NotAllPropertiesAreSetException {
        AbilityEffectApplicationRandomizer testCandidate = new AbilityEffectApplicationRandomizer();
        
        IndividualExorion source = TestExorions.findExorionById(1);
        IndividualExorion target = TestExorions.findExorionById(0);
        
        testCandidate.use(source, target);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetApplyChanceInPerThenThousand_chanceIsBelowZero_throwsException() {
        AbilityEffectApplicationRandomizer testCandidate = new AbilityEffectApplicationRandomizer();
        testCandidate.setApplyChanceInPerTenThousand(-1);  
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetApplyChanceInPerThenThousand_chanceIsAboveTenThousand_throwsException() {
        AbilityEffectApplicationRandomizer testCandidate = new AbilityEffectApplicationRandomizer();
        testCandidate.setApplyChanceInPerTenThousand(10001);  
    }    
    
    @Test
    public void testUse_decoratesPoisonAndChanceIsTenPercent_poisonIsAppliedBasedOnRng() throws NotAllPropertiesAreSetException {
        Poison poison = new Poison();
        
        ApplyBattleModifierAbilityEffect poisonApplier = new ApplyBattleModifierAbilityEffect(poison);
        BattleAbilityBase decoratedAbility = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .build();
        poisonApplier.decorate(decoratedAbility);   
        
        IndividualExorion source = TestExorions.findExorionById(1);
        IndividualExorion target = TestExorions.findExorionById(0);         
        
        poison.setAttackerBaseStats(source.getBaseStats());
        
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setIntegerSequenceToUse(new int[] {1001, 1000});
        
        AbilityEffectApplicationRandomizer testCandidate = new AbilityEffectApplicationRandomizer();
        testCandidate.setApplyChanceInPerTenThousand(1000);    
        testCandidate.decorate(poisonApplier);
        
        BattleModifiedIndividualExorion result = testCandidate.use(source, target);  
        assertThat(result, is(sameInstance(target)));
        
        result = testCandidate.use(source, target); 
        assertThat(result, is(sameInstance(poison)));
    }
}