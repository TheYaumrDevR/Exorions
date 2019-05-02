package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.IndividualExorion;
import java.util.HashSet;
import java.util.Set;

public class BattleAbilityBase extends BattleAbility {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String name;
    @Override
    public String getName() {
        return name;
    }
    
    private final Set<DamageTypes> damageTypes;
    @Override
    public Set<DamageTypes> getDamageTypes() {
        return new HashSet<>(damageTypes);
    }
    
    private final Set<AbilityLearningRequirements> learningRequirements;
    @Override
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        return new HashSet<>(learningRequirements);
    }
    
    private final float delayMultiplier;
    @Override
    public float getDelayMultiplier() {
        return delayMultiplier;
    }
    
    private final int requiredPowerPointsForStageTwo;
    @Override
    public int getRequiredPowerPointsForStageTwo() {
        return requiredPowerPointsForStageTwo;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private BattleAbilityBase(Builder builder) {
        name = builder.name;
        damageTypes = builder.damageTypes;
        learningRequirements = builder.learningRequirements;
        delayMultiplier = builder.delayMultiplier;
        requiredPowerPointsForStageTwo = builder.requiredPowerPointsForStageTwo;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public void use(IndividualExorion attacker, IndividualExorion defender) {}    
    
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
        
        public BattleAbilityBase build() {
            return new BattleAbilityBase(this);
        }
    }
    
    //</editor-fold>
}