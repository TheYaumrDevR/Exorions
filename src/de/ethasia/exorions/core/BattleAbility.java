package de.ethasia.exorions.core;

import java.util.Set;

public abstract class BattleAbility {
    
    public abstract String getName();
    public abstract Set<DamageTypes> getDamageTypes();
    public abstract Set<AbilityLearningRequirements> getLearningRequirements();
    public abstract float getDelayMultiplier();
    public abstract int getRequiredPowerPointsForStageTwo();
}