package de.ethasia.exorions.core.maps;

import de.ethasia.exorions.core.general.SetValueIsNotWithinLegalBoundsException;

public class InteriorMap {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final int MAXIMUM_DIMENSION = 50;
    private static final int MAXIMUM_HEIGHT = 25;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private short xDimension, zDimension;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    public InteriorMap(short xDimension, short zDimension) {
        if (xDimension < 0 || zDimension < 0) {
            throw new SetValueIsNotWithinLegalBoundsException(0, MAXIMUM_DIMENSION);
        }
        
        if (xDimension > MAXIMUM_DIMENSION || zDimension > MAXIMUM_DIMENSION) {
            throw new SetValueIsNotWithinLegalBoundsException(0, MAXIMUM_DIMENSION);
        }
        
        this.xDimension = xDimension;
        this.zDimension = zDimension;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean tileAtIsColliding(short x, short y, short z) {
        if (x > MAXIMUM_DIMENSION || x < 0 || x > xDimension) {
            return true;
        }
        
        if (y > MAXIMUM_HEIGHT || y < 0) {
            return true;
        }
        
        return z > MAXIMUM_DIMENSION || z < 0 || z > zDimension;
    }
    
    //</editor-fold>
}