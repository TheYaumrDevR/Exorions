package de.ethasia.exorions.core.battle;

public abstract class BattleModifiedIndividualExorion {
    
    public abstract void takeDamage(int amount);
    public abstract int getModifiedAccuracy();
    public abstract int getModifiedSpecialDefense();
    public abstract void tick(IndividualExorionBattleModifier root);
    protected abstract boolean reapplyModifierOfType(Class type);
}