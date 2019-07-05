package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.general.PersistedEntityNotFoundException;

public class BattleAbilities {
    
    public static BattleAbility findAbilityById(int id) {
        if (id < 0) {
            throw new PersistedEntityNotFoundException();
        }
        
        BattleAbilityBase ram = new BattleAbilityBase.Builder()
            .setName("Ram")
            .setDamageType(DamageTypes.BLUNT)
            .setDamageType(DamageTypes.SHATTER)
            .setLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .setLearningRequirements(AbilityLearningRequirements.HORNS)
            .setDelayMultiplier(1.2f)
            .setRequiredPowerPointsForStageTwo(2)
            .build();
        
        Stagger stagger = new Stagger();
        ApplyBattleModifierAbilityEffect applyStagger = new ApplyBattleModifierAbilityEffect(stagger);
        applyStagger.decorate(ram);
        
        AbilityEffectApplicationRandomizer staggerRandomizer = new AbilityEffectApplicationRandomizer();
        staggerRandomizer.setApplyChanceInPerTenThousand(2000);    
        staggerRandomizer.decorate(applyStagger);        
        
        DirectDamageAbilityEffect ramDamageEffect = new DirectDamageAbilityEffect();        
        ramDamageEffect.decorate(staggerRandomizer);
        
        return ramDamageEffect;
    }
}