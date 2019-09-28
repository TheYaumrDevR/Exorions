package de.ethasia.exorions.usecases.stateinitialization;

import de.ethasia.exorions.usecases.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;

public class StartNewGameUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final OverworldStatePresenter overworldStatePresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StartNewGameUseCase() {
        overworldStatePresenter = PresentersFactory.getInstance().createOverworldStatePresenter();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void startNewGame() {
        overworldStatePresenter.presentOverworldWithNewGameMap();
    }
    
    //</editor-fold>
}