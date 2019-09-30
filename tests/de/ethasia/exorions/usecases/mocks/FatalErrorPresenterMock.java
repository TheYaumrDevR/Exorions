package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;

public class FatalErrorPresenterMock implements FatalErrorPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
        
    private static String lastShownError = "";
    public static String getLastShownError() {
        return lastShownError;
    }
    
    private static String lastShownErrorCause = "";
    public static String getLastShownErrorCause() {
        return lastShownErrorCause;
    }
    
    private static String lastShownStackTrace = "";
    public static String getLastShownStackTrace() {
        return lastShownStackTrace;
    }
        
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastShownError = "";
        lastShownErrorCause = "";
        lastShownStackTrace = "";
    }
    
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="FatalErrorPresenter Overrides">
    
    @Override
    public void showFatalError(String error, String cause, String stackTrace) {
        lastShownError = error;
        lastShownErrorCause = cause;
        lastShownStackTrace = stackTrace;
    }    
    
    //</editor-fold>
}