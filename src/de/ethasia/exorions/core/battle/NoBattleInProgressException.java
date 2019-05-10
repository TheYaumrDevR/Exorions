package de.ethasia.exorions.core.battle;

public class NoBattleInProgressException extends RuntimeException {
    
    public NoBattleInProgressException() {
        super("Battle input is not possible when no battle is in progress.");
    }
}