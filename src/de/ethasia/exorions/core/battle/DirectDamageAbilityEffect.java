package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import java.util.Map;

public class DirectDamageAbilityEffect extends BattleAbilityEffect {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private Map<Integer, Float> abilityPowerByAbilityLevel;
    public void setAbilityPowerByAbilityLevel(Map<Integer, Float> value) {
        abilityPowerByAbilityLevel = value;
    }
    
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
            
            int attack = Math.round(attacker.getModifiedAttackPower() * abilityPowerByAbilityLevel.get(getAbilityLevel()));
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