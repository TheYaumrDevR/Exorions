package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.usecases.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.usecases.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;

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