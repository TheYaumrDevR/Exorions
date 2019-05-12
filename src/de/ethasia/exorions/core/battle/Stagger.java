package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class Stagger extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    BattleModifiedIndividualExorion modifiedExorion;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public int getModifiedAccuracy() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }
        
        int accuracy = modifiedExorion.getModifiedAccuracy();
        int reduceBy = Math.round(accuracy / 5.f);
        
        return accuracy - reduceBy;
    }
    
    @Override
    public void applyTo(BattleModifiedIndividualExorion target) {
        modifiedExorion = target;
    }    
    
    //</editor-fold>
}