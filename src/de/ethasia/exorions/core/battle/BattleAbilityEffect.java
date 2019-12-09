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
    public int getRequiredPowerPointsForStageTwo() {
        if (null != decoratedAbility) {
            return decoratedAbility.getRequiredPowerPointsForStageTwo();
        }         
        
        throw new DecoratorMustDecorateSomethingException();
    }    
    
    @Override
    public int getMinimumLevelRequired() {
        if (null != decoratedAbility) {
            return decoratedAbility.getMinimumLevelRequired();            
        }
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public int getAbilityLevel() {
        if (null != decoratedAbility) {
            return decoratedAbility.getAbilityLevel();
        }
        
        throw new DecoratorMustDecorateSomethingException();
    }
    
    @Override
    public int getVelocityCost() {
        if (null != decoratedAbility) {
            return decoratedAbility.getVelocityCost();
        }
        
        throw new DecoratorMustDecorateSomethingException();        
    }
    
    public void decorate(BattleAbility battleAbility) {
        decoratedAbility = battleAbility;
    }
    
    public BattleModifiedIndividualExorion useDecoratedAbilitiesOnly(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender) {
        if (null != decoratedAbility) {
            return decoratedAbility.use(attacker, defender);
        } else {
            throw new DecoratorMustDecorateSomethingException();
        }
    }    
}