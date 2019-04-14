package de.ethasia.exorions.core;

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
        
        throw new BattleAbilityEffectMustDecorateBattleAbilityException();
    }
    
    @Override
    public Set<DamageTypes> getDamageTypes() {
        if (null != decoratedAbility) {
            return decoratedAbility.getDamageTypes();
        }
        
        throw new BattleAbilityEffectMustDecorateBattleAbilityException();
    }
    
    @Override
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        if (null != decoratedAbility) {
            return decoratedAbility.getLearningRequirements();
        }
        
        throw new BattleAbilityEffectMustDecorateBattleAbilityException();
    }
    
    @Override
    public float getDelayMultiplier() {
        if (null != decoratedAbility) {
            return decoratedAbility.getDelayMultiplier();
        }        
        
        throw new BattleAbilityEffectMustDecorateBattleAbilityException();
    }
    
    @Override
    public int getRequiredPowerPointsForStageTwo() {
        if (null != decoratedAbility) {
            return decoratedAbility.getRequiredPowerPointsForStageTwo();
        }         
        
        throw new BattleAbilityEffectMustDecorateBattleAbilityException();
    }
    
    public void use(IndividualExorion attacker, IndividualExorion defender) {
        if (null != decoratedAbility) {
            int attack = attacker.getBaseStats().getAttackValue();
            int defense = defender.getBaseStats().getDefenseValue();
            
            int damage = attack * attack / (attack + defense);
            defender.takeDamage(damage);
        } else {
            throw new BattleAbilityEffectMustDecorateBattleAbilityException();    
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Decorator Implementations">
    
    @Override
    public void decorate(BattleAbility battleAbility) {
        decoratedAbility = battleAbility;
    }
    
    //</editor-fold>
}