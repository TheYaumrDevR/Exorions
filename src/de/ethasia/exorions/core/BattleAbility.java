package de.ethasia.exorions.core;

import java.util.HashSet;
import java.util.Set;

public class BattleAbility {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final Set<DamageTypes> damageTypes;
    public Set<DamageTypes> getDamageTypes() {
        return new HashSet<>(damageTypes);
    }
    
    private final Set<AbilityLearningRequirements> learningRequirements;
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        return new HashSet<>(learningRequirements);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private BattleAbility(Builder builder) {
        damageTypes = builder.damageTypes;
        learningRequirements = builder.learningRequirements;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private final Set<DamageTypes> damageTypes;
        private final Set<AbilityLearningRequirements> learningRequirements;
        
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
        
        public BattleAbility build() {
            return new BattleAbility(this);
        }
    }
    
    //</editor-fold>
}