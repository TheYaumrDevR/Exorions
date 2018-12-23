package de.ethasia.exorions.core.breeding;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;

public class ChromosomePair {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final Chromosome paternalChromosome;
    public Chromosome getPaternalChromosome() {
        return paternalChromosome;
    }
    
    private final Chromosome maternalChromosome;
    public Chromosome getMaternalChromosome() {
        return maternalChromosome;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private ChromosomePair(Builder builder) {
        paternalChromosome = builder.paternalChromosome;
        maternalChromosome = builder.maternalChromosome;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public ChromosomePair cloneWithCrossover() {
        Chromosome clonedMaternalChromosome = maternalChromosome.createClone();
        Chromosome clonedPaternalChromosome = paternalChromosome.createClone();
        ChromosomePair clonedPair = null;
        
        try {
            clonedPair = new ChromosomePair.Builder()
                .setMaternalChromosome(clonedMaternalChromosome)
                .setPaternalChromosome(clonedPaternalChromosome)
                .build();
        } catch (NotAllPropertiesAreSetException ex) {
        }
        
        return clonedPair;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    
    public static class Builder {
        
        private Chromosome paternalChromosome;
        private Chromosome maternalChromosome;
        
        public Builder setPaternalChromosome(Chromosome value) {
            paternalChromosome = value;
            return this;
        }
        
        public Builder setMaternalChromosome(Chromosome value) {
            maternalChromosome = value;
            return this;
        }
        
        public ChromosomePair build() throws NotAllPropertiesAreSetException {
            if (null == paternalChromosome || null == maternalChromosome) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new ChromosomePair(this);
        }
    }
    
    //</editor-fold>
}