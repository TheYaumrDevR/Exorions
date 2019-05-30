package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;
import de.ethasia.exorions.core.IndividualExorionBaseStats;

public class Bleed extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final BattleCalculator battleCalculator;
    private int attackPowerToBaseDamageOn;
    private int defenseToBaseDamageOn;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Bleed() {
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected int getAmountOfTicks() {
        return 3;
    }

    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
    }
    
    @Override
    public void tick(IndividualExorionBattleModifier root) {
        throwExceptionIfNothingIsDecorated();
        
        if (!this.isActive()) {
            return;
        }        
        
        int bleedAttackPower = Math.round(attackPowerToBaseDamageOn / 3.f);
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(bleedAttackPower, defenseToBaseDamageOn);
        modifiedExorion.takeDamage(damage);        
        
        super.tick(root);
    }    

    @Override
    public void takeDamage(int amount) {
        throwExceptionIfNothingIsDecorated();
        modifiedExorion.takeDamage(amount);
    }

    @Override
    public int getModifiedAccuracy() {
        throwExceptionIfNothingIsDecorated();
        return modifiedExorion.getModifiedAccuracy();
    }
    
    @Override
    public int getModifiedAttackPower() {
        throwExceptionIfNothingIsDecorated();
        return modifiedExorion.getModifiedAttackPower();
    }
    
    @Override
    public int getModifiedDefense() {
        throwExceptionIfNothingIsDecorated();
        return modifiedExorion.getModifiedDefense();
    }

    @Override
    public int getModifiedSpecialDefense() {
        throwExceptionIfNothingIsDecorated();
        return modifiedExorion.getModifiedSpecialDefense();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setAttackPowerToBaseDamageOn(int attackOrSpecialAttackValue) {
        attackPowerToBaseDamageOn = attackOrSpecialAttackValue;
    }
    
    public void setDefenseValueToBaseDamageOn(int defenseValue) {
        defenseToBaseDamageOn = defenseValue;
    }
    
    //</editor-fold>
}