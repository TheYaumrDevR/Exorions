package de.ethasia.exorions.interactors.overworld;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.core.dialogs.DialogOption;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.interactors.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;
import java.util.List;
import java.util.stream.Collectors;

public class DialogInteractionUseCase implements InteractionTileUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final DialogWithOptions dialogToManage;
    private final DialogWindowPresenter dialogWindowPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public DialogInteractionUseCase(DialogWithOptions dialogToManage) {
        this.dialogToManage = dialogToManage;
        dialogWindowPresenter = PresentersFactory.getInstance().createDialogWindowPresenter();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void startInteraction() {
        if (null == dialogToManage) {
            throw new NotAllPropertiesAreSetException();
        }
        
        Player.getInstance().setIsBusy(true);
        List<DialogOptionTextWithHandler> dialogOptionTextsWithHandlers = convertDialogOptionsToTextsWithHandlers(dialogToManage.getOptions());
        dialogWindowPresenter.showDialogWindowWithTextsAndHandlers(dialogToManage.getText(), dialogOptionTextsWithHandlers);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void cancelInteraction() {
        Player.getInstance().setIsBusy(false);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helpers">
    
    private List<DialogOptionTextWithHandler> convertDialogOptionsToTextsWithHandlers(List<DialogOption> dialogOptions) {
        List<DialogOptionTextWithHandler> result = dialogOptions.stream().map((DialogOption dialogOption) -> {
            DialogOptionSelectionHandler dialogOptionSelectionHandler;
            
            if (null != dialogOption.getFollowUpNode()) {
                if (dialogOption.getFollowUpNode().isLeaf()) {
                    dialogOptionSelectionHandler = () -> {
                        dialogWindowPresenter.showDialogWindowWithDialogEndText(dialogOption.getFollowUpNode().getText());
                    };                    
                } else {
                    dialogOptionSelectionHandler = () -> {
                
                    };                     
                }
            } else {
                dialogOptionSelectionHandler = () -> {
                    Player.getInstance().setIsBusy(true);
                };                
            }
            
            return new DialogOptionTextWithHandler.Builder()
                    .setText(dialogOption.getText())
                    .setSelectionHandler(dialogOptionSelectionHandler)
                    .build();
        }).collect(Collectors.toList());
        
        return result;
    }
    
    //</editor-fold>
}