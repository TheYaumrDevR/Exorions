package de.ethasia.exorions.core.maps;

import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import de.ethasia.exorions.core.holowatch.HoloWatchMessageBox;

import java.util.List;

public class Player extends MoveableMapObject {
    
    //<editor-fold defaultstate="collapsed" desc="Static Fields">
    
    private static Player instance;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final HoloWatchMessageBox holoWatchMessageBox;
    private boolean isBusy;
    private MoveDirections facingDirection;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters / Setters">
    
    public void setIsBusy(boolean value) {
        isBusy = value;
    }
    
    public boolean isBusy() {
        return isBusy;
    }
    
    public MoveDirections getFacingDirection() {
        return facingDirection;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private Player() {
        holoWatchMessageBox = new HoloWatchMessageBox();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        
        return instance;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void clearHoloWatchMessages() {
        holoWatchMessageBox.clear();
    }
    
    public void receiveHoloWatchMessage(HoloWatchMessage message) {
        holoWatchMessageBox.putMessage(message);
    }
    
    public List<HoloWatchMessage> getAllMessages() {
        return holoWatchMessageBox.getMessages();
    }
    
    @Override
    public void placeOnMapWithPosition(InteriorMap map, short x, short y, short z) {
        super.placeOnMapWithPosition(map, x, y, z);
        
        if (null != currentMap) {
            currentMap.stepOnTileAt(posX, posY, posZ);            
        }
    }    
    
    @Override
    public void moveTo(MoveDirections direction) {
        if (!isBusy) {
            facingDirection = direction;
            super.moveTo(direction);
        }
    }
    
    @Override
    public boolean willMoveTo(MoveDirections direction) {
        if (isBusy) {
            return false;
        }
        
        return super.willMoveTo(direction);
    }
    
    public void turnDown() {
        facingDirection = MoveDirections.DOWN;
    }
    
    public void turnRight() {
        facingDirection = MoveDirections.RIGHT;
    }
    
    public void turnUp() {
        facingDirection = MoveDirections.UP;
    }
    
    public void turnLeft() {
        facingDirection = MoveDirections.LEFT;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    @Override
    protected boolean setPositionIfPositionIsNotColliding(short x, short y, short z) {    
        boolean result = super.setPositionIfPositionIsNotColliding(x, y, z);
        currentMap.stepOnTileAt(posX, posY, posZ);
        
        return result;
    }
    
    //</editor-fold>
}