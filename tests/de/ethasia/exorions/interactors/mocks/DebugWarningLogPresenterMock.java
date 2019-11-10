package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.DebugWarningLogPresenter;

public class DebugWarningLogPresenterMock implements DebugWarningLogPresenter {

    //<editor-fold defaultstate="collapsed" desc="Static Mock Properties">
    
    private static String lastAddedLogEntry;
    public static String getLastAddedLogEntry() {
        return lastAddedLogEntry;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void resetMock() {
        lastAddedLogEntry = "";
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="DebugWarningLogPresenter Overrides">
    
    @Override
    public void addLogEntry(String message) {
        lastAddedLogEntry = message;
    }

    @Override
    public void presentLogWindow() {
    }    
    
    //</editor-fold>
}