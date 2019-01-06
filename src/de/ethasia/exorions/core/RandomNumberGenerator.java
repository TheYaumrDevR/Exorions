package de.ethasia.exorions.core;

public interface RandomNumberGenerator {
    
    public boolean createRandomBoolean();
    public int createRandomIntegerBetweenAnd(int lowerBound, int upperBound);
}