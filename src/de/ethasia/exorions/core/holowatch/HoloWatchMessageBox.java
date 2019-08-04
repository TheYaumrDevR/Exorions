package de.ethasia.exorions.core.holowatch;

import java.util.ArrayList;
import java.util.List;

public class HoloWatchMessageBox {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private List<HoloWatchMessage> messages;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public HoloWatchMessageBox() {
        messages = new ArrayList<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void putMessage(HoloWatchMessage message) {
        messages.add(message);
        messages.sort(HoloWatchMessage.getSortComparator());
    }
    
    public List<HoloWatchMessage> getMessages() {
        return messages;
    }
    
    //</editor-fold>
}