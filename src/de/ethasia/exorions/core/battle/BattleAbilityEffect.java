package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import java.util.Set;

public abstract class BattleAbilityEffect extends BattleAbility {
    
    protected BattleAbility decoratedAbility;
    
    @Override
    public String getName() {
        if (null != decoratedAbility) {
            return decoratedAbility.getName();
        }
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public Set<DamageTypes> getDamageTypes() {
        if (null != decoratedAbility) {
            return decoratedAbility.getDamageTypes();
        }
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public Set<AbilityLearningRequirements> getLearningRequirements() {
        if (null != decoratedAbility) {
            return decoratedAbility.getLearningRequirements();
        }
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public float getDelayMultiplier() {
        if (null != decoratedAbility) {
            return decoratedAbility.getDelayMultiplier();
        }        
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public int getRequiredPowerPointsForStageTwo() {
        if (null != decoratedAbility) {
            return decoratedAbility.getRequiredPowerPointsForStageTwo();
        }         
        
        throw new DecoratorMustDecorateSomethingException();
    }    
    
    public void decorate(BattleAbility battleAbility) {
        decoratedAbility = battleAbility;
    }
}