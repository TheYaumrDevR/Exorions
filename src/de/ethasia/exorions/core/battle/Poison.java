package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class Poison extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private IndividualExorionBaseStats attackerBaseStats;
    private BattleModifiedIndividualExorion decoratedExorion;
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Poison() {
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void applyTo(BattleModifiedIndividualExorion target) {
        decoratedExorion = target;
    }
    
    @Override
    public void takeDamage(int amount) {
        if (null == decoratedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }    
        
        decoratedExorion.takeDamage(amount);
    }

    @Override
    public int getModifiedAccuracy() {
        if (null == decoratedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }   
        
        return decoratedExorion.getModifiedAccuracy();
    }
    
    @Override
    public int getModifiedSpecialDefense() {
        if (null == decoratedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }   
        
        return decoratedExorion.getModifiedSpecialDefense();
    }    
    
    @Override
    public void tick(IndividualExorionBattleModifier defender) {
        int specialAttack = attackerBaseStats.getSpecialAttackValue();
        int specialDefense = defender.getModifiedSpecialDefense();
        
        int specialAttackReduced = specialAttack / 5;
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(specialAttackReduced, specialDefense);
        decoratedExorion.takeDamage(damage);
    }
    
    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
        attackerBaseStats = value;
    }
    
    //</editor-fold>
}