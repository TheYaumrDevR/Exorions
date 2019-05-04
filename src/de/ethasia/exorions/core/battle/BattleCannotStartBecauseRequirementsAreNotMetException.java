package de.ethasia.exorions.core.battle;

public class BattleCannotStartBecauseRequirementsAreNotMetException extends RuntimeException {
    
    public BattleCannotStartBecauseRequirementsAreNotMetException() {
        super("A battle can only start when two different non-empty teams are on the battlefield. All Exorion must have at least one ability.");
    }
}