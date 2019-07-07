package de.ethasia.exorions.core.battle;

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
        super.tick(attackerRoot, defenderRoot);
    }  
    
    //</editor-fold>
}