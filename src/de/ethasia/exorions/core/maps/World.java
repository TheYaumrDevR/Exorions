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
    
    public void setActiveMap(String mapIdentifier) {
        activeMap = interiorMapsByIdentifier.get(mapIdentifier);
    }
    
    public InteriorMap getActiveMap() {
        return activeMap;
    }
    
    //</editor-fold>
}