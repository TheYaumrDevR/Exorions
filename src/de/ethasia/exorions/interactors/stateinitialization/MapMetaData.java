package de.ethasia.exorions.interactors.stateinitialization;

public class MapMetaData {
        
    private final String mapName;
    
    /** Used to locate the information for the map logic. This can be any type of locator, e.g. a file path. */
    private final String mapLogicLocator;
    
    /** Used to locate the visuals of the map. This can be any type of locator, e.g. a file path. */
    private final String mapVisualsLocator;
        
    public MapMetaData(String mapName, String logicFilePath, String visualFilePath) {
        this.mapName = mapName;
        this.mapLogicLocator = logicFilePath;
        this.mapVisualsLocator = visualFilePath;
    }
        
    public String getMapName() {
        return mapName;
    }
        
    public String getLogicFilePath() {
        return mapLogicLocator;
    }
        
    public String getVisualFilePath() {
        return mapVisualsLocator;
    }
}