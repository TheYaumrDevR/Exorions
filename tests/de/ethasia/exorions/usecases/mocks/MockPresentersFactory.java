package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.interactors.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
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
}