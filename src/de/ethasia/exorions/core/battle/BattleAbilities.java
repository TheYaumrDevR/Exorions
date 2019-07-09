package de.ethasia.exorions.core.battle;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.general.PersistedEntityNotFoundException;

public class BattleAbilities {
    
    public static BattleAbility findAbilityById(int id) {
        if (id < 0) {
            throw new PersistedEntityNotFoundException();
        }
        
        switch(id) {
            case 0:
                return createRam();
            case 1:
                return createBite();
            case 2:
                return createClawSwipe();
        }
        
        return null;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static BattleAbility createRam() {
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
    
    private static BattleAbility createBite() {
        BattleAbilityBase bite = new BattleAbilityBase.Builder()
            .setName("Bite")
            .setDamageType(DamageTypes.SQUEEZE)
            .setDamageType(DamageTypes.RIP)
            .setDamageType(DamageTypes.INFECTION)
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setDelayMultiplier(1.1f)
            .setRequiredPowerPointsForStageTwo(2)
            .build();
        
        Poison poison = new Poison();
        ApplyBattleModifierAbilityEffect applyPoison = new ApplyBattleModifierAbilityEffect(poison);
        applyPoison.decorate(bite);
        
        AbilityEffectApplicationRandomizer poisonRandomizer = new AbilityEffectApplicationRandomizer();
        poisonRandomizer.setApplyChanceInPerTenThousand(2000);    
        poisonRandomizer.decorate(applyPoison);        
        
        DirectDamageAbilityEffect ramDamageEffect = new DirectDamageAbilityEffect();        
        ramDamageEffect.decorate(poisonRandomizer);        
        
        return ramDamageEffect;
    }
    
    private static BattleAbility createClawSwipe() {
        BattleAbilityBase clawSwipeBase = new BattleAbilityBase.Builder()
            .setName("Claw Swipe")
            .setDamageType(DamageTypes.CUT)
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setDelayMultiplier(1.0f)
            .setRequiredPowerPointsForStageTwo(2)
            .build();
        
        Bleed bleed = new Bleed();
        ApplyBattleModifierAbilityEffect applyBleed = new ApplyBattleModifierAbilityEffect(bleed);
        applyBleed.decorate(clawSwipeBase);
        
        AbilityEffectApplicationRandomizer bleedRandomizer = new AbilityEffectApplicationRandomizer();
        bleedRandomizer.setApplyChanceInPerTenThousand(1500);
        bleedRandomizer.decorate(applyBleed);
        
        DirectDamageAbilityEffect clawSwipeDamage = new DirectDamageAbilityEffect();
        clawSwipeDamage.decorate(bleedRandomizer);
        
        return clawSwipeDamage;
    }
    
    //</editor-fold>
}