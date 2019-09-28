package de.ethasia.exorions.usecases.interfaces;

import de.ethasia.exorions.usecases.crosslayer.DialogWindowPresenter;

public abstract class PresentersFactory {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static PresentersFactory instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void setInstance(PresentersFactory value) {
        instance = value;
    }
    
    public static PresentersFactory getInstance() {
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Abstract methods">
    
    public abstract DialogWindowPresenter createDialogWindowPresenter();
    
    //</editor-fold>
}