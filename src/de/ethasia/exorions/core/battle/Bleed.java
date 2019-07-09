package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.BattleCalculator;

public class Bleed extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final BattleCalculator battleCalculator;
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
    public void applyTo(BattleModifiedIndividualExorion target) {
        target.reapplyModifierOfType(this.getClass());
        modifiedExorion = target;            
    }
    
    @Override
    protected boolean reapplyModifierOfType(Class type) {
        if (this.getClass() == type && ticksLeftTillInactivation > 0) {
            ticksLeftTillInactivation = getAmountOfTicks();
        }
        
        return modifiedExorion.reapplyModifierOfType(type);
    }     
    
    @Override
    public void tick(BattleModifiedIndividualExorion attackerRoot, BattleModifiedIndividualExorion defenderRoot) {
        throwExceptionIfNothingIsDecorated();
        
        if (!this.isActive()) {
            modifiedExorion.tick(attackerRoot, defenderRoot);
            return;
        }        
        
        int bleedAttackPower = Math.round(attackerRoot.getModifiedAttackPower() / 4.f);
        int damage = battleCalculator.calculateDamageFromAttackAndDefense(bleedAttackPower, defenseToBaseDamageOn);
        modifiedExorion.takeDamage(damage);        
        
        super.tick(attackerRoot, defenderRoot);
        modifiedExorion.tick(attackerRoot, defenderRoot);
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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setDefenseValueToBaseDamageOn(int defenseValue) {
        defenseToBaseDamageOn = defenseValue;
    }
    
    //</editor-fold>
}