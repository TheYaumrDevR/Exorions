package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;

public abstract class IndividualExorionBattleModifier extends BattleModifiedIndividualExorion {
    
    protected int ticksLeftTillInactivation;
    
    public boolean isActive() {
        return ticksLeftTillInactivation > 0;
    }
    
    protected IndividualExorionBattleModifier() {
        ticksLeftTillInactivation = getAmountOfTicks();
    }
    
    protected abstract int getAmountOfTicks();
    public abstract void applyTo(BattleModifiedIndividualExorion target);
    public abstract void setAttackerBaseStats(IndividualExorionBaseStats value);
    
    public void tick(IndividualExorionBattleModifier root) {
        if (this.isActive()) {
            ticksLeftTillInactivation--;
        }
    }
}