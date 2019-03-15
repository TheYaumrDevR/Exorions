package de.ethasia.exorions.core;

public class BattleCalculator {
    
    public int calculateBaseStatForLevelFromStatAtMaximumLevel(int currentLevel, int statAtMaximumLevel) {
        int result = (int)((float)statAtMaximumLevel / ExorionSpecies.MAXIMUM_LEVEL * currentLevel);
        return result < 1 ? 1 : result;
    }
}