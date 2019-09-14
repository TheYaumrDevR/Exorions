package de.ethasia.exorions.core.maps;

public class TriggerTile implements MapTile {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private TileTrigger tileTrigger;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public TriggerTile() {}
    
    public TriggerTile(TileTrigger tileTrigger) {
        this.tileTrigger = tileTrigger;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean isGround() {
        return false;
    }
    
    @Override
    public boolean isCollidingTile() {
        return false;
    }
    
    @Override
    public void onSteppedOn() {
        if (null != tileTrigger) {
            tileTrigger.execute();
        }
    }
    
    //</editor-fold>
}