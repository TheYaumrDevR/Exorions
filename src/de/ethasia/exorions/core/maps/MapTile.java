package de.ethasia.exorions.core.maps;

public interface MapTile {
    
    public boolean isCollidingTile();    
    public boolean isGround();   
    public void onSteppedOn();
}