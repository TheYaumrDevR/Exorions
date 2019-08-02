package de.ethasia.exorions.core.maps;

public enum MapTileTypes {
    FLOOR {
    
        @Override
        public boolean isCollidingTile() {
            return false;
        }
        
        @Override
        public boolean isGround() {
            return true;
        }
    },
    
    COLLISION {
        
        @Override
        public boolean isCollidingTile() {
            return true;
        }
        
        @Override
        public boolean isGround() {
            return false;
        }        
    },

    WARP {

        @Override
        public boolean isCollidingTile() {
            return false;
        }        
        
        @Override
        public boolean isGround() {
            return false;
        }        
    },

    TRIGGER {

        @Override
        public boolean isCollidingTile() {
            return false;
        }
        
        @Override
        public boolean isGround() {
            return false;
        }        
    };
    
    public abstract boolean isCollidingTile();
    public abstract boolean isGround();
}