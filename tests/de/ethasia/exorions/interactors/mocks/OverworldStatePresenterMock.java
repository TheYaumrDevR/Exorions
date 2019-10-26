package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;

public class OverworldStatePresenterMock implements OverworldStatePresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
        
    private static String lastPresentedMapName = "";
    public static String getLastPresentedMapName() {
        return lastPresentedMapName;
    }
    
    private static int presentOverworldWithMapFromMetaData;
    public static int getPresentOverworldWithMapFromMetaDataCallCounter() {
        return presentOverworldWithMapFromMetaData;
    }
    
    private static RuntimeException nextExceptionThrownOnMethodCall;
    public static void setNextExceptionThrownOnMethodCall(RuntimeException value) {
        nextExceptionThrownOnMethodCall = value;
    }
        
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastPresentedMapName = "";
        presentOverworldWithMapFromMetaData = 0;
        nextExceptionThrownOnMethodCall = null;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OverworldStatePresenter Overrides">
    
    @Override
    public void  presentOverworldWithMapFromMetaData(MapMetaData newGameMapMetaData) {
        if (null != nextExceptionThrownOnMethodCall) {
            throw nextExceptionThrownOnMethodCall;
        }
        
        presentOverworldWithMapFromMetaData++;
    }    
    
    //</editor-fold>
}