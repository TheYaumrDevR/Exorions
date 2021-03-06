package de.ethasia.exorions.ioadapters.repositories;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.DamageTypes;
import de.ethasia.exorions.core.battle.AbilityEffectApplicationRandomizer;
import de.ethasia.exorions.core.battle.ApplyBattleModifierAbilityEffect;
import de.ethasia.exorions.core.battle.BattleAbility;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.battle.Bleed;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.battle.Poison;
import de.ethasia.exorions.core.battle.Stagger;
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
            case 3:
                return createErosion();    
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
            .setAbilityLevel(1)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        Stagger stagger = new Stagger();
        ApplyBattleModifierAbilityEffect applyStagger = new ApplyBattleModifierAbilityEffect(stagger);
        applyStagger.decorate(ram);
        
        AbilityEffectApplicationRandomizer staggerRandomizer = new AbilityEffectApplicationRandomizer();
        staggerRandomizer.setApplyChanceInPerTenThousand(2000);    
        staggerRandomizer.decorate(applyStagger);        
        
        DirectDamageAbilityEffect ramDamageEffect = new DirectDamageAbilityEffect(); 
        ramDamageEffect.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
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
            .setAbilityLevel(1)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        Poison poison = new Poison();
        ApplyBattleModifierAbilityEffect applyPoison = new ApplyBattleModifierAbilityEffect(poison);
        applyPoison.decorate(bite);
        
        AbilityEffectApplicationRandomizer poisonRandomizer = new AbilityEffectApplicationRandomizer();
        poisonRandomizer.setApplyChanceInPerTenThousand(2000);    
        poisonRandomizer.decorate(applyPoison);        
        
        DirectDamageAbilityEffect biteDamageEffect = new DirectDamageAbilityEffect();   
        biteDamageEffect.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        biteDamageEffect.decorate(poisonRandomizer);        
        
        return biteDamageEffect;
    }
    
    private static BattleAbility createClawSwipe() {
        BattleAbilityBase clawSwipeBase = new BattleAbilityBase.Builder()
            .setName("Claw Swipe")
            .setDamageType(DamageTypes.CUT)
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setDelayMultiplier(1.0f)
            .setRequiredPowerPointsForStageTwo(2)
            .setAbilityLevel(1)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        Bleed bleed = new Bleed();
        ApplyBattleModifierAbilityEffect applyBleed = new ApplyBattleModifierAbilityEffect(bleed);
        applyBleed.decorate(clawSwipeBase);
        
        AbilityEffectApplicationRandomizer bleedRandomizer = new AbilityEffectApplicationRandomizer();
        bleedRandomizer.setApplyChanceInPerTenThousand(1500);
        bleedRandomizer.decorate(applyBleed);
        
        DirectDamageAbilityEffect clawSwipeDamage = new DirectDamageAbilityEffect();
        clawSwipeDamage.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        clawSwipeDamage.decorate(bleedRandomizer);
        
        return clawSwipeDamage;
    }
    
    private static BattleAbility createErosion() {
        // The Exorion burrows and erodes the ground under the enemy.
        // Deals ground based damage and may lower evasiveness of opponent.
        
        BattleAbilityBase erosion = new BattleAbilityBase.Builder()
            .setName("Erosion")
            .setDamageType(DamageTypes.GROUND)
            .setLearningRequirements(AbilityLearningRequirements.BURROWING_LOCOMOTION)
            .setDelayMultiplier(1.1f)
            .setRequiredPowerPointsForStageTwo(2)
            .setAbilityLevel(1)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();  
        
        DirectDamageAbilityEffect erosionDamage = new DirectDamageAbilityEffect();
        erosionDamage.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        erosionDamage.decorate(erosion);        
        
        return erosionDamage;
    }
    
    //</editor-fold>
}