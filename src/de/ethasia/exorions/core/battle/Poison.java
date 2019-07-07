package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;

public class Poison extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public Poison() {
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected int getAmountOfTicks() {
        return 2;
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
    public int getModifiedSpecialAttackPower() {
        throwExceptionIfNothingIsDecorated();
        return modifiedExorion.getModifiedSpecialAttackPower();
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
    
    @Override
    public void tick(BattleModifiedIndividualExorion attackerRoot, BattleModifiedIndividualExorion defenderRoot) {
        throwExceptionIfNothingIsDecorated();        
        
        if (!this.isActive()) {
            modifiedExorion.tick(attackerRoot, defenderRoot);
            return;
        }
        
        int specialAttack = attackerRoot.getModifiedSpecialAttackPower();
        int specialDefense = defenderRoot.getModifiedSpecialDefense();
        
        int specialAttackReduced = Math.round(specialAttack / 3.f);
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(specialAttackReduced, specialDefense);
        modifiedExorion.takeDamage(damage);
        
        super.tick(attackerRoot, defenderRoot);
        modifiedExorion.tick(attackerRoot, defenderRoot);
    }
    
    //</editor-fold>
}