package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.crosslayer.PlayerAvatarMovementPresenter;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;

public class MockPresentersFactory extends PresentersFactory {

    @Override
    public DialogWindowPresenter createDialogWindowPresenter() {
        return new DialogWindowPresenterMock();
    }

    @Override
    public OverworldStatePresenter createOverworldStatePresenter() {
        return new OverworldStatePresenterMock();
    }

    @Override
    public FatalErrorPresenter createFatalErrorPresenter() {
        return new FatalErrorPresenterMock();
    }
    
    @Override
    public PlayerAvatarMovementPresenter createPlayerAvatarMovementPresenter() {
        return new PlayerAvatarMovementPresenterMock();
    }    
}