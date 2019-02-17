package de.ethasia.exorions.core.breeding;

import de.ethasia.exorions.core.interfaces.AllelePairGeneValueCalculator;

public class AllelePairGeneValueCalculatorImpl implements AllelePairGeneValueCalculator {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    @Override
    public int calculateCombinedGeneValue(Allele firstStatAllele, Allele secondStatAllele) {
        if (firstStatAllele.isDominant() && !secondStatAllele.isDominant()) {
            return firstStatAllele.getStatModifier();
        } else if (!firstStatAllele.isDominant() && secondStatAllele.isDominant()) {
            return secondStatAllele.getStatModifier();
        }
        
        return calculateCombinedGeneValuesWhenBothAllelesHaveSameDominance(firstStatAllele, secondStatAllele);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private int calculateCombinedGeneValuesWhenBothAllelesHaveSameDominance(Allele firstStatAllele, Allele secondStatAllele) {
        if (0 == (firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) % 2) {
            return (firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) / 2;
        }
        
        return Math.round(((float)firstStatAllele.getStatModifier() + secondStatAllele.getStatModifier()) / 2);        
    }
    
    //</editor-fold>
}