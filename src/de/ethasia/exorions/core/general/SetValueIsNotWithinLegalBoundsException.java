package de.ethasia.exorions.core.general;

public class SetValueIsNotWithinLegalBoundsException extends RuntimeException {
    
    public SetValueIsNotWithinLegalBoundsException(int min, int max) {
        super("The value you tried to set is not within the legal bounds for this property. Min: " + min + " Max: " + max);
    }
}