package de.ethasia.exorions.core.battle;

public class RequirementsToLearnAbilityAreNotFulfilledException extends RuntimeException {
    
    public RequirementsToLearnAbilityAreNotFulfilledException() {
        super("The given BattleAbility cannot be learnt, because the requirements are not fulfilled.");
    }
}