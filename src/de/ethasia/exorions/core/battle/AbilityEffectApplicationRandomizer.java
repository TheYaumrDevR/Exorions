package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.general.DecoratorMustDecorateSomethingException;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RandomNumberGenerator;

public class AbilityEffectApplicationRandomizer extends BattleAbilityEffect {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int applyChanceInPerTenThousand;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters / Setters">
    
    public void setApplyChanceInPerTenThousand(int applyChanceInPTT) {
        if (applyChanceInPTT < 0 || applyChanceInPTT > 10000) {
            throw new IllegalArgumentException("Apply chance in ptt must be between 0 and 10000");
        }
        
        applyChanceInPerTenThousand = applyChanceInPTT;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public BattleModifiedIndividualExorion use(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender) {
        if (null != decoratedAbility) {
            RandomNumberGenerator rng = CoreClassesFactory.getInstance().getRandomNumberGeneratorSingletonInstance();
            int randomNumber = rng.createRandomIntegerBetweenAnd(1, 10000);
            
            if (randomNumber <= applyChanceInPerTenThousand) {
                return decoratedAbility.use(attacker, defender);
            }
            
            return defender;
        } else {
            throw new DecoratorMustDecorateSomethingException();            
        }
    }    
    
    //</editor-fold>
}