package de.ethasia.exorions.ioadapters;

import de.ethasia.exorions.interactors.crosslayer.PlayerMovementUseCase;
import de.ethasia.exorions.interactors.overworld.PlayerMovementUseCaseImpl;

public class UseCasesFactoryIoAdaptersImpl extends UseCasesFactoryIoAdapters {

    //<editor-fold defaultstate="collapsed" desc="UseCasesFactoryIoAdapters Overrides">
    
    @Override
    public PlayerMovementUseCase createPlayerMovementUseCase() {
        return new PlayerMovementUseCaseImpl();
    }    
    
    //</editor-fold>    
}