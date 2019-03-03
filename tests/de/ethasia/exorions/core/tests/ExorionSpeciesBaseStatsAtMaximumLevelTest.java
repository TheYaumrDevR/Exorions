package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExorionSpeciesBaseStatsAtMaximumLevelTest {
   
    @Test
    public void testBuilderBuild_createdObjectContainsSetStats() {
        ExorionSpeciesBaseStatsAtMaximumLevel testCandidate = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(50)
            .setAttackBaseStat(49)
            .setDefenseBaseStat(48)
            .setSpecialAttackBaseStat(47)
            .setSpecialDefenseBaseStat(46)
            .setAccuracyBaseStat(45)
            .setEvasivenessBaseStat(44)
            .setCriticalHitFrequencyBaseStat(43)
            .setCriticalHitAvoidanceBaseStat(42)
            .setSwiftnessBaseStat(41)
            .build();
        
        assertThat(testCandidate.getMaximumHealthBaseStat(), is(equalTo(50)));
        assertThat(testCandidate.getAttackBaseStat(), is(equalTo(49)));
        assertThat(testCandidate.getDefenseBaseStat(), is(equalTo(48)));
        assertThat(testCandidate.getSpecialAttackBaseStat(), is(equalTo(47)));
        assertThat(testCandidate.getSpecialDefenseBaseStat(), is(equalTo(46)));
        assertThat(testCandidate.getAccuracyBaseStat(), is(equalTo(45)));
        assertThat(testCandidate.getEvasivenessBaseStat(), is(equalTo(44)));
        assertThat(testCandidate.getCriticalHitFrequencyBaseStat(), is(equalTo(43)));
        assertThat(testCandidate.getCriticalHitAvoidanceBaseStat(), is(equalTo(42)));
        assertThat(testCandidate.getSwiftnessBaseStat(), is(equalTo(41)));
    }
}