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
        if (null == decoratedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }         
        
        if (!this.isActive()) {
            return;
        }
        
        int specialAttack = attackerBaseStats.getSpecialAttackValue();
        int specialDefense = defender.getModifiedSpecialDefense();
        
        int specialAttackReduced = Math.round(specialAttack / 3.f);
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(specialAttackReduced, specialDefense);
        decoratedExorion.takeDamage(damage);
        
        super.tick(defender);
    }
    
    //</editor-fold>
}