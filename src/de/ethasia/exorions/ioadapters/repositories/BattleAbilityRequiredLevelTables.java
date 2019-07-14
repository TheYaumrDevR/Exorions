package de.ethasia.exorions.ioadapters.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleAbilityRequiredLevelTables {
    
    public static Map<Integer, Integer> getRequiredLevelTableForBasicLevelOneAbility() {
        Map<Integer, Integer> requiredLevelByAbilityLevel = new ConcurrentHashMap<>();        
        
        requiredLevelByAbilityLevel.put(1, 1);
        requiredLevelByAbilityLevel.put(2, 3);
        requiredLevelByAbilityLevel.put(3, 5);
        requiredLevelByAbilityLevel.put(4, 7);
        requiredLevelByAbilityLevel.put(5, 9);
        requiredLevelByAbilityLevel.put(6, 11);
        requiredLevelByAbilityLevel.put(7, 13);
        requiredLevelByAbilityLevel.put(8, 15);
        requiredLevelByAbilityLevel.put(9, 17);
        requiredLevelByAbilityLevel.put(10, 19);
        requiredLevelByAbilityLevel.put(11, 21);
        requiredLevelByAbilityLevel.put(12, 23);
        requiredLevelByAbilityLevel.put(13, 25);
        requiredLevelByAbilityLevel.put(14, 27);
        requiredLevelByAbilityLevel.put(15, 29);
        requiredLevelByAbilityLevel.put(16, 31);
        requiredLevelByAbilityLevel.put(17, 33);
        requiredLevelByAbilityLevel.put(18, 35);
        requiredLevelByAbilityLevel.put(19, 37);
        requiredLevelByAbilityLevel.put(20, 39);
        
        return requiredLevelByAbilityLevel;
    }
}