package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.general.SetValueIsNotWithinLegalBoundsException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BattleAbilityBase extends BattleAbility {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final Map<Integer, Integer> requiredLevelByAbilityLevel;    
    
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
    
    @Override
    public int getMinimumLevelRequired() {
        return requiredLevelByAbilityLevel.get(abilityLevel);
    }
    
    private final int abilityLevel;
    @Override
    public int getAbilityLevel() {
        return abilityLevel;
    }
    
    private final int velocityCost;
    @Override
    public int getVelocityCost() {
        return velocityCost;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private BattleAbilityBase(Builder builder) {
        name = builder.name;
        damageTypes = builder.damageTypes;
        learningRequirements = builder.learningRequirements;
        delayMultiplier = builder.delayMultiplier;
        requiredPowerPointsForStageTwo = builder.requiredPowerPointsForStageTwo;
        abilityLevel = builder.abilityLevel;
        requiredLevelByAbilityLevel = builder.requiredLevelByAbilityLevel;
        velocityCost = builder.velocityCost;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public BattleModifiedIndividualExorion use(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender) { 
        return defender; 
    }   
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private static final int MAXIMUM_ABILITY_LEVEL = 20;
        
        private String name;
        private final Set<DamageTypes> damageTypes;
        private final Set<AbilityLearningRequirements> learningRequirements;
        private float delayMultiplier;
        private int requiredPowerPointsForStageTwo;
        private int abilityLevel;
        private int velocityCost;
        
        private Map<Integer, Integer> requiredLevelByAbilityLevel;
        
        public Builder() {
            abilityLevel = 1;
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
        
        public Builder setAbilityLevel(int value) {
            if (value > MAXIMUM_ABILITY_LEVEL || value < 1) {
                throw new SetValueIsNotWithinLegalBoundsException(1, MAXIMUM_ABILITY_LEVEL);
            }
            
            abilityLevel = value;
            return this;
        }
        
        public Builder setRequiredLevelByAbilityLevel(Map<Integer, Integer> value) {
            requiredLevelByAbilityLevel = value;
            return this;
        }
        
        public Builder setVelocityCost(int value) {
            velocityCost = value;
            return this;
        }
        
        public BattleAbilityBase build() {
            if (null == requiredLevelByAbilityLevel) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new BattleAbilityBase(this);
        }
    }
    
    //</editor-fold>
}