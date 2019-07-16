package de.ethasia.exorions.ioadapters.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BattleAbilityPowerByLevelTables {
    
    public static Map<Integer, Float> getStandardAbilityPowerByLevelTable() {
        Map<Integer, Float> abilityPowerByAbilityLevel = new ConcurrentHashMap<>();
        
        abilityPowerByAbilityLevel.put(1, 0.9f);
        abilityPowerByAbilityLevel.put(2, 0.91f);
        abilityPowerByAbilityLevel.put(3, 0.92f);
        abilityPowerByAbilityLevel.put(4, 0.93f);
        abilityPowerByAbilityLevel.put(5, 0.94f);
        abilityPowerByAbilityLevel.put(6, 0.95f);
        abilityPowerByAbilityLevel.put(7, 0.96f);
        abilityPowerByAbilityLevel.put(8, 0.97f);
        abilityPowerByAbilityLevel.put(9, 0.98f);
        abilityPowerByAbilityLevel.put(10, 0.99f);
        abilityPowerByAbilityLevel.put(11, 1.f);
        abilityPowerByAbilityLevel.put(12, 1.01f);
        abilityPowerByAbilityLevel.put(13, 1.02f);
        abilityPowerByAbilityLevel.put(14, 1.03f);
        abilityPowerByAbilityLevel.put(15, 1.04f);
        abilityPowerByAbilityLevel.put(16, 1.05f);
        abilityPowerByAbilityLevel.put(17, 1.06f);
        abilityPowerByAbilityLevel.put(18, 1.07f);
        abilityPowerByAbilityLevel.put(19, 1.08f);
        abilityPowerByAbilityLevel.put(20, 1.1f);    
        
        return abilityPowerByAbilityLevel;
    }
}