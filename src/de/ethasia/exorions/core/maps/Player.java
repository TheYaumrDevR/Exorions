package de.ethasia.exorions.core.maps;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.holowatch.HoloWatchMessageBox;

import java.util.List;

public class Player extends MoveableMapObject {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static Player instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private HoloWatchMessageBox holoWatchMessageBox;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private Player() {
        holoWatchMessageBox = new HoloWatchMessageBox();
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
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void clearHoloWatchMessages() {
        holoWatchMessageBox.clear();
    }
    
    public void receiveHoloWatchMessage(HoloWatchMessage message) {
        holoWatchMessageBox.putMessage(message);
    }
    
    public List<HoloWatchMessage> getAllMessages() {
        return holoWatchMessageBox.getMessages();
    }
    
    //</editor-fold>
}