package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import java.util.Map;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

import java.util.HashMap;

public class TestExorions {
    
    private static Map<Integer, ExorionSpecies> exorionsById;
    
    static {
        exorionsById = new HashMap<>();     
        fillMapWithMockExorion();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static IndividualExorion findExorionById(int id) throws NotAllPropertiesAreSetException {
        ExorionSpecies species = exorionsById.get(id);
        
        IndividualExorionBaseStats individualStats = new IndividualExorionBaseStats.Builder()
            .setAccuracy(0)
            .setAttackValue(0)
            .setCriticalHitAvoidance(0)
            .setCriticalHitFrequency(0)
            .setDefenseValue(0)
            .setEvasiveness(0)
            .setMaximumHealthPoints(0)
            .setSpecialAttackValue(0)
            .setSpecialDefenseValue(0)
            .setSwiftness(0)
            .build();
        
        IndividualExorion exorion = new IndividualExorion.Builder()
            .setBaseStats(individualStats)
            .setGenome(new MockGenome(0))
            .setLevel(0)
            .setSpecies(species)
            .build();
        
        exorion.levelUpBy(50);    
        
        return exorion;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void fillMapWithMockExorion() {
        ExorionSpeciesBaseStatsAtMaximumLevel baseState = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setAccuracyBaseStat(50)
            .setAttackBaseStat(50)
            .setCriticalHitAvoidanceBaseStat(50)
            .setCriticalHitFrequencyBaseStat(50)
            .setDefenseBaseStat(60)
            .setEvasivenessBaseStat(50)
            .setMaximumHealthBaseStat(54)
            .setSpecialAttackBaseStat(50)
            .setSpecialDefenseBaseStat(50)
            .setSwiftnessBaseStat(70)
            .build();
        
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setName("Chomper")
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TEETH)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setSpeciesBaseStats(baseState)
            .build();
        
        exorionsById.put(0, species);    
        
        baseState = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setAccuracyBaseStat(50)
            .setAttackBaseStat(60)
            .setCriticalHitAvoidanceBaseStat(50)
            .setCriticalHitFrequencyBaseStat(50)
            .setDefenseBaseStat(70)
            .setEvasivenessBaseStat(50)
            .setMaximumHealthBaseStat(58)
            .setSpecialAttackBaseStat(50)
            .setSpecialDefenseBaseStat(50)
            .setSwiftnessBaseStat(50)
            .build();
        
        species = new ExorionSpecies.Builder()
            .setName("Boned Ram")
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.HORNS)
            .setSpeciesBaseStats(baseState)
            .build();        
        
        exorionsById.put(1, species); 
    }    
    
    //</editor-fold>
}