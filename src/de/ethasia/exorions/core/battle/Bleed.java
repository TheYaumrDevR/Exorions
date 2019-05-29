package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;

public class Bleed extends IndividualExorionBattleModifier {

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    protected int getAmountOfTicks() {
        return 3;
    }

    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
    }

    @Override
    public void takeDamage(int amount) {
    }

    @Override
    public int getModifiedAccuracy() {
        return modifiedExorion.getModifiedAccuracy();
    }
    
    @Override
    public int getModifiedAttackPower() {
        return modifiedExorion.getModifiedAttackPower();
    }

    @Override
    public int getModifiedSpecialDefense() {
        return modifiedExorion.getModifiedSpecialDefense();
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void setAttackPowerToBaseDamageOn(int attackOrSpecialAttackValue) {
        
    }
    
    //</editor-fold>
}