package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RandomNumberGenerator;

public class MockCoreClassesFactory extends CoreClassesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static RandomNumberGenerator rngInstance;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="CoreClassesFactory Overrides">
    
    @Override
    public RandomNumberGenerator getRandomNumberGeneratorSingletonInstance() {
        if (null == rngInstance) {
            rngInstance = new RandomNumberGeneratorMock();
        }
        
        return rngInstance;
    }    
    
    //</editor-fold>
}