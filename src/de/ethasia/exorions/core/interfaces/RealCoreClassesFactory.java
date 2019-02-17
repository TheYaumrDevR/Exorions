package de.ethasia.exorions.core.interfaces;

import de.ethasia.exorions.core.RandomNumberGeneratorImpl;
import de.ethasia.exorions.core.breeding.AllelePairGeneValueCalculatorImpl;

public class RealCoreClassesFactory extends CoreClassesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static RandomNumberGenerator rngInstance;
    private static AllelePairGeneValueCalculator allelePairGeneValueCalculatorInstance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Core Classes Factory">
    
    @Override
    public RandomNumberGenerator getRandomNumberGeneratorSingletonInstance() {
        if (null == rngInstance) {
            rngInstance = new RandomNumberGeneratorImpl();
        }
        
        return rngInstance;
    }    
    
    @Override
    public AllelePairGeneValueCalculator getAllelePairGeneValueCalculatorSingletonInstance() {
        if (null == allelePairGeneValueCalculatorInstance) {
            allelePairGeneValueCalculatorInstance = new AllelePairGeneValueCalculatorImpl();
        }
        
        return allelePairGeneValueCalculatorInstance;
    }    
    
    //</editor-fold>
}