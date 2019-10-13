package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.crosslayerinterfaces.InteractionTileUseCase;
import de.ethasia.exorions.core.interfaces.UseCasesFactory;

public class UseCasesMockFactory extends UseCasesFactory {

    @Override
    public InteractionTileUseCase createStartBattleSimulatorUseCase() {
        return new StartBattleSimulatorUseCaseMock();
    }
}