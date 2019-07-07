package de.ethasia.exorions.core.battle;

public abstract class BattleModifiedIndividualExorion {
    
    public abstract void takeDamage(int amount);
    public abstract int getModifiedAccuracy();
    public abstract int getModifiedAttackPower();
    public abstract int getModifiedSpecialAttackPower();
    public abstract int getModifiedDefense();
    public abstract int getModifiedSpecialDefense();
    public abstract void tick(BattleModifiedIndividualExorion attackerRoot, BattleModifiedIndividualExorion defenderRoot);
    protected abstract boolean reapplyModifierOfType(Class type);
}