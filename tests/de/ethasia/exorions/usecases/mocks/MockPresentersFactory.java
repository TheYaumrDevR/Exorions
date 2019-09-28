package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;

public class MockPresentersFactory extends PresentersFactory {

    @Override
    public DialogWindowPresenter createDialogWindowPresenter() {
        return new DialogWindowPresenterMock();
    }
}