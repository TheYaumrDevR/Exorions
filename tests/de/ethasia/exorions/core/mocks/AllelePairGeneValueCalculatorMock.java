package de.ethasia.exorions.core.mocks;

import de.ethasia.exorions.core.breeding.Allele;
import de.ethasia.exorions.core.interfaces.AllelePairGeneValueCalculator;

public class AllelePairGeneValueCalculatorMock extends MockWithMethodCallCounting implements AllelePairGeneValueCalculator {

    @Override
    public int calculateCombinedGeneValue(Allele firstStatAllele, Allele secondStatAllele) {
        this.incrementCallCountForMethodName("calculateCombinedGeneValue");
        return 0;
    }
}