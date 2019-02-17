package de.ethasia.exorions.core.interfaces;

import de.ethasia.exorions.core.breeding.Allele;

public interface AllelePairGeneValueCalculator {
    
    public int calculateCombinedGeneValue(Allele firstStatAllele, Allele secondStatAllele);
}