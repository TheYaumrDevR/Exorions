package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.breeding.Genome;

public class MockGenome extends Genome {
        
    private final int fixedGeneValue;
        
    public MockGenome(int fixedGeneValue) {
        super(new Genome.Random());
        this.fixedGeneValue = fixedGeneValue;
    }
        
    @Override
    public int getMaximumHealthTotalGeneValue() {
        return fixedGeneValue;
    }
    
    @Override
    public int getAttackTotalGeneValue() {
        return fixedGeneValue;
    }    
    
    @Override
    public int getDefenseTotalGeneValue() {
        return fixedGeneValue;
    }
    
    @Override
    public int getSpecialAttackTotalGeneValue() {
        return fixedGeneValue;
    }
    
    @Override
    public int getSpecialDefenseTotalGeneValue() {
        return fixedGeneValue;
    }   
    
    @Override
    public int getAccuracyTotalGeneValue() {
        return fixedGeneValue;
    }

    @Override
    public int getEvasivenessTotalGeneValue() {
        return fixedGeneValue;
    }  
    
    @Override
    public int getCriticalHitFrequencyTotalGeneValue() {
        return fixedGeneValue;
    } 

    @Override
    public int getCriticalHitAvoidanceTotalGeneValue() {
        return fixedGeneValue;
    }    
    
    @Override
    public int getSwiftnessTotalGeneValue() {
        return fixedGeneValue;
    }
}
