package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.usecases.overworld.DialogOptionTextWithHandler;
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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void emptyLastSetFields() {
        lastSetDialogText = "";
        lastSetOptionTextWithHandlers = null;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void showDialogWindowWithTextsAndHandlers(String currentDialogText, List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers) {
        lastSetDialogText = currentDialogText;
        lastSetOptionTextWithHandlers = dialogOptionTextsWithHandlers;
    }    
    
    //</editor-fold>
}