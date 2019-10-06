package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayerinterfaces.DialogWindowPresenter;
import de.ethasia.exorions.usecases.maps.DialogOptionTextWithHandler;
import java.util.List;

public class DialogWindowPresenterMock implements DialogWindowPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private static String lastSetDialogText;
    public static String getLastSetDialogText() {
        return lastSetDialogText;
    }
    
    private static List<DialogOptionTextWithHandler> lastSetOptionTextWithHandlers;
    public static List<DialogOptionTextWithHandler> getLastSetOptionTextWithHandlers() {
        return lastSetOptionTextWithHandlers;
    }
    
    private static String lastSetEndText;
    public static String getLastSetEndText() {
        return lastSetEndText;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastSetDialogText = "";
        lastSetOptionTextWithHandlers = null;
        lastSetEndText = "";
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showDialogWindowWithTextsAndHandlers(String currentDialogText, List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers) {
        lastSetDialogText = currentDialogText;
        lastSetOptionTextWithHandlers = dialogOptionTextsWithHandlers;
    }  
    
    @Override
    public void showDialogWindowWithDialogEndText(String dialogEndText) {
        lastSetEndText = dialogEndText;
    }    
    
    //</editor-fold>
}