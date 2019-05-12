package de.ethasia.exorions.core.battle;

public abstract class IndividualExorionBattleModifier extends BattleModifiedIndividualExorion {
    
    public abstract void applyTo(BattleModifiedIndividualExorion target);
}