package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.BattleAbility;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.breeding.Genome;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndividualExorionTest {
    
    @Test
    public void testBuilderBuild_speciesIsSet_speciesIsContainedInProduct() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        IndividualExorion product = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        ExorionSpecies speciesInProduct = product.getSpecies();
        
        assertThat(speciesInProduct, is(equalTo(species)));
    }

    @Test
    public void testCanLearnAbility_abilityWithoutRequirements_canLearn() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility.Builder battleAbilityBuilder = new BattleAbility.Builder();
        BattleAbility abilityToLearn = battleAbilityBuilder.build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testCanLearnAbility_abilityWithOneRequirement_cannotLearnBecauseExorionHasNoTail() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    }  
    
    @Test
    public void testCanLearnAbility_abilityWithOneRequirement_canLearnBecauseExorionHasRequirements() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    }   
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_cannotLearnBecauseOneIsNotSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();        
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_canLearnBothAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.CLAWS)
            .build();          
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_canLearnWhenMoreAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TEETH)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TENTACLES)    
            .build();  
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_cannotLearnAsDifferentAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.HARD_BODY)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)   
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();        
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbility abilityToLearn = new BattleAbility.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    } 
    
    @Test
    public void testBuilderBuild_baseStatsAreSet_createdExorionContainsStats() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorionBaseStats baseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(baseStats)
            .build();
        
        assertThat(testCandidate.getBaseStats(), is(equalTo(baseStats)));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_noPropertiesAreSet_throwsException() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .build();        
    }
    
    @Test
    public void testBuilderBuild_noLevelIsSet_defaultsToOne() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();        
        
        assertThat(testCandidate.getLevel(), is(equalTo(1)));
    }
    
    @Test
    public void testLevelUpBy_levelsUpByFive_endLevelIsFiveHigher() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build(); 

        testCandidate.levelUpBy(5);
        
        assertThat(testCandidate.getLevel(), is(equalTo(6)));
    }
    
    @Test
    public void testBuilderBuild_levelIsSetToFifty_productIsLevelFifty() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(50)
            .build(); 
        
        assertThat(testCandidate.getLevel(), is(equalTo(50)));
    }
    
    @Test(expected = RuntimeException.class)
    public void testLevelUpBy_levelOverMaximum_throwsException() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(50)
            .build(); 

        testCandidate.levelUpBy(1);
    }   
    
    @Test(expected = RuntimeException.class)
    public void testLevelUpBy_levelIsThirtyOverlevelsByFive_throwsException() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(30)
            .build(); 

        testCandidate.levelUpBy(25);
    }   
    
    @Test
    public void testIsMaximumLevel_levelIsMaximum_returnsTrue() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(50)
            .build(); 
        
        assertThat(testCandidate.isMaximumLevel(), is(true));
    }
    
    @Test
    public void testIsMaximumLevel_levelIsNotMaximum_returnsFalse() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(25)
            .build(); 
        
        assertThat(testCandidate.isMaximumLevel(), is(false));
    } 
    
    @Test
    public void testIsMaximumLevel_levelsUpToFifty_returnsTrue() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(49)
            .build(); 

        testCandidate.levelUpBy(1);
        
        assertThat(testCandidate.isMaximumLevel(), is(true));
    }    
    
    @Test
    public void testBuilderBuild_genomeIsSet_productContainsSameGenome() throws NotAllPropertiesAreSetException {
        CoreClassesFactory.setInstance(new RealCoreClassesFactory());
        
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        Genome genome = new Genome.Random().build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setGenome(genome)
            .build(); 
        
        assertThat(testCandidate.getGenome(), is(genome));
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private ExorionSpeciesBaseStatsAtMaximumLevel createBaseStatsForExorion() {
        return new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(50)
            .setAttackBaseStat(49)
            .setDefenseBaseStat(48)
            .setSpecialAttackBaseStat(47)
            .setSpecialDefenseBaseStat(46)
            .setAccuracyBaseStat(45)
            .setEvasivenessBaseStat(44)
            .setCriticalHitFrequencyBaseStat(43)
            .setCriticalHitAvoidanceBaseStat(42)
            .setSwiftnessBaseStat(41)
            .build();        
    }    
    
    //</editor-fold>
}