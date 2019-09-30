package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.crosslayer.DialogWindowPresenter;
import de.ethasia.exorions.interactors.crosslayer.FatalErrorPresenter;
import de.ethasia.exorions.interactors.crosslayer.OverworldStatePresenter;
import de.ethasia.exorions.interactors.interfaces.PresentersFactory;

public class RealPresentersFactory extends PresentersFactory {

    //<editor-fold defaultstate="collapsed" desc="PresentersFactory Overrides">
    
    @Override
    public DialogWindowPresenter createDialogWindowPresenter() {
        return null;
    }

    @Override
    public OverworldStatePresenter createOverworldStatePresenter() {
        return new OverworldStatePresenterImpl();
    }

    @Override
    public FatalErrorPresenter createFatalErrorPresenter() {
        return null;
    }    
    
    //</editor-fold>    
}