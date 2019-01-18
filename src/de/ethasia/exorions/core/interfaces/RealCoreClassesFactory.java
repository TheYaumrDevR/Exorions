package de.ethasia.exorions.core.interfaces;

import de.ethasia.exorions.core.RandomNumberGeneratorImpl;

public class RealCoreClassesFactory extends CoreClassesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static RandomNumberGenerator rngInstance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Core Classes Factory">
    
    @Override
    public RandomNumberGenerator getRandomNumberGeneratorSingletonInstance() {
        if (null == rngInstance) {
            rngInstance = new RandomNumberGeneratorImpl();
        }
        
        return rngInstance;
    }    
    
    //</editor-fold>
}