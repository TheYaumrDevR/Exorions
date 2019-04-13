package de.ethasia.exorions.core;

import java.util.HashSet;
import java.util.Set;

public class DirectDamageAbilityEffect extends BattleAbilityEffect {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    BattleAbility decoratedAbility;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="BattleAbility Implementations">
    
    @Override
    public String getName() {
        if (null != decoratedAbility) {
            return decoratedAbility.getName();
        }
        
        return "";
    }
    
    @Override
    public Set<DamageTypes> getDamageTypes() {
        return new HashSet<>();
    }
    
    @Override
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        return new HashSet<>();
    }
    
    @Override
    public float getDelayMultiplier() {
        return 1.0f;
    }
    
    @Override
    public int getRequiredPowerPointsForStageTwo() {
        return 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Decorator Implementations">
    
    @Override
    public void decorate(BattleAbility battleAbility) {
        decoratedAbility = battleAbility;
    }
    
    //</editor-fold>
}