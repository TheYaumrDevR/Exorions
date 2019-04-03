package de.ethasia.exorions.core;

public class BattleCalculator {
    
    public int calculateBaseStatForLevelFromStatAtMaximumLevel(int currentLevel, int statAtMaximumLevel) {
        int result = (int)((float)statAtMaximumLevel / ExorionSpecies.MAXIMUM_LEVEL * currentLevel);
        return result < 1 ? 1 : result;
    }
    
    public double calculateGeneValueStatIncreaseFactor(int geneValue) {
        double exponent = (-1 * geneValue / 25.0) + 2;
        return (1 / Math.pow(2, exponent));
    }
    
    public int calculateGenomeAdjustedStat(int baseStat, double genomeFactor) {
        int addedStat = (int)Math.round(baseStat * genomeFactor);
        
        return baseStat + addedStat;
    }
}