package de.ethasia.exorions.core.general;

public class DecoratorMustDecorateSomethingException extends RuntimeException {
    
    public DecoratorMustDecorateSomethingException() {
        super("A decorator must decorate another class and cannot be used alone.");
    }
}