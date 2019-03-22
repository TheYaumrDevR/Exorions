package de.ethasia.exorions.core;

import de.ethasia.exorions.core.breeding.Genome;

public class IndividualExorion {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IndividualExorionBaseStats baseStats;
    private final ExorionSpecies species;
    private final Genome genome;
    private int level;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private IndividualExorion(Builder builder) {
        baseStats = builder.baseStats;
        species = builder.species;
        genome = builder.genome;
        level = builder.level;
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
    }
    
    public boolean isMaximumLevel() {
        return level == ExorionSpecies.MAXIMUM_LEVEL;
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