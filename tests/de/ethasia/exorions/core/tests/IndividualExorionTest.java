package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.battle.BattleAbilityBase;
import de.ethasia.exorions.core.ExorionSpecies;
import de.ethasia.exorions.core.ExorionSpeciesBaseStatsAtMaximumLevel;
import de.ethasia.exorions.core.IndividualExorion;
import de.ethasia.exorions.core.IndividualExorionBaseStats;
import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.OutOfLevelBoundsException;
import de.ethasia.exorions.core.battle.BattleAbility;
import de.ethasia.exorions.core.battle.DirectDamageAbilityEffect;
import de.ethasia.exorions.core.battle.RequirementsToLearnAbilityAreNotFulfilledException;
import de.ethasia.exorions.core.breeding.Genome;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.interfaces.RealCoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockGenome;
import de.ethasia.exorions.core.mocks.TestExorions;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityPowerByLevelTables;
import de.ethasia.exorions.ioadapters.repositories.BattleAbilityRequiredLevelTables;

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
        
        BattleAbilityBase.Builder battleAbilityBuilder = new BattleAbilityBase.Builder();
        BattleAbilityBase abilityToLearn = battleAbilityBuilder
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    }
    
    @Test
    public void testCanLearnAbility_levelRequirementIsNotMet_cannotLearn() {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build(); 
        
        BattleAbilityBase.Builder battleAbilityBuilder = new BattleAbilityBase.Builder();
        BattleAbilityBase abilityToLearn = battleAbilityBuilder
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(5)
            .build();        
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(false));
    }
    
    @Test
    public void testCanLearnAbility_levelRequirementIsMet_canLearn() {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setLevel(11)
            .build(); 
        
        BattleAbilityBase.Builder battleAbilityBuilder = new BattleAbilityBase.Builder();
        BattleAbilityBase abilityToLearn = battleAbilityBuilder
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(5)
            .build();        
        
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
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.NEEDLES)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
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
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.CLAWS)
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
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
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        
        boolean result = testCandidate.canLearnAbility(abilityToLearn);
        
        assertThat(result, is(true));
    } 
    
    @Test
    public void testCanLearnAbility_abilityWithTwoRequirements_cannotLearnAsDifferentAreSatisfied() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setFulfilledLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)   
            .setSpeciesBaseStats(createBaseStatsForExorion())
            .build();        
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .build();
        
        BattleAbilityBase abilityToLearn = new BattleAbilityBase.Builder()
            .setLearningRequirements(AbilityLearningRequirements.TAIL)
            .setLearningRequirements(AbilityLearningRequirements.TENTACLES)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
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
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
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
    
    @Test(expected = OutOfLevelBoundsException.class)
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
    
    @Test(expected = OutOfLevelBoundsException.class)
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
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
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
    
    @Test
    public void testLevelUpBy_levelUpIsSuccessful_baseStatsAreCalculatedBasedOnSpeciesStatsAndGenomeAndNewLevel() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setLevel(30)
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
            .build(); 

        testCandidate.levelUpBy(1);
        
        assertThat(testCandidate.getBaseStats().getMaximumHealthPoints(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getAttackValue(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getDefenseValue(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getSpecialAttackValue(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getSpecialDefenseValue(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getAccuracy(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getEvasiveness(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getCriticalHitFrequency(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getCriticalHitAvoidance(), is(equalTo(47)));
        assertThat(testCandidate.getBaseStats().getSwiftness(), is(equalTo(47)));
    } 
    
    @Test
    public void testLevelUpBy_levelUpIsSuccessful_currentHealthPointsAreEqualToMaximumHealthPoints() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setLevel(30)
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
            .build(); 

        testCandidate.levelUpBy(1);
        
        assertThat(testCandidate.getBaseStats().getCurrentHealthPoints(), is(equalTo(47)));
    }    
    
    @Test
    public void testTakeDamage_damageIsLessThanCurrentHealth_currentHealthIsLoweredByDamage() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setLevel(30)
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
            .build(); 

        testCandidate.levelUpBy(1);
        testCandidate.takeDamage(20);
        
        assertThat(testCandidate.getBaseStats().getCurrentHealthPoints(), is(equalTo(27)));
    } 

    @Test
    public void testIsFainted_healthIsFull_returnsFalse() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setLevel(49)
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
            .build(); 

        testCandidate.levelUpBy(1);
        
        assertThat(testCandidate.isFainted(), is(false));
    }     
    
    @Test
    public void testIsFainted_healthIsZero_returnsTrue() throws NotAllPropertiesAreSetException {
        ExorionSpecies species = new ExorionSpecies.Builder()
            .setSpeciesBaseStats(createBaseStatsForExorionWithAllValuesSetTo(50))
            .build();
        
        IndividualExorionBaseStats individualBaseStats = new IndividualExorionBaseStats.Builder()
            .build();
        
        IndividualExorion testCandidate = new IndividualExorion.Builder()
            .setSpecies(species)
            .setBaseStats(individualBaseStats)
            .setLevel(49)
            .setGenome(createMockGenomeWithFixedValueSetTo(25))
            .build(); 

        testCandidate.levelUpBy(1);
        testCandidate.takeDamage(200);
        
        assertThat(testCandidate.isFainted(), is(true));
    }
    
    @Test
    public void testLearnAbilityOnSlotOne_canLearnAbility_abilityInSlotOneIsSet() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        
        testCandidate.learnAbilityOnSlotOne(ability);
        
        BattleAbility abilityOnSlotOne = testCandidate.getAbilityOnSlotOne();
        
        assertThat(abilityOnSlotOne, is(equalTo(ability)));
        assertThat(testCandidate.getAbilityOnSlotTwo(), is(equalTo(null)));
        assertThat(testCandidate.getAbilityOnSlotThree(), is(equalTo(null)));
    }
    
    @Test
    public void testLearnAbilityOnSlotTwo_canLearnAbility_abilityInSlotTwoIsSet() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        
        testCandidate.learnAbilityOnSlotTwo(ability);
        
        BattleAbility abilityOnSlotTwo = testCandidate.getAbilityOnSlotTwo();
        
        assertThat(abilityOnSlotTwo, is(equalTo(ability)));
        assertThat(testCandidate.getAbilityOnSlotOne(), is(equalTo(null)));
        assertThat(testCandidate.getAbilityOnSlotThree(), is(equalTo(null)));
    } 
    
    @Test
    public void testLearnAbilityOnSlotThree_canLearnAbility_abilityInSlotThreeIsSet() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        
        testCandidate.learnAbilityOnSlotThree(ability);
        
        BattleAbility abilityOnSlotThree = testCandidate.getAbilityOnSlotThree();
        
        assertThat(abilityOnSlotThree, is(equalTo(ability)));
        assertThat(testCandidate.getAbilityOnSlotOne(), is(equalTo(null)));
        assertThat(testCandidate.getAbilityOnSlotTwo(), is(equalTo(null)));
    } 

    @Test(expected = RequirementsToLearnAbilityAreNotFulfilledException.class)
    public void testLearnAbilityOnSlotOne_canNotLearnAbility_throwsException() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        testCandidate.learnAbilityOnSlotOne(ability);
    }    
    
    @Test(expected = RequirementsToLearnAbilityAreNotFulfilledException.class)
    public void testLearnAbilityOnSlotTwo_canNotLearnAbility_throwsException() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        testCandidate.learnAbilityOnSlotTwo(ability);
    }
    
    @Test(expected = RequirementsToLearnAbilityAreNotFulfilledException.class)
    public void testLearnAbilityOnSlotThree_canNotLearnAbility_throwsException() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.SKELETON)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .build();
        
        testCandidate.learnAbilityOnSlotThree(ability);
    }   
    
    @Test
    public void testUseSlotOneAbility_directDamageAbility_damageIsDealt() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        IndividualExorion defenderCopy = TestExorions.findExorionById(1);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        directDamageEffect.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);
        
        testCandidate.learnAbilityOnSlotOne(directDamageEffect);
        testCandidate.useSlotOneAbility(defender);
        
        directDamageEffect.use(testCandidate, defenderCopy);
        int expectedDefenderHealth = defenderCopy.getBaseStats().getCurrentHealthPoints();
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    } 
    
    @Test
    public void testUseSlotTwoAbility_directDamageAbility_damageIsDealt() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        IndividualExorion defenderCopy = TestExorions.findExorionById(1);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        directDamageEffect.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);
        
        testCandidate.learnAbilityOnSlotTwo(directDamageEffect);
        testCandidate.useSlotTwoAbility(defender);
        
        directDamageEffect.use(testCandidate, defenderCopy);
        int expectedDefenderHealth = defenderCopy.getBaseStats().getCurrentHealthPoints();
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    } 
    
    @Test
    public void testUseSlotThreeAbility_directDamageAbility_damageIsDealt() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        IndividualExorion defenderCopy = TestExorions.findExorionById(1);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        directDamageEffect.setAbilityPowerByAbilityLevel(BattleAbilityPowerByLevelTables.getStandardAbilityPowerByLevelTable());
        
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);
        
        testCandidate.learnAbilityOnSlotThree(directDamageEffect);
        testCandidate.useSlotThreeAbility(defender);
        
        directDamageEffect.use(testCandidate, defenderCopy);
        int expectedDefenderHealth = defenderCopy.getBaseStats().getCurrentHealthPoints();
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    }  
    
    @Test
    public void testUseSlotOneAbility_noAbilityIsSet_nothingHappens() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        int expectedDefenderHealth = defender.getBaseStats().getCurrentHealthPoints();
        testCandidate.useSlotOneAbility(defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    } 
    
    @Test
    public void testUseSlotTwoAbility_noAbilityIsSet_nothingHappens() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        int expectedDefenderHealth = defender.getBaseStats().getCurrentHealthPoints();
        testCandidate.useSlotTwoAbility(defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    }
    
    @Test
    public void testUseSlotThreeAbility_noAbilityIsSet_nothingHappens() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        IndividualExorion defender = TestExorions.findExorionById(1);
        
        int expectedDefenderHealth = defender.getBaseStats().getCurrentHealthPoints();
        testCandidate.useSlotThreeAbility(defender);
        
        assertThat(defender.getBaseStats().getCurrentHealthPoints(), is(equalTo(expectedDefenderHealth)));
    }    
    
    @Test
    public void testHasAtLeastOneAbility_noAbilityIsSet_returnsFalse() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(1);
        
        boolean result = testCandidate.hasAtLeastOneAbility();
        
        assertThat(result, is(equalTo(false)));
    }
    
    @Test
    public void testHasAtLeastOneAbility_oneAbilityIsSet_returnsTrue() throws NotAllPropertiesAreSetException {
        IndividualExorion testCandidate = TestExorions.findExorionById(0);
        
        DirectDamageAbilityEffect directDamageEffect = new DirectDamageAbilityEffect();
        BattleAbilityBase ability = new BattleAbilityBase.Builder()
            .setName("Foosh")
            .setLearningRequirements(AbilityLearningRequirements.TEETH)
            .setRequiredLevelByAbilityLevel(BattleAbilityRequiredLevelTables.getRequiredLevelTableForBasicLevelOneAbility())
            .setAbilityLevel(1)
            .build();
        directDamageEffect.decorate(ability);
        
        testCandidate.learnAbilityOnSlotThree(directDamageEffect);
        
        boolean result = testCandidate.hasAtLeastOneAbility();
        
        assertThat(result, is(equalTo(true)));
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
    
    public static ExorionSpeciesBaseStatsAtMaximumLevel createBaseStatsForExorionWithAllValuesSetTo(int statValues) {
        return new ExorionSpeciesBaseStatsAtMaximumLevel.Builder()
            .setMaximumHealthBaseStat(statValues)
            .setAttackBaseStat(statValues)
            .setDefenseBaseStat(statValues)
            .setSpecialAttackBaseStat(statValues)
            .setSpecialDefenseBaseStat(statValues)
            .setAccuracyBaseStat(statValues)
            .setEvasivenessBaseStat(statValues)
            .setCriticalHitFrequencyBaseStat(statValues)
            .setCriticalHitAvoidanceBaseStat(statValues)
            .setSwiftnessBaseStat(statValues)
            .build();        
    } 
    
    private Genome createMockGenomeWithFixedValueSetTo(int geneValue) {
        return new MockGenome(geneValue);
    }
    
    //</editor-fold>
}