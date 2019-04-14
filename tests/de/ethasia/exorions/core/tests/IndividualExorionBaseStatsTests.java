package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.IndividualExorionBaseStats;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndividualExorionBaseStatsTests {

    @Test
    public void testIndividualBaseStatsBuilder_canCreateStats_createdObjectNotNull() {
        IndividualExorionBaseStats.Builder baseStatsBuilder = new IndividualExorionBaseStats.Builder();
        
        IndividualExorionBaseStats exorionBaseStats = baseStatsBuilder
            .setMaximumHealthPoints(100)
            .setAttackValue(100)
            .setSpecialAttackValue(100)
            .setDefenseValue(100)
            .setSpecialDefenseValue(100)
            .setSwiftness(100)
            .setEvasiveness(100)
            .setAccuracy(100)
            .setCriticalHitAvoidance(100)
            .setCriticalHitFrequency(100)
            .build();
        
        assertThat(exorionBaseStats, is(notNullValue()));
    }
    
    @Test
    public void testIndividualBaseStatsBuilder_statsAreSet_createdObjectHasSameValuesAsSetInBuilder() {
        IndividualExorionBaseStats.Builder exorionBaseStatsBuilder = new IndividualExorionBaseStats.Builder();
        
        IndividualExorionBaseStats exorionBaseStats = exorionBaseStatsBuilder
            .setMaximumHealthPoints(12)
            .setAttackValue(76)
            .setSpecialAttackValue(30)
            .setDefenseValue(41)
            .setSpecialDefenseValue(99)
            .setSwiftness(100)
            .setEvasiveness(22)
            .setAccuracy(34)
            .setCriticalHitAvoidance(96)
            .setCriticalHitFrequency(45)
            .build();
        
        assertThat(exorionBaseStats.getMaximumHealthPoints(), is(12));
        assertThat(exorionBaseStats.getAttackValue(), is(76));
        assertThat(exorionBaseStats.getSpecialAttackValue(), is(30));
        assertThat(exorionBaseStats.getDefenseValue(), is(41));
        assertThat(exorionBaseStats.getSpecialDefenseValue(), is(99));
        assertThat(exorionBaseStats.getSwiftness(), is(100));
        assertThat(exorionBaseStats.getEvasiveness(), is(22));
        assertThat(exorionBaseStats.getAccuracy(), is(34));
        assertThat(exorionBaseStats.getCriticalHitAvoidance(), is(96));
        assertThat(exorionBaseStats.getCriticalHitFrequency(), is(45));
    }   
    
    @Test
    public void testIndividualBaseStatsCreation_getCurrentHealthPoints_currentHealthPointsIsEqualToMaxHealthPoints() {
        IndividualExorionBaseStats.Builder baseStatsBuilder = new IndividualExorionBaseStats.Builder();
        
        IndividualExorionBaseStats exorionBaseStats = baseStatsBuilder
            .setMaximumHealthPoints(100)
            .setAttackValue(100)
            .setSpecialAttackValue(100)
            .setDefenseValue(100)
            .setSpecialDefenseValue(100)
            .setSwiftness(100)
            .setEvasiveness(100)
            .setAccuracy(100)
            .setCriticalHitAvoidance(100)
            .setCriticalHitFrequency(100)
            .build();
        
        assertThat(exorionBaseStats.getCurrentHealthPoints(), is(equalTo(100)));
    }   
    
    @Test
    public void testIndividualBaseStatsCreation_setCurrentHealthPoints_currentHealthPointsIsEqualToSetHealthPoints() {
        IndividualExorionBaseStats.Builder baseStatsBuilder = new IndividualExorionBaseStats.Builder();
        
        IndividualExorionBaseStats exorionBaseStats = baseStatsBuilder
            .setMaximumHealthPoints(100)
            .setAttackValue(100)
            .setSpecialAttackValue(100)
            .setDefenseValue(100)
            .setSpecialDefenseValue(100)
            .setSwiftness(100)
            .setEvasiveness(100)
            .setAccuracy(100)
            .setCriticalHitAvoidance(100)
            .setCriticalHitFrequency(100)
            .build();
        
        exorionBaseStats.setCurrentHealthPoints(30);
        assertThat(exorionBaseStats.getCurrentHealthPoints(), is(equalTo(30)));
    } 

    @Test
    public void testIndividualBaseStatsCreation_setCurrentHealthPointsToLessThanZero_currentHealthPointsIsZero() {
        IndividualExorionBaseStats.Builder baseStatsBuilder = new IndividualExorionBaseStats.Builder();
        
        IndividualExorionBaseStats exorionBaseStats = baseStatsBuilder
            .setMaximumHealthPoints(100)
            .setAttackValue(100)
            .setSpecialAttackValue(100)
            .setDefenseValue(100)
            .setSpecialDefenseValue(100)
            .setSwiftness(100)
            .setEvasiveness(100)
            .setAccuracy(100)
            .setCriticalHitAvoidance(100)
            .setCriticalHitFrequency(100)
            .build();
        
        exorionBaseStats.setCurrentHealthPoints(-23);
        assertThat(exorionBaseStats.getCurrentHealthPoints(), is(equalTo(0)));
    }    
}
