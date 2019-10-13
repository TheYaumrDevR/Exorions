package de.ethasia.exorions.interactors.crosslayer;

import de.ethasia.exorions.interactors.overworld.DialogOptionTextWithHandler;
import java.util.List;

public interface DialogWindowPresenter {
    
    public void showDialogWindowWithTextsAndHandlers(String currentDialogText, List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers);
    public void showDialogWindowWithDialogEndText(String dialogEndText);
}