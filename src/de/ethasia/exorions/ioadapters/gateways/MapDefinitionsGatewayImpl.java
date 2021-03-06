package de.ethasia.exorions.ioadapters.gateways;

import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
import de.ethasia.exorions.ioadapters.crosslayer.Maps;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import de.ethasia.exorions.interactors.crosslayer.MapDefinitionsGateway;
import de.ethasia.exorions.interactors.crosslayer.MapTileDataMalformedException;
import org.w3c.dom.NamedNodeMap;

public class MapDefinitionsGatewayImpl implements MapDefinitionsGateway {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private String lastPathToMapDefinitionRead;
    private Document lastDocumentRead;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MapDefinitionsGatewayImpl() {
        lastPathToMapDefinitionRead = "";
        lastDocumentRead = null;
    }
     
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="MapDefinitionsGateway Overrides">
    
    @Override
    public int getMapDimensionX(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);
            
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;
        
            return tryParseMapAttributeWithNameFromDocument("dimX", mapLogic);            
        }
        
        return tryParseMapAttributeWithNameFromDocument("dimX", lastDocumentRead); 
    }
    
    @Override
    public int getMapDimensionZ(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);
        
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;            
            
            return tryParseMapAttributeWithNameFromDocument("dimZ", mapLogic);            
        }

        return tryParseMapAttributeWithNameFromDocument("dimZ", lastDocumentRead); 
    }    
    
    @Override
    public MapMetaData tryToRetrieveMetaDataForNewGameMap() {
        TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
        
        Maps maps = technicalsFactory.createMaps();
        
        Document mapList = maps.readMapList();
        MapMetaData startingMapData = tryToFindMapDataOfStartingMap(mapList);         
        
        return startingMapData;
    }
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findFloorTileDefinitions(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);
            
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;             
        
            return convertDocumentToFloorTileDefinitions(mapLogic);            
        }

        return convertDocumentToFloorTileDefinitions(lastDocumentRead); 
    }
    
    @Override
    public DefinitionsForUndistinguishableMapTiles findCollisionTileDefinitions(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);
            
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;             
        
            return convertDocumentToCollisionTileDefinitions(mapLogic);                    
        }
        
        return convertDocumentToCollisionTileDefinitions(lastDocumentRead);        
    }    
    
    @Override
    public int getInitialPlayerPositionX(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);   
        
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;      
            
            return tryParseMapAttributeWithNameFromDocument("initialX", mapLogic);             
        }
 
        return tryParseMapAttributeWithNameFromDocument("initialX", lastDocumentRead);  
    }
    
    @Override
    public int getInitialPlayerPositionY(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {    
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();              
            
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);  
            
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;            
            
            return tryParseMapAttributeWithNameFromDocument("initialY", mapLogic);              
        }  
        
        return tryParseMapAttributeWithNameFromDocument("initialY", lastDocumentRead);
    }
    
    @Override
    public int getInitialPlayerPositionZ(String pathToMapDefinition) {
        if (!lastPathToMapDefinitionRead.equals(pathToMapDefinition)) {   
            TechnicalsFactory technicalsFactory = TechnicalsFactory.getInstance();
            Maps maps = technicalsFactory.createMaps();  
        
            Document mapLogic = maps.readMapLogic(pathToMapDefinition);  
            
            lastPathToMapDefinitionRead = pathToMapDefinition;
            lastDocumentRead = mapLogic;              
            
            return tryParseMapAttributeWithNameFromDocument("initialZ", mapLogic);             
        }        
        
        return tryParseMapAttributeWithNameFromDocument("initialZ", lastDocumentRead);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private int tryParseMapAttributeWithNameFromDocument(String dimensionAttributeName, Document source) {
        int result = 0;
        
        NodeList mapNodes = source.getElementsByTagName("map");
        
        if (mapNodes.getLength() > 0) {
            NamedNodeMap mapNodeAttributes = mapNodes.item(0).getAttributes();
            
            try {
                result = Integer.parseInt(mapNodeAttributes.getNamedItem(dimensionAttributeName).getNodeValue());
            } catch (NumberFormatException | NullPointerException ex) {
                throw new MapTileDataMalformedException("A map must have a dimX, dimZ, initialX, initialY and initialZ attribute in its definition.");
            }
        } else {
            throw new MapDataCouldNotBeLoadedException("Information for the map could not be loaded, because the map node is missing.", "");
        }
        
        return result;
    }
    
    private MapMetaData tryToFindMapDataOfStartingMap(Document mapList) {
        String mapName = "";
        String logicFilePath = "";
        String visualFilePath = "";
        
        NodeList newGameMap = mapList.getElementsByTagName("newGameMap");
        if (newGameMap.getLength() > 0) {
            try {
                mapName = newGameMap.item(0).getAttributes().getNamedItem("name").getNodeValue();
                logicFilePath = newGameMap.item(0).getAttributes().getNamedItem("logic").getNodeValue();
                visualFilePath = newGameMap.item(0).getAttributes().getNamedItem("visuals").getNodeValue();                
            } catch (NullPointerException ex) {
                throw new MapDataCouldNotBeLoadedException("The name or the file paths for the starting game map are not readable. Thus it cannot be loaded.", "");                
            }
        } else {
            throw new MapDataCouldNotBeLoadedException("Information for the starting game map was not found. The maplist is corrupted.", "");
        }
        
        if (mapName.isEmpty() || logicFilePath.isEmpty() || visualFilePath.isEmpty()) {
            throw new MapDataCouldNotBeLoadedException("The name or the file paths for the starting game map are empty. Thus it cannot be loaded.", "");
        }
        
        return new MapMetaData(mapName, logicFilePath, visualFilePath);
    }    
    
    private DefinitionsForUndistinguishableMapTiles convertDocumentToFloorTileDefinitions(Document mapLogic) {
        DefinitionsForUndistinguishableMapTiles result = new DefinitionsForUndistinguishableMapTiles();
        NodeList floors = mapLogic.getElementsByTagName("f");
        
        for (int i = 0; i < floors.getLength(); i++) {
            NamedNodeMap floorNodeAttributes = floors.item(i).getAttributes();
            
            try {
                int floorWidth = Integer.parseInt(floorNodeAttributes.getNamedItem("w").getNodeValue());
                int floorDepth = Integer.parseInt(floorNodeAttributes.getNamedItem("l").getNodeValue());
                int floorPosX = Integer.parseInt(floorNodeAttributes.getNamedItem("x").getNodeValue());
                int floorPosY = Integer.parseInt(floorNodeAttributes.getNamedItem("y").getNodeValue());
                int floorPosZ = Integer.parseInt(floorNodeAttributes.getNamedItem("z").getNodeValue());
                
                result.addNewDefinitionWidthLengthHeightXyz(floorWidth, floorDepth, 1, floorPosX, floorPosY, floorPosZ);
            } catch (NumberFormatException | NullPointerException ex) {
                throw new MapTileDataMalformedException("Floor data must have attributes w, l, x, y, z of type integer.");
            }
        }        
        
        return result;
    }
    
    private DefinitionsForUndistinguishableMapTiles convertDocumentToCollisionTileDefinitions(Document mapLogic) {
        DefinitionsForUndistinguishableMapTiles result = new DefinitionsForUndistinguishableMapTiles();
        NodeList collisions = mapLogic.getElementsByTagName("c");
        
        for (int i = 0; i < collisions.getLength(); i++) {
            NamedNodeMap collisionNodeAttributes = collisions.item(i).getAttributes();
            
            try {
                int collisionWidth = Integer.parseInt(collisionNodeAttributes.getNamedItem("w").getNodeValue());
                int collisionDepth = Integer.parseInt(collisionNodeAttributes.getNamedItem("l").getNodeValue());
                int collisionHeight = Integer.parseInt(collisionNodeAttributes.getNamedItem("h").getNodeValue());
                int collisionPosX = Integer.parseInt(collisionNodeAttributes.getNamedItem("x").getNodeValue());
                int collisionPosY = Integer.parseInt(collisionNodeAttributes.getNamedItem("y").getNodeValue());
                int collisionPosZ = Integer.parseInt(collisionNodeAttributes.getNamedItem("z").getNodeValue());
                
                result.addNewDefinitionWidthLengthHeightXyz(collisionWidth, collisionDepth, collisionHeight, collisionPosX, collisionPosY, collisionPosZ);
            } catch (NumberFormatException | NullPointerException ex) {
                throw new MapTileDataMalformedException("Collision data must have attributes w, l, h, x, y, z of type integer.");
            }
        }

        return result;
    }
    
    //</editor-fold>
}