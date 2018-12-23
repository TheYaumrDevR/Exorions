package de.ethasia.exorions.core.breeding;

public class Allele {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final int statModifier;
    private final boolean isDominant;
    
    public int getStatModifier() {
        return statModifier;
    }
    
    public boolean isDominant() {
        return isDominant;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private Allele(int statModifier, boolean isDominant) {
        this.statModifier = statModifier;
        this.isDominant = isDominant;
    }
    
    private Allele(Builder builder) {
        statModifier = builder.statModifier;
        isDominant = builder.isDominant;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public Allele createClone() {
        Allele clone = new Allele(statModifier, isDominant);
        return clone;
    }
    
    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        
        if (!(other instanceof Allele)) {
            return false;
        }
        
        return equals((Allele)other);
    }
    
    @Override
    public int hashCode() {
        short dominantOffset = isDominant ? (short)256 : (short)128;
        
        return statModifier + dominantOffset;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private boolean equals(Allele other) {
        return other.isDominant == this.isDominant && other.statModifier == this.statModifier;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private int statModifier;
        private boolean isDominant;
        
        public Builder setStatModifier(int value) {
            statModifier = value;
            return this;
        }
        
        public Builder setIsDominant(boolean value) {
            isDominant = value;
            return this;
        }
        
        public Allele build() {
            return new Allele(this);
        }
    }
    
    //</editor-fold>
}