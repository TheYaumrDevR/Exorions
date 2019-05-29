package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class Poison extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private IndividualExorionBaseStats attackerBaseStats;
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Poison() {
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters / Setters">
    
    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
        attackerBaseStats = value;
    }    
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected int getAmountOfTicks() {
        return 2;
    }
    
    @Override
    public void takeDamage(int amount) {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }    
        
        modifiedExorion.takeDamage(amount);
    }

    @Override
    public int getModifiedAccuracy() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }   
        
        return modifiedExorion.getModifiedAccuracy();
    }
    
    @Override
    public int getModifiedAttackPower() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }
        
        return modifiedExorion.getModifiedAttackPower();
    }
    
    @Override
    public int getModifiedSpecialDefense() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }   
        
        return modifiedExorion.getModifiedSpecialDefense();
    }    
    
    @Override
    public void tick(IndividualExorionBattleModifier defender) {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }         
        
        if (!this.isActive()) {
            return;
        }
        
        int specialAttack = attackerBaseStats.getSpecialAttackValue();
        int specialDefense = defender.getModifiedSpecialDefense();
        
        int specialAttackReduced = Math.round(specialAttack / 3.f);
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(specialAttackReduced, specialDefense);
        modifiedExorion.takeDamage(damage);
        
        super.tick(defender);
        modifiedExorion.tick(defender);
    }
    
    //</editor-fold>
}