package de.ethasia.exorions.ioadapters.mocks;

import de.ethasia.exorions.ioadapters.crosslayer.OverworldGameState;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

public class OverworldGameStateMock extends OverworldGameState {

    //<editor-fold defaultstate="collapsed" desc="OverworldGameState Overrides">
    
    @Override
    public void bind(Nifty nifty, Screen screen) {}

    @Override
    public void onStartScreen() {}

    @Override
    public void onEndScreen() {}    
    
    //</editor-fold>    
}