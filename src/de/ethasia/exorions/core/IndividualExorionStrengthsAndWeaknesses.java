package de.ethasia.exorions.core;

import java.util.HashSet;
import java.util.Set;

public class IndividualExorionStrengthsAndWeaknesses {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final Set<DamageTypes> resistantAgainst;
    private final Set<DamageTypes> doubleStrongAgainst;
    private final Set<DamageTypes> strongAgainst;
    private final Set<DamageTypes> weakAgainst;
    private final Set<DamageTypes> doubleWeakAgainst;
    private final Set<DamageTypes> destroyedBy;
    
    //</editor-fold>
      
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    
    public Set<DamageTypes> getResistantAgainst() {
        return new HashSet<>(resistantAgainst);
    }
    
    public Set<DamageTypes> getDoubleStrongAgainst() {
        return new HashSet<>(doubleStrongAgainst);
    }
    
    public Set<DamageTypes> getStrongAgainst() {
        return new HashSet<>(strongAgainst);
    }
    
    public Set<DamageTypes> getWeakAgainst() {
        return new HashSet<>(weakAgainst);
    }
    
    public Set<DamageTypes> getDoubleWeakAgainst() {
        return new HashSet<>(doubleWeakAgainst);
    }
    
    public Set<DamageTypes> getDestroyedBy() {
        return new HashSet<>(destroyedBy);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private IndividualExorionStrengthsAndWeaknesses(Builder builder) {
        resistantAgainst = builder.resistantAgainst;
        doubleStrongAgainst = builder.doubleStrongAgainst;
        strongAgainst = builder.strongAgainst;
        weakAgainst = builder.weakAgainst;
        doubleWeakAgainst = builder.doubleWeakAgainst;
        destroyedBy = builder.destroyedBy;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        //<editor-fold defaultstate="collapsed" desc="Fields">
        
        private final Set<DamageTypes> resistantAgainst;
        private final Set<DamageTypes> doubleStrongAgainst;
        private final Set<DamageTypes> strongAgainst;
        private final Set<DamageTypes> weakAgainst;
        private final Set<DamageTypes> doubleWeakAgainst;
        private final Set<DamageTypes> destroyedBy;
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Constructor">
        
        public Builder() {
            resistantAgainst = new HashSet<>();
            doubleStrongAgainst = new HashSet<>();
            strongAgainst = new HashSet<>();
            weakAgainst = new HashSet<>();
            doubleWeakAgainst = new HashSet<>();
            destroyedBy = new HashSet<>();
        }
        
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Methods">
        
        public Builder setResistantAgainst(DamageTypes damageType) {
            resistantAgainst.add(damageType);
            return this;
        }
        
        public Builder setDoubleStrongAgainst(DamageTypes damageType) {
            doubleStrongAgainst.add(damageType);
            return this;
        }
        
        public Builder setStrongAgainst(DamageTypes damageType) {
            strongAgainst.add(damageType);
            return this;
        }
        
        public Builder setWeakAgainst(DamageTypes damageType) {
            weakAgainst.add(damageType);
            return this;
        }
        
        public Builder setDoubleWeakAgainst(DamageTypes damageType) {
            doubleWeakAgainst.add(damageType);
            return this;
        }
        
        public Builder setDestroyedBy(DamageTypes damageType) {
            destroyedBy.add(damageType);
            return this;
        }
        
        public IndividualExorionStrengthsAndWeaknesses build() {
            return new IndividualExorionStrengthsAndWeaknesses(this);
        }
        
        //</editor-fold>
    }
    
    //</editor-fold>
}