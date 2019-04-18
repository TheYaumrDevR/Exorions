package de.ethasia.exorions.core.battle;

public class BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException extends RuntimeException {
    
    public BattleCanOnlyStartWithTwoDifferentNonEmptyTeamsException() {
        super("A battle can only start when two different non-empty teams are on the battlefield.");
    }
}