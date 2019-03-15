package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.BattleCalculator;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BattleCalculatorTest {
    
    @Test
    public void testCalculateBaseStatForLevelFromStatAtMaximumLevel_linearCalculationIsDoneFromStatAtMaximumLevel() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        int result = testCandidate.calculateBaseStatForLevelFromStatAtMaximumLevel(30, 50);
        
        assertThat(result, is(equalTo(30)));
    }
    
    @Test
    public void testCalculateBaseStatForLevelFromStatAtMaximumLevel_maxStatIsGivenAtMaxLevel() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        int result = testCandidate.calculateBaseStatForLevelFromStatAtMaximumLevel(50, 50);
        
        assertThat(result, is(equalTo(50)));
    } 
    
    @Test
    public void testCalculateBaseStatForLevelFromStatAtMaximumLevel_lowestPossibleValueReturnedIsOne() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        int result = testCandidate.calculateBaseStatForLevelFromStatAtMaximumLevel(1, 25);
        
        assertThat(result, is(equalTo(1)));
    }   
    
    @Test
    public void testCalculateBaseStatForLevelFromStatAtMaximumLevel_floatValuesAreRoundedDown() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        int result = testCandidate.calculateBaseStatForLevelFromStatAtMaximumLevel(7, 25);
        
        assertThat(result, is(equalTo(3)));
    }     
}