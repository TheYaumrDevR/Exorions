package de.ethasia.exorions.core;

import java.util.HashSet;
import java.util.Set;

public class BattleAbility {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String name;
    public String getName() {
        return name;
    }
    
    private final Set<DamageTypes> damageTypes;
    public Set<DamageTypes> getDamageTypes() {
        return new HashSet<>(damageTypes);
    }
    
    private final Set<AbilityLearningRequirements> learningRequirements;
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        return new HashSet<>(learningRequirements);
    }
    
    private final float delayMultiplier;
    public float getDelayMultiplier() {
        return delayMultiplier;
    }
    
    private final int requiredPowerPointsForStageTwo;
    public int getRequiredPowerPointsForStageTwo() {
        return requiredPowerPointsForStageTwo;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private BattleAbility(Builder builder) {
        name = builder.name;
        damageTypes = builder.damageTypes;
        learningRequirements = builder.learningRequirements;
        delayMultiplier = builder.delayMultiplier;
        requiredPowerPointsForStageTwo = builder.requiredPowerPointsForStageTwo;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String name;
        private final Set<DamageTypes> damageTypes;
        private final Set<AbilityLearningRequirements> learningRequirements;
        private float delayMultiplier;
        private int requiredPowerPointsForStageTwo;
        
        public Builder() {
            damageTypes = new HashSet<>();
            learningRequirements = new HashSet<>();
        }
        
        public Builder setDamageType(DamageTypes damageType) {
            damageTypes.add(damageType);
            return this;
        }
        
        public Builder setLearningRequirements(AbilityLearningRequirements value) {
            learningRequirements.add(value);
            return this;
        }
        
        public Builder setDelayMultiplier(float value) {
            delayMultiplier = value;
            return this;
        }
        
        public Builder setName(String value) {
            name = value;
            return this;
        }
        
        public Builder setRequiredPowerPointsForStageTwo(int value) {
            requiredPowerPointsForStageTwo = value;
            return this;
        }
        
        public BattleAbility build() {
            return new BattleAbility(this);
        }
    }
    
    //</editor-fold>
}