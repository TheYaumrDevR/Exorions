package de.ethasia.exorions.core;

import de.ethasia.exorions.core.breeding.Genome;

public class IndividualExorion {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private IndividualExorionBaseStats baseStats;
    private final ExorionSpecies species;
    private final Genome genome;
    private int level;
    
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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean canLearnAbility(BattleAbility ability) {
        return species.getFulfilledLearningRequirements().containsAll(ability.getLearningRequirements());
    }
    
    public void levelUpBy(int value) {
        if (level + value > ExorionSpecies.MAXIMUM_LEVEL) {
            throw new RuntimeException("Cannot level an Exorion over the maximum level.");
        }
        
        level += value;
        adjustBaseStats();
    }
    
    public boolean isMaximumLevel() {
        return level == ExorionSpecies.MAXIMUM_LEVEL;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void adjustBaseStats() {
        int maximumHealthSpeciesBaseValue = species.getSpeciesBaseStats().getMaximumHealthBaseStat();
        int maximumHealthAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, maximumHealthSpeciesBaseValue);
        
        int attackSpeciesBaseValue = species.getSpeciesBaseStats().getAttackBaseStat();
        int attackValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, attackSpeciesBaseValue);        
        
        int defenseSpeciesBaseValue = species.getSpeciesBaseStats().getDefenseBaseStat();
        int defenseValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, defenseSpeciesBaseValue);
        
        int specialAttackSpeciesBaseValue = species.getSpeciesBaseStats().getSpecialAttackBaseStat();
        int specialAttackValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, specialAttackSpeciesBaseValue);         
        
        int specialDefenseSpeciesBaseValue = species.getSpeciesBaseStats().getSpecialDefenseBaseStat();
        int specialDefenseValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, specialDefenseSpeciesBaseValue);         
        
        int accuracySpeciesBaseValue = species.getSpeciesBaseStats().getAccuracyBaseStat();
        int accuracyValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, accuracySpeciesBaseValue);          
        
        int evasivenessSpeciesBaseValue = species.getSpeciesBaseStats().getEvasivenessBaseStat();
        int evasivenessValueAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, evasivenessSpeciesBaseValue);         
        
        int criticalHitFrequencySpeciesBaseValue = species.getSpeciesBaseStats().getCriticalHitFrequencyBaseStat();
        int criticalHitFrequencyAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, criticalHitFrequencySpeciesBaseValue);         
        
        int criticalHitAvoidanceSpeciesBaseValue = species.getSpeciesBaseStats().getCriticalHitAvoidanceBaseStat();
        int criticalHitAvoidanceAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, criticalHitAvoidanceSpeciesBaseValue);         
        
        int swiftnessSpeciesBaseValue = species.getSpeciesBaseStats().getSwiftnessBaseStat();
        int swiftnessAtCurrentLevel = battleCalculator.calculateBaseStatForLevelFromStatAtMaximumLevel(level, swiftnessSpeciesBaseValue);          
        
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