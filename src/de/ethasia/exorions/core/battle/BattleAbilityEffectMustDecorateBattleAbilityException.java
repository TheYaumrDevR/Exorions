package de.ethasia.exorions.core.battle;

public class BattleAbilityEffectMustDecorateBattleAbilityException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public BattleAbilityEffectMustDecorateBattleAbilityException() {
        super("A BattleAbilityEffect must decorate another BattleAbility before being used. Use the decorate method to do so.");
    }
    
    //</editor-fold>
}