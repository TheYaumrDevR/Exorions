package de.ethasia.exorions.core;

public class IndividualExorionBaseStats {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private int currentHealthPoints;
    private final int maximumHealthPoints;
    private final int attackValue;
    private final int specialAttackValue;
    private final int defenseValue;
    private final int specialDefenseValue;
    private final int swiftness;
    private final int evasiveness;
    private final int accuracy;
    private final int criticalHitAvoidance;
    private final int criticalHitFrequency;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private IndividualExorionBaseStats(Builder builder) {
        attackValue = builder.attackValue;
        specialAttackValue = builder.specialAttackValue;
        defenseValue = builder.defenseValue;
        specialDefenseValue = builder.specialDefenseValue;
        swiftness = builder.swiftness;
        evasiveness = builder.evasiveness;
        maximumHealthPoints = builder.maximumHealthPoints;
        accuracy = builder.accuracy;
        criticalHitAvoidance = builder.criticalHitAvoidance;
        criticalHitFrequency = builder.criticalHitFrequency;
        currentHealthPoints = maximumHealthPoints;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }
    public void setCurrentHealthPoints(int value) {
        if (value < 0) {
            value = 0;
        }
        
        currentHealthPoints = value;
    }
    
    public int getMaximumHealthPoints() {
        return maximumHealthPoints;
    }
    
    public int getAttackValue() {
        return attackValue;
    }
    
    public int getSpecialAttackValue() {
        return specialAttackValue;
    }
    
    public int getDefenseValue() {
        return defenseValue;
    }
    
    public int getSpecialDefenseValue() {
        return specialDefenseValue;
    }
    
    public int getSwiftness() {
        return swiftness;
    }
    
    public int getEvasiveness() {
        return evasiveness;
    }
    
    public int getAccuracy() {
        return accuracy;
    }
    
    public int getCriticalHitAvoidance() {
        return criticalHitAvoidance;
    }
    
    public int getCriticalHitFrequency() {
        return criticalHitFrequency;
    }
    
    //</editor-fold>

    public static class Builder {
        
        //<editor-fold defaultstate="collapsed" desc="Fields">
        
        private int maximumHealthPoints;
        private int attackValue;
        private int specialAttackValue;
        private int defenseValue;
        private int specialDefenseValue;
        private int swiftness;
        private int evasiveness;
        private int accuracy;
        private int criticalHitAvoidance;
        private int criticalHitFrequency;
        
        //</editor-fold>

        public Builder() {}
        
        //<editor-fold defaultstate="collapsed" desc="Builder Methods
        
        public Builder setMaximumHealthPoints(int value) {
            maximumHealthPoints = value;
            return this;
        }
            
        public Builder setAttackValue(int value) {
            attackValue = value;
            return this;
        }
        
        public Builder setSpecialAttackValue(int value) {
            specialAttackValue = value;
            return this;
        }
        
        public Builder setDefenseValue(int value) {
            defenseValue = value;
            return this;
        }
        
        public Builder setSpecialDefenseValue(int value) {
            specialDefenseValue = value;
            return this;
        }
        
        public Builder setSwiftness(int value) {
            swiftness = value;
            return this;
        }
        
        public Builder setEvasiveness(int value) {
            evasiveness = value;
            return this;
        }
        
        public Builder setAccuracy(int value) {
            accuracy = value;
            return this;
        }
        
        public Builder setCriticalHitAvoidance(int value) {
            criticalHitAvoidance = value;
            return this;
        }
        
        public Builder setCriticalHitFrequency(int value) {
            criticalHitFrequency = value;
            return this;
        }
        
        public IndividualExorionBaseStats build() {
            return new IndividualExorionBaseStats(this);
        }
            
        //</editor-fold>        
    }
    
}