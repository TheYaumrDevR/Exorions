package de.ethasia.exorions.ioadapters.controllers.mocks;

import de.ethasia.exorions.ioadapters.UseCasesFactoryIoAdapters;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerMovementUseCase;

public class UseCasesMockFactoryIoAdapters extends UseCasesFactoryIoAdapters {

    //<editor-fold defaultstate="collapsed" desc="UseCasesFactoryIoAdapters Overrides">
    
    @Override
    public PlayerMovementUseCase createPlayerMovementUseCase() {
        return new PlayerMovementUseCaseMock();
    }    
    
    //</editor-fold>    
}