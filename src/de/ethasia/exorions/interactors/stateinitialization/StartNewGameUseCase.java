package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;

public class StartNewGameUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final OverworldStatePresenter overworldStatePresenter;
    private final FatalErrorPresenter fatalErrorPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StartNewGameUseCase() {
        overworldStatePresenter = PresentersFactory.getInstance().createOverworldStatePresenter();
        fatalErrorPresenter = PresentersFactory.getInstance().createFatalErrorPresenter();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void startNewGame() {
        try {
            overworldStatePresenter.presentOverworldWithNewGameMap();  
        } catch (InformationForMapsCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        } catch (MapDataCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        }
    }
    
    //</editor-fold>
}