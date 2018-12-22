package de.ethasia.exorions.core;

public class IndividualExorion {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final IndividualExorionBaseStats baseStats;
    private final ExorionSpecies species;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private IndividualExorion(Builder builder) {
        baseStats = builder.baseStats;
        species = builder.species;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    public IndividualExorionBaseStats getBaseStats() {
        return baseStats;
    }
    
    public ExorionSpecies getSpecies() {
        return species;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public boolean canLearnAbility(BattleAbility ability) {
        return species.getFulfilledLearningRequirements().containsAll(ability.getLearningRequirements());
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private IndividualExorionBaseStats baseStats;
        private ExorionSpecies species;
        
        public Builder setSpecies(ExorionSpecies value) {
            species = value;
            return this;
        }
        
        public Builder setBaseStats(IndividualExorionBaseStats value) {
            baseStats = value;
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