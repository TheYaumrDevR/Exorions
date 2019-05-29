package de.ethasia.exorions.core;

import de.ethasia.exorions.core.battle.BattleAbility;
import de.ethasia.exorions.core.battle.BattleModifiedIndividualExorion;
import de.ethasia.exorions.core.battle.IndividualExorionBattleModifier;
import de.ethasia.exorions.core.battle.RequirementsToLearnAbilityAreNotFulfilledException;
import de.ethasia.exorions.core.breeding.Genome;

public class IndividualExorion extends BattleModifiedIndividualExorion {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private IndividualExorionBaseStats baseStats;
    private final ExorionSpecies species;
    private final Genome genome;
    private int level;
    
    private BattleAbility abilityOnSlotOne;
    private BattleAbility abilityOnSlotTwo;
    private BattleAbility abilityOnSlotThree;
    
    private final BattleCalculator battleCalculator;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private IndividualExorion(Builder builder) {
        baseStats = builder.baseStats;
        species = builder.species;
        genome = builder.genome;
        level = builder.level;
        
        battleCalculator = new BattleCalculator();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public IndividualExorionBaseStats getBaseStats() {
        return baseStats;
    }
    
    public ExorionSpecies getSpecies() {
        return species;
    }
    
    public int getLevel() {
        return level;
    }  
    
    public Genome getGenome() {
        return genome;
    }
    
    public BattleAbility getAbilityOnSlotOne() {
        return abilityOnSlotOne;
    }
    
    public BattleAbility getAbilityOnSlotTwo() {
        return abilityOnSlotTwo;
    }
    
    public BattleAbility getAbilityOnSlotThree() {
        return abilityOnSlotThree;
    }
    
    @Override
    public int getModifiedAccuracy() {
        return baseStats.getAccuracy();
    }  
    
    @Override
    public int getModifiedSpecialDefense() {
        return baseStats.getSpecialDefenseValue();
    }     
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean canLearnAbility(BattleAbility ability) {
        return species.getFulfilledLearningRequirements().containsAll(ability.getLearningRequirements());
    }
    
    public void levelUpBy(int value) {
        if (level + value > ExorionSpecies.MAXIMUM_LEVEL) {
            throw new OutOfLevelBoundsException();
        }
        
        level += value;
        adjustBaseStats();
    }
    
    public boolean isMaximumLevel() {
        return level == ExorionSpecies.MAXIMUM_LEVEL;
    }
    
    @Override
    public void takeDamage(int amount) {
        int newHealthPoints = baseStats.getCurrentHealthPoints() - amount;
        baseStats.setCurrentHealthPoints(newHealthPoints);
    }
    
    @Override
    public void tick(IndividualExorionBattleModifier root) {}
    
    @Override
    protected boolean reapplyModifierOfType(Class type) {
        return false;
    }
    
    public boolean isFainted() {
        return baseStats.getCurrentHealthPoints() == 0;
    }
    
    public void learnAbilityOnSlotOne(BattleAbility ability) {
        if (!canLearnAbility(ability)) {
            throw new RequirementsToLearnAbilityAreNotFulfilledException();
        }
        
        abilityOnSlotOne = ability;
    }
    
    public void learnAbilityOnSlotTwo(BattleAbility ability) {
        if (!canLearnAbility(ability)) {
            throw new RequirementsToLearnAbilityAreNotFulfilledException();
        }        
        
        abilityOnSlotTwo = ability;
    }
    
    public void learnAbilityOnSlotThree(BattleAbility ability) {
        if (!canLearnAbility(ability)) {
            throw new RequirementsToLearnAbilityAreNotFulfilledException();
        }         
        
        abilityOnSlotThree = ability;
    }
    
    public void useSlotOneAbility(IndividualExorion target) {
        if (null != abilityOnSlotOne) {
            abilityOnSlotOne.use(this, target);
        }        
    }
    
    public void useSlotTwoAbility(IndividualExorion target) {
        if (null != abilityOnSlotTwo) {
            abilityOnSlotTwo.use(this, target);
        }
    }
    
    public void useSlotThreeAbility(IndividualExorion target) {
        if (null != abilityOnSlotThree) {
            abilityOnSlotThree.use(this, target);
        }
    }
    
    public boolean hasAtLeastOneAbility() {
        return null != abilityOnSlotOne 
            || null != abilityOnSlotTwo 
            || null != abilityOnSlotThree;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void adjustBaseStats() {
        int maximumHealthSpeciesBaseValue = species.getSpeciesBaseStats().getMaximumHealthBaseStat();
        int maximumHealthAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, maximumHealthSpeciesBaseValue);
        
        int attackSpeciesBaseValue = species.getSpeciesBaseStats().getAttackBaseStat();
        int attackValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, attackSpeciesBaseValue);        
        
        int defenseSpeciesBaseValue = species.getSpeciesBaseStats().getDefenseBaseStat();
        int defenseValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, defenseSpeciesBaseValue);
        
