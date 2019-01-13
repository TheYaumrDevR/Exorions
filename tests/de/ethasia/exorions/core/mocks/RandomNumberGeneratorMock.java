package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.RandomNumberGenerator;

public class RandomNumberGeneratorMock implements RandomNumberGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int indexOfNextInt;
    private int indexOfNextBool;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private int[] integerSequenceToUse;
    public void setIntegerSequenceToUse(int[] value) {
        integerSequenceToUse = value;
    }
    
    private boolean[] booleanSequenceToUse;
    public void setBooleanSequenceToUse(boolean[] value) {
        booleanSequenceToUse = value;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RandomNumberGeneratorMock() {
        indexOfNextInt = 0;
        indexOfNextBool = 0;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="RandomNumberGenerator Overrides">
    
    @Override
    public boolean createRandomBoolean() {
        if (0 == booleanSequenceToUse.length) {
            return false;
        }
        
        boolean result;
        
        if (indexOfNextBool < booleanSequenceToUse.length) {
            result = booleanSequenceToUse[indexOfNextBool];
            indexOfNextBool++;
        } else {
            indexOfNextBool = 0;
            result = createRandomBoolean();
        }
        
        return result;
    }

    @Override
    public int createRandomIntegerBetweenAnd(int lowerBound, int upperBound) {
        if (0 == integerSequenceToUse.length) {
            return 0;
        }
        
        int result;
        
        if (indexOfNextInt < integerSequenceToUse.length) {
            result = integerSequenceToUse[indexOfNextInt];
            indexOfNextInt++;
        } else {
            indexOfNextInt = 0;
            result = createRandomIntegerBetweenAnd(lowerBound, upperBound);
        }
        
        if (result < lowerBound || result > upperBound) {
            throw new RuntimeException("Integer in sequence was out of bounds. Please specify a sequence within the bounds beforehand.");
        }
        
        return result;
    }    
    
    //</editor-fold>
}