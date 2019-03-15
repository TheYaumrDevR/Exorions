package de.ethasia.exorions.core;

import java.util.HashSet;
import java.util.Set;

public class ExorionSpecies {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int MAXIMUM_LEVEL = 50;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String name;
    public String getName() {
        return name;
    }
    
    private final Set<AbilityLearningRequirements> fulfilledAbilityLearningRequirements;
    public Set<AbilityLearningRequirements> getFulfilledLearningRequirements() {
        return new HashSet<>(fulfilledAbilityLearningRequirements);
    }
    
    private final ExorionSpeciesBaseStatsAtMaximumLevel baseStats;
    public ExorionSpeciesBaseStatsAtMaximumLevel getSpeciesBaseStats() {
        return baseStats;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private ExorionSpecies(Builder builder) {
        name = builder.name;
        fulfilledAbilityLearningRequirements = builder.fulfilledAbilityLearningRequirements;
        baseStats = builder.speciesBaseStats;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String name;
        private final Set<AbilityLearningRequirements> fulfilledAbilityLearningRequirements;
        private ExorionSpeciesBaseStatsAtMaximumLevel speciesBaseStats;
        
        public Builder() {
            fulfilledAbilityLearningRequirements = new HashSet<>();
        }
        
        public Builder setName(String value) {
            name = value;
            return this;
        }
        
        public Builder setFulfilledLearningRequirements(AbilityLearningRequirements value) {
            fulfilledAbilityLearningRequirements.add(value);
            return this;
        }
        
        public Builder setSpeciesBaseStats(ExorionSpeciesBaseStatsAtMaximumLevel value) {
            speciesBaseStats = value;
            return this;
        }
        
        public ExorionSpecies build() {
            return new ExorionSpecies(this);
        }
    }
    
    //</editor-fold>
}