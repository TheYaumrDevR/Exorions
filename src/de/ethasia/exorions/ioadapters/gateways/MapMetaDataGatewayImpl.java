package de.ethasia.exorions.ioadapters.gateways;

import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class MapMetaDataGatewayImpl {
    
    //<editor-fold defaultstate="collapsed" desc="MapMetaDataGateway Overrides">
    
    public MapMetaData tryToRetrieveMetaDataForNewGameMap() {
        TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
        
        Maps maps = technicalsFactory.createMaps();
        
        Document mapList = maps.readMapList();
        MapMetaData startingMapData = tryToFindMapDataOfStartingMap(mapList);         
        
        return startingMapData;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private MapMetaData tryToFindMapDataOfStartingMap(Document mapList) {
        String mapName = "";
        String logicFilePath = "";
        String visualFilePath = "";
        
        NodeList newGameMap = mapList.getElementsByTagName("newGameMap");
        if (newGameMap.getLength() > 0) {
            mapName = newGameMap.item(0).getAttributes().getNamedItem("name").getNodeValue();
            logicFilePath = newGameMap.item(0).getAttributes().getNamedItem("logic").getNodeValue();
            visualFilePath = newGameMap.item(0).getAttributes().getNamedItem("visuals").getNodeValue();
        } else {
            throw new MapDataCouldNotBeLoadedException("Information for the starting game map was not found. The maplist is corrupted.", "");
        }
        
        if (mapName.isEmpty() || logicFilePath.isEmpty() || visualFilePath.isEmpty()) {
            throw new MapDataCouldNotBeLoadedException("The name or the file paths for the starting game map are empty. Thus it cannot be loaded.", "");
        }
        
        return new MapMetaData(mapName, logicFilePath, visualFilePath);
    }    
    
    //</editor-fold>
}