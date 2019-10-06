package de.ethasia.exorions.core.interfaces;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;

public abstract class UseCasesFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Implementation">
    
    private static UseCasesFactory instance;
    
    public static void setInstance(UseCasesFactory instance) {
        UseCasesFactory.instance = instance;
    }
    
    public static UseCasesFactory getInstance() {
        return UseCasesFactory.instance;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Factory Methods">
    
    public abstract InteractionTileUseCase createStartBattleSimulatorUseCase();
    
    //</editor-fold>
}