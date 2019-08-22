package de.ethasia.exorions.core.maps;

public enum MapTileTypes implements MapTile {
    FLOOR {
    
        @Override
        public boolean isCollidingTile() {
            return false;
        }
        
        @Override
        public boolean isGround() {
            return true;
        }
        
        @Override
        public void onSteppedOn() {}
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
        
        @Override
        public void onSteppedOn() {}        
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
        
        @Override
        public void onSteppedOn() {}        
    };
}