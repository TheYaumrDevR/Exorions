package de.ethasia.exorions.usecases.crosslayer;

import de.ethasia.exorions.usecases.overworld.DialogOptionTextWithHandler;
import java.util.List;

public interface DialogWindowPresenter {
    
    public void showDialogWindowWithTextsAndHandlers(String currentDialogText, List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers);
}