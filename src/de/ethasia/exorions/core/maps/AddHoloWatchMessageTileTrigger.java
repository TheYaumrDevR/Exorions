package de.ethasia.exorions.core.maps;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.holowatch.HoloWatchMessage;

public class AddHoloWatchMessageTileTrigger {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private HoloWatchMessage messageToAdd;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AddHoloWatchMessageTileTrigger(HoloWatchMessage message) {
        messageToAdd = message;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void execute() {
        if (null == messageToAdd) {
            throw new NotAllPropertiesAreSetException();
        }
        
        Player.getInstance().receiveHoloWatchMessage(messageToAdd);
    }
    
    //</editor-fold>
}