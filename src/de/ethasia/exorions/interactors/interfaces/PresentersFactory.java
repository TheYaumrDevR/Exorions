package de.ethasia.exorions.interactors.interfaces;

import de.ethasia.exorions.interactors.crosslayer.DebugWarningLogPresenter;
import de.ethasia.exorions.interactors.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.crosslayer.PlayerAvatarMovementPresenter;

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
    public abstract OverworldStatePresenter createOverworldStatePresenter();
    public abstract FatalErrorPresenter createFatalErrorPresenter();
    public abstract DebugWarningLogPresenter getDebugWarningLogPresenterSingletonInstance();
    public abstract PlayerAvatarMovementPresenter createPlayerAvatarMovementPresenter();
    
    //</editor-fold>
}