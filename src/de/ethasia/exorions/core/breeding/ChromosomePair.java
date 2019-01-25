package de.ethasia.exorions.core.breeding;

import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RandomNumberGenerator;

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
        Chromosome.Builder firstDaughterChromosomeBuilder = new Chromosome.Builder();
        Chromosome.Builder secondDaughterChromosomeBuilder = new Chromosome.Builder();
        
        setupChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
                
        ChromosomePair clonedPair = null;
        
        try {
            clonedPair = new ChromosomePair.Builder()
                .setMaternalChromosome(firstDaughterChromosomeBuilder.build())
                .setPaternalChromosome(secondDaughterChromosomeBuilder.build())
                .build();
        } catch (NotAllPropertiesAreSetException ex) {
        }
        
        return clonedPair;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void setupChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        setMaximumHealthAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setAccuracyAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setAttackAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setCriticalHitAvoidanceAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setCriticalHitFrequencyAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setDefenseAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setEvasivenessAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setSpecialAttackAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setSpecialDefenseAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);
        setSwiftnessAlleleInCrossoverChildren(firstDaughterChromosomeBuilder, secondDaughterChromosomeBuilder);        
    }
    
    private void setMaximumHealthAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getMaximumHealthAllele();
        Allele paternalAllele = paternalChromosome.getMaximumHealthAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setMaximumHealthAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setMaximumHealthAllele(allelesAfterSwapping[1]);
    }
    
    private void setAccuracyAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getAccuracyAllele();
        Allele paternalAllele = paternalChromosome.getAccuracyAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setAccuracyAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setAccuracyAllele(allelesAfterSwapping[1]);
    }
    
    private void setAttackAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getAttackAllele();
        Allele paternalAllele = paternalChromosome.getAttackAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setAttackAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setAttackAllele(allelesAfterSwapping[1]);
    }    
    
    private void setCriticalHitAvoidanceAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getCriticalHitAvoidanceAllele();
        Allele paternalAllele = paternalChromosome.getCriticalHitAvoidanceAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setCriticalHitAvoidanceAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setCriticalHitAvoidanceAllele(allelesAfterSwapping[1]);
    } 
    
    private void setCriticalHitFrequencyAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getCriticalHitFrequencyAllele();
        Allele paternalAllele = paternalChromosome.getCriticalHitFrequencyAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setCriticalHitFrequencyAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setCriticalHitFrequencyAllele(allelesAfterSwapping[1]);
    }
    
    private void setDefenseAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getDefenseAllele();
        Allele paternalAllele = paternalChromosome.getDefenseAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setDefenseAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setDefenseAllele(allelesAfterSwapping[1]);
    }

    private void setEvasivenessAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getEvasivenessAllele();
        Allele paternalAllele = paternalChromosome.getEvasivenessAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setEvasivenessAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setEvasivenessAllele(allelesAfterSwapping[1]);
    }

    private void setSpecialAttackAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getSpecialAttackAllele();
        Allele paternalAllele = paternalChromosome.getSpecialAttackAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setSpecialAttackAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setSpecialAttackAllele(allelesAfterSwapping[1]);
    }

    private void setSpecialDefenseAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getSpecialDefenseAllele();
        Allele paternalAllele = paternalChromosome.getSpecialDefenseAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setSpecialDefenseAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setSpecialDefenseAllele(allelesAfterSwapping[1]);
    }   
    
    private void setSwiftnessAlleleInCrossoverChildren(Chromosome.Builder firstDaughterChromosomeBuilder, Chromosome.Builder secondDaughterChromosomeBuilder) {
        Allele maternalAllele = maternalChromosome.getSwiftnessAllele();
        Allele paternalAllele = paternalChromosome.getSwiftnessAllele();
        
        Allele[] allelesAfterSwapping = swapAllelesIfNecessary(maternalAllele, paternalAllele);
        firstDaughterChromosomeBuilder.setSwiftnessAllele(allelesAfterSwapping[0]);
        secondDaughterChromosomeBuilder.setSwiftnessAllele(allelesAfterSwapping[1]);
    }     
    
    private Allele[] swapAllelesIfNecessary(Allele maternalAllele, Allele paternalAllele) {
        RandomNumberGenerator rng = CoreClassesFactory.getInstance().getRandomNumberGeneratorSingletonInstance();
        boolean swapAlleles = rng.createRandomBoolean();   
        
        if (swapAlleles) {
            return new Allele[] {paternalAllele, maternalAllele};
        }
        
        return new Allele[] {maternalAllele, paternalAllele};
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