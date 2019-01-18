package de.ethasia.exorions;

import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;

public class Dependencies {
    
    //<editor-fold defaultstate="collapsed" desc="Public Methods">
    
    public static void injectDependencies() {
        injectCoreDependencies();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static void injectCoreDependencies() {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
    }
    
    //</editor-fold>
}