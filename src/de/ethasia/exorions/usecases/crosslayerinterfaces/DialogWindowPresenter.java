package de.ethasia.exorions.usecases.crosslayerinterfaces;

import de.ethasia.exorions.usecases.maps.DialogOptionTextWithHandler;
import java.util.List;

public interface DialogWindowPresenter {
    
    public void showDialogWindowWithTextsAndHandlers(String currentDialogText, List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers);
}