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
    
    @Test
    public void testCalculateGeneValueStatIncreaseFactor_gvIsZero_factorIsOneQuarter() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        double result = testCandidate.calculateGeneValueStatIncreaseFactor(0);
        
        assertThat(result, is(equalTo(0.25)));
    }
    
    @Test
    public void testCalculateGeneValueStatIncreaseFactor_gvIs25_factorIsOneHalf() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        double result = testCandidate.calculateGeneValueStatIncreaseFactor(25);
        
        assertThat(result, is(equalTo(0.5)));
    } 
    
    @Test
    public void testCalculateGeneValueStatIncreaseFactor_gvIs50_factorIsOne() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        double result = testCandidate.calculateGeneValueStatIncreaseFactor(50);
        
        assertThat(result, is(equalTo(1.0)));
    } 

    @Test
    public void testCalculateGeneValueStatIncreaseFactor_gvIs12_factorIsCircaOneThird() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        double result = testCandidate.calculateGeneValueStatIncreaseFactor(12);
        
        assertEquals(0.348, result, 0.001);
    }   
    
    @Test
    public void testCalculateGenomeAdjustedStat_baseStatAndFactorGiven_baseStatIsIncreasedByItsMultipliedValue() {
        BattleCalculator testCandidate = new BattleCalculator();
        
        int baseStat = 50;
        double factor = 0.5;
        
        int result = testCandidate.calculateGenomeAdjustedStat(50, 0.5);
        
        assertThat(result, is(equalTo(75)));
    }
}