package de.ethasia.exorions.core.maps;

public class Player extends MoveableMapObject {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static Player instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private Player() {
        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        
        return instance;
    }
    
    //</editor-fold>
}