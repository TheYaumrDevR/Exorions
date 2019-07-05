package de.ethasia.exorions.core.general;

public class PersistedEntityNotFoundException extends RuntimeException {
    
    public PersistedEntityNotFoundException() {
        super("The persisted entity with the specified lookup criteria was not found. You used a non-existent key or other filtering predicate.");
    }
}