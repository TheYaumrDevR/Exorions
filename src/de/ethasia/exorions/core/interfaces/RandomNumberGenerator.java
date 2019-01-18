package de.ethasia.exorions.core.interfaces;

public interface RandomNumberGenerator {
    
    public boolean createRandomBoolean();
    public int createRandomIntegerBetweenAnd(int lowerBound, int upperBound);
}