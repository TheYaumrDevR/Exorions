package de.ethasia.exorions.interactors.stateinitialization;

import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.InformationForMapsCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.interfaces.GatewaysFactory;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;

public class StartNewGameUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final OverworldStatePresenter overworldStatePresenter;
    private final FatalErrorPresenter fatalErrorPresenter;
    private final MapDefinitionsGateway mapMetaDataGateway;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public StartNewGameUseCase() {
        overworldStatePresenter = PresentersFactory.getInstance().createOverworldStatePresenter();
        fatalErrorPresenter = PresentersFactory.getInstance().createFatalErrorPresenter();
        mapMetaDataGateway = GatewaysFactory.getInstance().createMapMetaDataGateway();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void startNewGame() {
        try {
            MapMetaData startingMapMetaData = mapMetaDataGateway.tryToRetrieveMetaDataForNewGameMap();
            overworldStatePresenter.presentOverworldWithMapFromMetaData(startingMapMetaData);  
        } catch (InformationForMapsCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        } catch (MapDataCouldNotBeLoadedException ex) {
            fatalErrorPresenter.showFatalError(ex.getErrorMessage(), ex.getErrorCause(), ex.getStackTraceString());
        }
    }
    
    //</editor-fold>
}