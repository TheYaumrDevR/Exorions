package de.ethasia.exorions.core.interfaces;

/**
 * Provides methods to resolve interfaces in the core layer to implementations.
 */
public abstract class CoreClassesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Singleton Implementation">
    
    private static CoreClassesFactory instance;
    
    public static void setInstance(CoreClassesFactory instance) {
        CoreClassesFactory.instance = instance;
    }
    
    public static CoreClassesFactory getInstance() {
        return CoreClassesFactory.instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Factory Methods">
    
    public abstract RandomNumberGenerator getRandomNumberGeneratorSingletonInstance();
    
    //</editor-fold>
}
