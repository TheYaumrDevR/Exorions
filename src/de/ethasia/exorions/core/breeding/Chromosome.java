package de.ethasia.exorions.core.breeding;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;

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
        Chromosome clone = null;
        
        try {
            clone = new Chromosome.Builder()
                .setMaximumHealthAllele(maximumHealthAllele.createClone())
                .setAttackAllele(attackAllele.createClone())
                .setDefenseAllele(defenseAllele.createClone())
                .setSpecialAttackAllele(specialAttackAllele.createClone())
                .setSpecialDefenseAllele(specialDefenseAllele.createClone())
                .setSwiftnessAllele(swiftnessAllele.createClone())
                .setAccuracyAllele(accuracyAllele.createClone())
                .setEvasivenessAllele(evasivenessAllele.createClone())
                .setCriticalHitAvoidanceAllele(criticalHitAvoidanceAllele.createClone())
                .setCriticalHitFrequencyAllele(criticalHitFrequencyAllele.createClone())
                .build(); 
            
            return clone;
        } catch (NotAllPropertiesAreSetException ex) {}
        
        return clone;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        
        if (!(other instanceof Chromosome)) {
            return false;
        }
        
        return equals((Chromosome)other);
    }
    
    @Override
    public int hashCode() {
        return maximumHealthAllele.hashCode()
            + attackAllele.hashCode()
            + defenseAllele.hashCode()
            + specialAttackAllele.hashCode()
            + specialDefenseAllele.hashCode()
            + swiftnessAllele.hashCode()
            + accuracyAllele.hashCode()
            + evasivenessAllele.hashCode()
            + criticalHitAvoidanceAllele.hashCode()
            + criticalHitFrequencyAllele.hashCode();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private boolean equals(Chromosome other) {
        return other.maximumHealthAllele.equals(this.maximumHealthAllele)
            && other.attackAllele.equals(this.attackAllele)
            && other.defenseAllele.equals(this.defenseAllele)
            && other.specialAttackAllele.equals(this.specialAttackAllele)
            && other.specialDefenseAllele.equals(this.specialDefenseAllele)
            && other.swiftnessAllele.equals(this.swiftnessAllele)
            && other.accuracyAllele.equals(this.accuracyAllele)
            && other.evasivenessAllele.equals(this.evasivenessAllele)
            && other.criticalHitAvoidanceAllele.equals(this.criticalHitAvoidanceAllele)
            && other.criticalHitFrequencyAllele.equals(this.criticalHitFrequencyAllele);
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
        
        private boolean randomizeUndefinedAlleles;
        
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
        
        public Builder setRandomizeUndefinedAlleles() {
            randomizeUndefinedAlleles = true;
            return this;
        }
        
        public Chromosome build() throws NotAllPropertiesAreSetException {
            if (randomizeUndefinedAlleles) {
                createRandomizedAllelesForNullAlleles();
            } else {
                throwExceptionIfAnyAlleleIsNull();
            }
            
            return new Chromosome(this);
        }
        
        private void createRandomizedAllelesForNullAlleles() {
            if (null == maximumHealthAllele) {
                maximumHealthAllele = new Allele.Builder().build();
            }
            
            if (null == attackAllele) {
                attackAllele = new Allele.Builder().build();
            } 
            
            if (null == defenseAllele) {
                defenseAllele = new Allele.Builder().build();
            }  
            
            if (null == specialAttackAllele) {
                specialAttackAllele = new Allele.Builder().build();
            }
            
            if (null == specialDefenseAllele) {
                specialDefenseAllele = new Allele.Builder().build();
            }
            
            if (null == swiftnessAllele) {
                swiftnessAllele = new Allele.Builder().build();
            }
            
            if (null == accuracyAllele) {
                accuracyAllele = new Allele.Builder().build();
            }
            
            if (null == evasivenessAllele) {
                evasivenessAllele = new Allele.Builder().build();
            }
            
            if (null == criticalHitAvoidanceAllele) {
                criticalHitAvoidanceAllele = new Allele.Builder().build();
            }
            
            if (null == criticalHitFrequencyAllele) {
                criticalHitFrequencyAllele = new Allele.Builder().build();
            }
        }
        
        private void throwExceptionIfAnyAlleleIsNull() throws NotAllPropertiesAreSetException {
            if (null == maximumHealthAllele
                || null == attackAllele 
                || null == defenseAllele
                || null == specialAttackAllele
                || null == specialDefenseAllele
                || null == swiftnessAllele
                || null == accuracyAllele
                || null == evasivenessAllele
                || null == criticalHitAvoidanceAllele
                || null == criticalHitFrequencyAllele) 
            {
                throw new NotAllPropertiesAreSetException();
            }            
        }
    }
    
    //</editor-fold>
}