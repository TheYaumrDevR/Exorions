package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public abstract class IndividualExorionBattleModifier extends BattleModifiedIndividualExorion {
    
    protected int ticksLeftTillInactivation;
    protected BattleModifiedIndividualExorion modifiedExorion;
    
    public boolean isActive() {
        return ticksLeftTillInactivation > 0;
    }
    
    protected IndividualExorionBattleModifier() {
        ticksLeftTillInactivation = getAmountOfTicks();
    }
    
    protected abstract int getAmountOfTicks();
    public abstract void setAttackerBaseStats(IndividualExorionBaseStats value);
    
    public void applyTo(BattleModifiedIndividualExorion target) {
        if (!target.reapplyModifierOfType(this.getClass())) {
            modifiedExorion = target;            
        }
    }    
    
    @Override
    public void tick(IndividualExorionBattleModifier root) {
        if (this.isActive()) {
            ticksLeftTillInactivation--;
        }
    }
    
    @Override
    protected boolean reapplyModifierOfType(Class type) {
        if (this.getClass() == type) {
            ticksLeftTillInactivation = getAmountOfTicks();
            return true;
        }
        
        return modifiedExorion.reapplyModifierOfType(type);
    }   
    
    protected void throwExceptionIfNothingIsDecorated() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }        
    }
}