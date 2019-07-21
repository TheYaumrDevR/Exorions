package de.ethasia.exorions.core.maps;

public enum MapTileTypes {
    COLLISION {
        
        @Override
        public boolean isCollidingTile() {
            return true;
        }
    },

    WARP {

        @Override
        public boolean isCollidingTile() {
            return false;
        }        
    },

    TRIGGER {

        @Override
        public boolean isCollidingTile() {
            return false;
        }
    };
    
    public abstract boolean isCollidingTile();
}