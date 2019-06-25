package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class ApplyBattleModifierAbilityEffect extends BattleAbilityEffect {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    IndividualExorionBattleModifier modifierToApply;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public ApplyBattleModifierAbilityEffect(IndividualExorionBattleModifier modifier) {
        modifierToApply = modifier;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">    
    
    @Override
    public BattleModifiedIndividualExorion use(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender) {
        if (null != decoratedAbility) {
            decoratedAbility.use(attacker, defender);
            
            modifierToApply.applyTo(defender);
            
            if (modifierToApply.isApplied()) {
                return modifierToApply;
            }

            return defender;
        } else {
            throw new DecoratorMustDecorateSomethingException();            
        }
    }    
    
    //</editor-fold>
}