package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import java.util.Set;

public abstract class BattleAbility {
    
    public abstract String getName();
    public abstract Set<DamageTypes> getDamageTypes();
    public abstract Set<AbilityLearningRequirements> getLearningRequirements();
    public abstract float getDelayMultiplier();
    public abstract int getRequiredPowerPointsForStageTwo();
    public abstract int getMinimumLevelRequired();
    public abstract int getAbilityLevel();
    
    public abstract BattleModifiedIndividualExorion use(BattleModifiedIndividualExorion attacker, BattleModifiedIndividualExorion defender);
}