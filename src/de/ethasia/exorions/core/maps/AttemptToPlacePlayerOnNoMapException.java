package de.ethasia.exorions.core.maps;

public class AttemptToPlacePlayerOnNoMapException extends RuntimeException {
    
    public AttemptToPlacePlayerOnNoMapException() {
        super("It was attempted to place the player on no map (map was null). This is invalid.");
    }
}