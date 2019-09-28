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
    
    private static RuntimeException nextExceptionThrownOnMethodCall;
    public static void setNextExceptionThrownOnMethodCall(RuntimeException value) {
        nextExceptionThrownOnMethodCall = value;
    }
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastPresentedMapName = "";
        presentOverworldWithNewGameMapCallCounter = 0;
        nextExceptionThrownOnMethodCall = null;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OverworldStatePresenter Overrides">
    
    @Override
    public void presentOverworldWithNewGameMap() {
        if (null != nextExceptionThrownOnMethodCall) {
            throw nextExceptionThrownOnMethodCall;
        }
        
        presentOverworldWithNewGameMapCallCounter++;
    }    
    
    //</editor-fold>
}