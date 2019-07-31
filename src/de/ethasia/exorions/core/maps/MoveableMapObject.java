package de.ethasia.exorions.core.maps;

public class MoveableMapObject {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    public static final int MINIMUM_TIME_BETWEEN_MOVEMENTS_MILLIS = 333;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private short posX, posY, posZ;
    private InteriorMap currentMap;
    
    private long lastMovementTimeMillis;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void placeOnMapWithPosition(InteriorMap map, short x, short y, short z) {
        if (!map.tileAtIsColliding(x, y, z)) {
            currentMap = map;
            posX = x;
            posY = y;
            posZ = z;            
        }
    }
    
    public boolean willMoveTo(MoveDirections direction) {
        if (null == currentMap) {
            return false;
        }
        
        short newX = posX;
        short newZ = posZ;
        
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
        
        return !currentMap.tileAtIsColliding(newX, posY, newZ);
    }
    
    public void moveTo(MoveDirections direction) {
        if (null == currentMap) {
            throw new NoMapToMoveOnException();
        } 
        
        moveToIfLastMovementWasLongEnoughInThePast(direction);
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
    
    public InteriorMap getMapPlacedOn() {
        return currentMap;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helpers">
    
    private void moveToIfLastMovementWasLongEnoughInThePast(MoveDirections direction) {
        long currentTimeMillis = System.currentTimeMillis();
        if (lastMovementWasBackFurtherThanTheMinimumMovementTime(currentTimeMillis)) {
            lastMovementTimeMillis = currentTimeMillis;
            relocateBasedOnMapAndMoveDirection(direction);
        }        
    }
    
    private boolean lastMovementWasBackFurtherThanTheMinimumMovementTime(long currentTimeMillis) {
        return currentTimeMillis - lastMovementTimeMillis > MINIMUM_TIME_BETWEEN_MOVEMENTS_MILLIS;
    }
    
    private void relocateBasedOnMapAndMoveDirection(MoveDirections direction) {
        short newX = posX;
        short newZ = posZ;
        
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