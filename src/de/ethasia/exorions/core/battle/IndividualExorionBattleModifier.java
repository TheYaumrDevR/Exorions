package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;

public abstract class IndividualExorionBattleModifier extends BattleModifiedIndividualExorion {
    
    public abstract void applyTo(BattleModifiedIndividualExorion target);
    public abstract void setAttackerBaseStats(IndividualExorionBaseStats value);
    public abstract void tick(IndividualExorionBattleModifier root);
}