        int specialAttackSpeciesBaseValue = species.getSpeciesBaseStats().getSpecialAttackBaseStat();
        int specialAttackValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, specialAttackSpeciesBaseValue);         
        
        int specialDefenseSpeciesBaseValue = species.getSpeciesBaseStats().getSpecialDefenseBaseStat();
        int specialDefenseValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, specialDefenseSpeciesBaseValue);        
         
        int accuracySpeciesBaseValue = species.getSpeciesBaseStats().getAccuracyBaseStat();
        int accuracyValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, accuracySpeciesBaseValue);          
        
        int evasivenessSpeciesBaseValue = species.getSpeciesBaseStats().getEvasivenessBaseStat();
        int evasivenessValueAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, evasivenessSpeciesBaseValue);         
        
        int criticalHitFrequencySpeciesBaseValue = species.getSpeciesBaseStats().getCriticalHitFrequencyBaseStat();
        int criticalHitFrequencyAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, criticalHitFrequencySpeciesBaseValue);         
        
        int criticalHitAvoidanceSpeciesBaseValue = species.getSpeciesBaseStats().getCriticalHitAvoidanceBaseStat();
        int criticalHitAvoidanceAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, criticalHitAvoidanceSpeciesBaseValue);         
        
        int swiftnessSpeciesBaseValue = species.getSpeciesBaseStats().getSwiftnessBaseStat();
        int swiftnessAtCurrentLevel = calculateStatValueForCurrentLevelAndGV(level, swiftnessSpeciesBaseValue);          
        
        this.baseStats = new IndividualExorionBaseStats.Builder()
            .setMaximumHealthPoints(maximumHealthAtCurrentLevel)
            .setAttackValue(attackValueAtCurrentLevel)
            .setDefenseValue(defenseValueAtCurrentLevel)
            .setSpecialAttackValue(specialAttackValueAtCurrentLevel)
            .setSpecialDefenseValue(specialDefenseValueAtCurrentLevel)
            .setAccuracy(accuracyValueAtCurrentLevel)
            .setEvasiveness(evasivenessValueAtCurrentLevel)
            .setCriticalHitFrequency(criticalHitFrequencyAtCurrentLevel)
            .setCriticalHitAvoidance(criticalHitAvoidanceAtCurrentLevel)
            .setSwiftness(swiftnessAtCurrentLevel)
            .build();
    }
    
    private int calculateStatValueForCurrentLevelAndGV(int level, int baseValueAtMaxLevel) {
        int statAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, baseValueAtMaxLevel);
        double geneValueStatIncreaseFactor = battleCalculator.calculateGeneValueStatIncreaseFactor(genome.getMaximumHealthTotalGeneValue());
        
        return battleCalculator.calculateGenomeAdjustedStat(statAtCurrentLevel, geneValueStatIncreaseFactor);        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private IndividualExorionBaseStats baseStats;
        private ExorionSpecies species;
        private Genome genome;
        private int level;
        
        public Builder() {
            level = 1;
        }
        
        public Builder setSpecies(ExorionSpecies value) {
            species = value;
            return this;
        }
        
        public Builder setBaseStats(IndividualExorionBaseStats value) {
            baseStats = value;
            return this;
        }
        
        public Builder setLevel(int value) {
            level = value;
            return this;
        }
        
        public Builder setGenome(Genome value) {
            genome = value;
            return this;
        }
        
        public IndividualExorion build() throws NotAllPropertiesAreSetException {
            if (null == species) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new IndividualExorion(this);
        }
    }
    
    //</editor-fold>
}