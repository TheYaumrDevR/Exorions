package de.ethasia.exorions.core.breeding;

public class Chromosome {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final Allele maximumHealthAllele;
    public Allele getMaximumHealthAllele() {
        return maximumHealthAllele;
    }
    
    private final Allele attackAllele;
    public Allele getAttackAllele() {
        return attackAllele;
    }
    
    private final Allele defenseAllele;
    public Allele getDefenseAllele() {
        return defenseAllele;
    }
    
    private final Allele specialAttackAllele;
    public Allele getSpecialAttackAllele() {
        return specialAttackAllele;
    }
    
    private final Allele specialDefenseAllele;
    public Allele getSpecialDefenseAllele() {
        return specialDefenseAllele;
    }
    
    private final Allele swiftnessAllele;
    public Allele getSwiftnessAllele() {
        return swiftnessAllele;
    }
    
    private final Allele accuracyAllele;
    public Allele getAccuracyAllele() {
        return accuracyAllele;
    }
    
    private final Allele evasivenessAllele;
    public Allele getEvasivenessAllele() {
        return evasivenessAllele;
    }
    
    private final Allele criticalHitAvoidanceAllele;
    public Allele getCriticalHitAvoidanceAllele() {
        return criticalHitAvoidanceAllele;
    }
    
    private final Allele criticalHitFrequencyAllele;
    public Allele getCriticalHitFrequencyAllele() {
        return criticalHitFrequencyAllele;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private Chromosome(Builder builder) {
        maximumHealthAllele = builder.maximumHealthAllele;
        attackAllele = builder.attackAllele;
        defenseAllele = builder.defenseAllele;
        specialAttackAllele = builder.specialAttackAllele;
        specialDefenseAllele = builder.specialDefenseAllele;
        swiftnessAllele = builder.swiftnessAllele;
        accuracyAllele = builder.accuracyAllele;
        evasivenessAllele = builder.evasivenessAllele;
        criticalHitAvoidanceAllele = builder.criticalHitAvoidanceAllele;
        criticalHitFrequencyAllele = builder.criticalHitFrequencyAllele;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public Chromosome createClone() {
        Chromosome clone = new Chromosome.Builder()
            .build();
        
        return clone;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private Allele maximumHealthAllele;
        private Allele attackAllele;
        private Allele defenseAllele;
        private Allele specialAttackAllele;
        private Allele specialDefenseAllele;
        private Allele swiftnessAllele;
        private Allele accuracyAllele;
        private Allele evasivenessAllele;
        private Allele criticalHitAvoidanceAllele;
        private Allele criticalHitFrequencyAllele;
        
        public Builder setMaximumHealthAllele(Allele value) {
            maximumHealthAllele = value;
            return this;
        }
        
        public Builder setAttackAllele(Allele value) {
            attackAllele = value;
            return this;
        }
        
        public Builder setDefenseAllele(Allele value) {
            defenseAllele = value;
            return this;
        }
        
        public Builder setSpecialAttackAllele(Allele value) {
            specialAttackAllele = value;
            return this;
        }
        
        public Builder setSpecialDefenseAllele(Allele value) {
            specialDefenseAllele = value;
            return this;
        }
        
        public Builder setSwiftnessAllele(Allele value) {
            swiftnessAllele = value;
            return this;
        }
        
        public Builder setAccuracyAllele(Allele value) {
            accuracyAllele = value;
            return this;
        }
        
        public Builder setEvasivenessAllele(Allele value) {
            evasivenessAllele = value;
            return this;
        }
        
        public Builder setCriticalHitAvoidanceAllele(Allele value) {
            criticalHitAvoidanceAllele = value;
            return this;
        }
        
        public Builder setCriticalHitFrequencyAllele(Allele value) {
            criticalHitFrequencyAllele = value;
            return this;
        }
        
        public Chromosome build() {
            return new Chromosome(this);
        }
    }
    
    //</editor-fold>
}