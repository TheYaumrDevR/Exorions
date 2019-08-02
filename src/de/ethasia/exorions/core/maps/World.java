package de.ethasia.exorions.core.maps;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Map<String, InteriorMap> interiorMapsByIdentifier;
    private InteriorMap activeMap;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public World() {
        interiorMapsByIdentifier = new ConcurrentHashMap<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeInteriorMapWithIdentifier(String identifier, InteriorMap toPlace) {
        interiorMapsByIdentifier.put(identifier, toPlace);
    }
    
    public void setActiveMapWithPlayerPosition(String mapIdentifier, short posX, short posY, short posZ) {
        Player player = Player.getInstance();
        InteriorMap mapToPlaceOn = interiorMapsByIdentifier.get(mapIdentifier);
        
        player.placeOnMapWithPosition(mapToPlaceOn, posX, posY, posZ);
        InteriorMap mapPlayerIsOn = player.getMapPlacedOn();
        
        if (mapPlayerIsOn == mapToPlaceOn) {
            activeMap = mapToPlaceOn;          
        }
    }
    
    public InteriorMap getActiveMap() {
        return activeMap;
    }
    
    //</editor-fold>
}