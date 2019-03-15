package de.ethasia.exorions.core;

public class BattleCalculator {
    
    public int calculateBaseStatForLevelFromStatAtMaximumLevel(int currentLevel, int statAtMaximumLevel) {
        int result = (int)((float)statAtMaximumLevel / 50 * currentLevel);
        return result < 1 ? 1 : result;
    }
}