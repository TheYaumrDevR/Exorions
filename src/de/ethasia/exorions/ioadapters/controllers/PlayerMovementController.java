package de.ethasia.exorions.ioadapters.controllers;

import de.ethasia.exorions.ioadapters.UseCasesFactoryIoAdapters;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerMovementUseCase;

public class PlayerMovementController {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final PlayerMovementUseCase playerMovementUc;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public PlayerMovementController() {
        playerMovementUc = UseCasesFactoryIoAdapters.getInstance().createPlayerMovementUseCase();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void moveDown() {
        playerMovementUc.moveDown();
    }
    
    public void moveRight() {
        playerMovementUc.moveRight();
    }
    
    public void moveUp() {
        playerMovementUc.moveUp();
    }
    
    public void moveLeft() {
        playerMovementUc.moveLeft();
    }
    
    //</editor-fold>
}