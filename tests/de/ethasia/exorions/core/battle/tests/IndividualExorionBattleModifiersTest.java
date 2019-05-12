package de.ethasia.exorions.core.battle.tests;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
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
    
    @Test(expected = DecoratorMustDecorateSomethingException.class)
    public void testStaggerGetModifiedAccuracy_decoratesNothing_throwsException() {
        Stagger testCandidate = new Stagger();
        
        testCandidate.getModifiedAccuracy();
    }
}