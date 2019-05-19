package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;

public class Stagger extends IndividualExorionBattleModifier {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private BattleModifiedIndividualExorion modifiedExorion;
    
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
        
        if (!this.isActive()) {
            return modifiedExorion.getModifiedAccuracy();
        }
        
        int accuracy = modifiedExorion.getModifiedAccuracy();
        int reduceBy = Math.round(accuracy / 5.f);
        
        return accuracy - reduceBy;
    }
    
    @Override 
    public int getModifiedSpecialDefense() {
        if (null == modifiedExorion) {
            throw new DecoratorMustDecorateSomethingException();
        }   
        
        return modifiedExorion.getModifiedSpecialDefense();
    }
    
    @Override
    public void applyTo(BattleModifiedIndividualExorion target) {
        modifiedExorion = target;
    }    
    
    @Override
    public void tick(IndividualExorionBattleModifier root) {
        super.tick(root);
    }
    
    @Override
    public void setAttackerBaseStats(IndividualExorionBaseStats value) {
        
    }    
    
    //</editor-fold>
}