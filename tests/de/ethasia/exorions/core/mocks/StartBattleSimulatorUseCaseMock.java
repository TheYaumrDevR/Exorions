package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;

public class StartBattleSimulatorUseCaseMock implements InteractionTileUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Mock Methods">
    
    private static int startInteractionCallCountSinceLastReset;
    public static int getStartInteractionCallCountSinceLastReset() {
        return startInteractionCallCountSinceLastReset;
    }
    
    public static void resetMethodCallCounters() {
        startInteractionCallCountSinceLastReset = 0;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void startInteraction() {
        startInteractionCallCountSinceLastReset++;
    }    
    
    //</editor-fold>
}