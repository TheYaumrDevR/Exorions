package de.ethasia.exorions.ioadapters.repositories;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import de.ethasia.exorions.core.general.PersistedEntityNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExorionSpecies {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static final Map<Integer, de.ethasia.exorions.core.ExorionSpecies> exorionById = new ConcurrentHashMap<>();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static de.ethasia.exorions.core.ExorionSpecies findById(int id) {
        if (exorionById.containsKey(id)) {
            return exorionById.get(id);
        }        
        
        de.ethasia.exorions.core.ExorionSpecies result = null;
        
        switch (id) {
            case 0:
                result = createClawr();
                break;
            case 1:
                result = createHevil();
                break;
            case 2:
                result = createTrabber();
                break;
            default:
                throw new PersistedEntityNotFoundException();
        }
        
        exorionById.put(id, result);
        return result;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static de.ethasia.exorions.core.ExorionSpecies createClawr() {
        ExorionSpeciesBaseStatsAtMaximumLevel stats = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(50)
            .setAccuracyBaseStat(60)
            .setAttackBaseStat(68)
            .setCriticalHitAvoidanceBaseStat(50)
            .setCriticalHitFrequencyBaseStat(58)
            .setDefenseBaseStat(52)
            .setEvasivenessBaseStat(59)
            .setSwiftnessBaseStat(61)
            .setSpecialAttackBaseStat(25)
            .setSpecialDefenseBaseStat(40)
            .build();
        
        return new de.ethasia.exorions.core.ExorionSpecies.Builder()
            .setName("Clawr")
            .setFulfilledLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setSpeciesBaseStats(stats)
            .build();
    }
    
    private static de.ethasia.exorions.core.ExorionSpecies createHevil() {
        ExorionSpeciesBaseStatsAtMaximumLevel stats = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(60)
            .setAccuracyBaseStat(40)
            .setAttackBaseStat(50)
            .setCriticalHitAvoidanceBaseStat(70)
            .setCriticalHitFrequencyBaseStat(50)
            .setDefenseBaseStat(68)
            .setEvasivenessBaseStat(52)
            .setSwiftnessBaseStat(50)
            .setSpecialAttackBaseStat(25)
            .setSpecialDefenseBaseStat(58)
            .build();
        
        return new de.ethasia.exorions.core.ExorionSpecies.Builder()
            .setName("Hevil")
            .setFulfilledLearningRequirements(AbilityLearningRequirements.HORNS)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setSpeciesBaseStats(stats)
            .build();
    }
    
    private static de.ethasia.exorions.core.ExorionSpecies createTrabber() {
        ExorionSpeciesBaseStatsAtMaximumLevel stats = new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(55)
            .setAccuracyBaseStat(62)
            .setAttackBaseStat(40)
            .setCriticalHitAvoidanceBaseStat(53)
            .setCriticalHitFrequencyBaseStat(48)
            .setDefenseBaseStat(57)
            .setEvasivenessBaseStat(47)
            .setSwiftnessBaseStat(49)
            .setSpecialAttackBaseStat(50)
            .setSpecialDefenseBaseStat(62)
            .build();
        
        return new de.ethasia.exorions.core.ExorionSpecies.Builder()
            .setName("Trabber")
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TEETH)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setSpeciesBaseStats(stats)
            .build();
    }
    
    //</editor-fold>
}