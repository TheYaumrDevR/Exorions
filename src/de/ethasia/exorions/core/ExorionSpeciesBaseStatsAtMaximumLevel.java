package de.ethasia.exorions.core;

public class ExorionSpeciesBaseStatsAtMaximumLevel {
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    private final int maximumHealthBaseStat;
    public int getMaximumHealthBaseStat() {
        return maximumHealthBaseStat;
    }
    
    private final int attackBaseStat;
    public int getAttackBaseStat() {
        return attackBaseStat;
    }
    
    private final int defenseBaseStat;
    public int getDefenseBaseStat() {
        return defenseBaseStat;
    }
    
    private final int specialAttackBaseStat;
    public int getSpecialAttackBaseStat() {
        return specialAttackBaseStat;
    }

    private final int specialDefenseBaseStat;
    public int getSpecialDefenseBaseStat() {
        return specialDefenseBaseStat;
    }  
    
    private final int evasivenessBaseStat;
    public int getEvasivenessBaseStat() {
        return evasivenessBaseStat;
    }  
    
    private final int accuracyBaseStat;
    public int getAccuracyBaseStat() {
        return accuracyBaseStat;
    } 
    
    private final int criticalHitFrequencyBaseStat;
    public int getCriticalHitFrequencyBaseStat() {
        return criticalHitFrequencyBaseStat;
    }

    private final int criticalHitAvoidanceBaseStat;
    public int getCriticalHitAvoidanceBaseStat() {
        return criticalHitAvoidanceBaseStat;
    }   
    
    private final int swiftnessBaseStat;
    public int getSwiftnessBaseStat() {
        return swiftnessBaseStat;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private ExorionSpeciesBaseStatsAtMaximumLevel(Builder builder) {
        maximumHealthBaseStat = builder.maximumHealthBaseStats;
        attackBaseStat = builder.attackBaseStat;
        defenseBaseStat = builder.defenseBaseStat;
        specialAttackBaseStat = builder.specialAttackBaseStat;
        specialDefenseBaseStat = builder.specialDefenseBaseStat;
        evasivenessBaseStat = builder.evasivenessBaseStat;
        accuracyBaseStat = builder.accuracyBaseStat;
        criticalHitFrequencyBaseStat = builder.criticalHitFrequencyBaseStat;
        criticalHitAvoidanceBaseStat = builder.criticalHitAvoidanceBaseStat;
        swiftnessBaseStat = builder.swiftnessBaseStat;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private int maximumHealthBaseStats;
        private int attackBaseStat;
        private int defenseBaseStat;
        private int specialAttackBaseStat;
        private int specialDefenseBaseStat;
        private int evasivenessBaseStat;
        private int accuracyBaseStat;
        private int criticalHitFrequencyBaseStat;
        private int criticalHitAvoidanceBaseStat;
        private int swiftnessBaseStat;
        
        public Builder setMaximumHealthBaseStat(int value) {
            maximumHealthBaseStats = value;
            return this;
        }
        
        public Builder setAttackBaseStat(int value) {
            attackBaseStat = value;
            return this;
        }
        
        public Builder setDefenseBaseStat(int value) {
            defenseBaseStat = value;
            return this;
        }
        
        public Builder setSpecialAttackBaseStat(int value) {
            specialAttackBaseStat = value;
            return this;
        }
        
        public Builder setSpecialDefenseBaseStat(int value) {
            specialDefenseBaseStat = value;
            return this;
        }
        
        public Builder setAccuracyBaseStat(int value) {
            accuracyBaseStat = value;
            return this;
        }
        
        public Builder setEvasivenessBaseStat(int value) {
            evasivenessBaseStat = value;
            return this;
        }
        
        public Builder setCriticalHitFrequencyBaseStat(int value) {
            criticalHitFrequencyBaseStat = value;
            return this;
        }
        
        public Builder setCriticalHitAvoidanceBaseStat(int value) {
            criticalHitAvoidanceBaseStat = value;
            return this;
        }
        
        public Builder setSwiftnessBaseStat(int value) {
            swiftnessBaseStat = value;
            return this;
        }
     
        public ExorionSpeciesBaseStatsAtMaximumLevel build() {
            return new ExorionSpeciesBaseStatsAtMaximumLevel(this);
        }
    }
    
    //</editor-fold>
}