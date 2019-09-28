package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayer.OverworldStatePresenter;

public class OverworldStatePresenterMock implements OverworldStatePresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
        
    private static String lastPresentedMapName = "";
    public static String getLastPresentedMapName() {
        return lastPresentedMapName;
    }
    
    private static int presentOverworldWithNewGameMapCallCounter;
    public static int getPresentOverworldWithNewGameMapCallCounter() {
        return presentOverworldWithNewGameMapCallCounter;
    }
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastPresentedMapName = "";
        presentOverworldWithNewGameMapCallCounter = 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OverworldStatePresenter Overrides">
    
    @Override
    public void presentOverworldWithNewGameMap() {
        presentOverworldWithNewGameMapCallCounter++;
    }    
    
    //</editor-fold>
}