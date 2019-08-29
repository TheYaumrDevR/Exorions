package de.ethasia.exorions.core.maps;

public class ShowNotificationBoxTileTrigger implements TileTrigger {
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public void execute() {
        Player.getInstance().setIsBusy(true);
    }
    
    //</editor-fold>
}