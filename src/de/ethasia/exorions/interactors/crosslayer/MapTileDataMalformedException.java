package de.ethasia.exorions.interactors.crosslayer;

public class MapTileDataMalformedException extends RuntimeException {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MapTileDataMalformedException(String detailAppendix) {
        super("The tile data for a map is malformed. It has either attributes missing or they are not in the correct format. " + detailAppendix);
    }
    
    //</editor-fold>
}