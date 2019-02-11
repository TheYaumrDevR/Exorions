package de.ethasia.exorions.core.breeding;

public class AllelePairGeneValueCalculator {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public int calculateCombinedGeneValue(Allele firstStatAllele, Allele secondStatAllele) {
        if (firstStatAllele.isDominant() && !secondStatAllele.isDominant()) {
            return firstStatAllele.getStatModifier();
        } else if (!firstStatAllele.isDominant() && secondStatAllele.isDominant()) {
            return secondStatAllele.getStatModifier();
        }
        
        if (0 == (firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) % 2) {
            return (firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) / 2;
        }
        
        return Math.round(((float)firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) / 2);
    }
    
    //</editor-fold>
}