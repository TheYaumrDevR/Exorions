package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;

public class Stagger extends IndividualExorionBattleModifier {
    
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
        
        if (!this.isActive()) {
            return modifiedExorion.getModifiedAccuracy();
        }
        
        int accuracy = modifiedExorion.getModifiedAccuracy();
        int reduceBy = Math.round(accuracy / 5.f);
        
        return accuracy - reduceBy;
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
    
    @Override
    public void tick(BattleModifiedIndividualExorion root) {
        super.tick(root);
    }
    
    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
        
    }    
    
    //</editor-fold>
}