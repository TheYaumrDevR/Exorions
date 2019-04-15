package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.IndividualExorion;
import java.util.Set;

public abstract class BattleAbility {
    
    public abstract String getName();
    public abstract Set<DamageTypes> getDamageTypes();
    public abstract Set<AbilityLearningRequirements> getLearningRequirements();
    public abstract float getDelayMultiplier();
    public abstract int getRequiredPowerPointsForStageTwo();
    
    public abstract void use(IndividualExorion attacker, IndividualExorion defender);
}