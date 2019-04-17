package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import java.util.Map;

import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestExorions {
    
    private static Map<Integer, IndividualExorion> exorionsById;
    
    static {
        exorionsById = new HashMap<>();
        
        try {        
            fillMapWithMockExorion();
        } catch (NotAllPropertiesAreSetException ex) {
            Logger.getLogger(TestExorions.class.getName()).log(Level.SEVERE, "Could not create dictionary of mock Exorion.", ex);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static IndividualExorion findExorionById(int id) {
        return exorionsById.get(id);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void fillMapWithMockExorion() throws NotAllPropertiesAreSetException {
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
            .setSpeciesBaseStats(baseState)
            .build();
        
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
        
        exorionsById.put(0, exorion);        
    }    
    
    //</editor-fold>
}