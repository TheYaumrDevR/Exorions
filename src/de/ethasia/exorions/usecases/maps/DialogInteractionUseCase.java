package de.ethasia.exorions.usecases.maps;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.core.dialogs.DialogWithOptions;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.maps.Player;

public class DialogInteractionUseCase implements InteractionTileUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final DialogWithOptions dialogToManage;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public DialogInteractionUseCase(DialogWithOptions dialogToManage) {
        this.dialogToManage = dialogToManage;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public void startInteraction() {
        if (null == dialogToManage) {
            throw new NotAllPropertiesAreSetException();
        }
        
        Player.getInstance().setIsBusy(true);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void cancelInteraction() {
        Player.getInstance().setIsBusy(false);
    }
    
    //</editor-fold>
}