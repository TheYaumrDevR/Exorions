package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class DirectDamageAbilityEffect extends BattleAbilityEffect {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DirectDamageAbilityEffect() {
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="BattleAbility Implementations">
    
    @Override
    public BattleModifiedIndividualExorion use(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender) {
        if (null != decoratedAbility) {
            BattleModifiedIndividualExorion modifiedDefender = decoratedAbility.use(attacker, defender);
            
            int attack = attacker.getModifiedAttackPower();
            int defense = defender.getModifiedDefense();
            
            int damage = battleCalculator.calculateDamageFromAttackAndDefense(attack, defense);
            defender.takeDamage(damage);
            
            return modifiedDefender;
        } else {
            throw new DecoratorMustDecorateSomethingException();    
        }
    }
    
    //</editor-fold>
}