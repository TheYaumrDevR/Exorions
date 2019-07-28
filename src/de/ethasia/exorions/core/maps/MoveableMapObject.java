package de.ethasia.exorions.core.maps;

public class MoveableMapObject {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private short posX, posY, posZ;
    private InteriorMap currentMap;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeOnMapWithPosition(InteriorMap map, short x, short y, short z) {
        currentMap = map;
        posX = x;
        posY = y;
        posZ = z;
    }
    
    public void moveTo(MoveDirections direction) {
        if (null == currentMap) {
            throw new NoMapToMoveOnException();
        }
        
        relocateBasedOnMapAndMoveDirection(direction);
    }
    
    public short getPositionX() {
        return posX;
    }
    
    public short getPositionY() {
        return posY;
    }
    
    public short getPositionZ() {
        return posZ;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helpers">
    
    private void relocateBasedOnMapAndMoveDirection(MoveDirections direction) {
        short newX = 0;
        short newZ = 0;
        
        switch(direction) {
            case UP:
                newZ = (short)(posZ - 1);
                break;                
            case RIGHT:
                newX = (short)(posX + 1);
                break;
            case DOWN:
                newZ = (short)(posZ + 1);
                break;
            case LEFT:
                newX = (short)(posX - 1);
                break;
        } 
        
        setPositionIfPositionIsNotColliding(newX, posY, newZ);
    }
    
    private void setPositionIfPositionIsNotColliding(short x, short y, short z) {
        if (!currentMap.tileAtIsColliding(x, y, z)) {
            posX = x;
            posY = y;
            posZ = z;
        }        
    }
    
    //</editor-fold>
}