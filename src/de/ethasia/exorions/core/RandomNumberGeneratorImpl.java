package de.ethasia.exorions.core;

import de.ethasia.exorions.core.interfaces.RandomNumberGenerator;
import java.util.Random;

public class RandomNumberGeneratorImpl implements RandomNumberGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Random rng;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RandomNumberGeneratorImpl() {
        rng = new Random();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="RandomNumberGenerator Overrides">
    
    @Override
    public boolean createRandomBoolean() {
        return rng.nextBoolean();
    }

    @Override
    public int createRandomIntegerBetweenAnd(int lowerBound, int upperBound) {
        return rng.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }    
    
    //</editor-fold>    
}