package de.ethasia.exorions.ioadapters;

import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerMovementUseCase;

public abstract class UseCasesFactoryIoAdapters {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static UseCasesFactoryIoAdapters instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void setInstance(UseCasesFactoryIoAdapters value) {
        instance = value;
    }
    
    public static UseCasesFactoryIoAdapters getInstance() {
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    
    public abstract PlayerMovementUseCase createPlayerMovementUseCase();
    
    //</editor-fold>
}