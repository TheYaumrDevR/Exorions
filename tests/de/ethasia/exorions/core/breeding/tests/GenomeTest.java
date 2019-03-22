package de.ethasia.exorions.core.breeding.tests;

import de.ethasia.exorions.core.breeding.ChromosomePair;
import de.ethasia.exorions.core.breeding.Genome;
import de.ethasia.exorions.core.interfaces.CoreClassesFactory;
import de.ethasia.exorions.core.mocks.MockCoreClassesFactory;
import de.ethasia.exorions.core.mocks.RandomNumberGeneratorMock;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GenomeTest {
    
    @BeforeClass
    public static void initDependencies() {
        CoreClassesFactory.setInstance(new MockCoreClassesFactory());
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.setBooleanSequenceToUse(new boolean[] {false, true, false, false, true, true, true, true, false, false, false, false, false, false, true, false, true, true, false, true});
        rngMock.setIntegerSequenceToUse(new int[] {1, -1, 1, -1, 1, 1, 0, -1, -1, 1, 0, -1, 1, -1, 1, 0, -1, -1, -1}); 
    }
    
    @Before
    public void prepareUnitTests() {
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        rngMock.reset();         
    }

    @Test
    public void testRandomBuilder_createsRandomChromosomeSetByCallingRng() {   
        RandomNumberGeneratorMock rngMock = (RandomNumberGeneratorMock)(new MockCoreClassesFactory().getRandomNumberGeneratorSingletonInstance());
        Genome product = new Genome.Random().build();
        
        int allelesPerChromosomePair = 20;
        int chromosomePairs = 25;
        int expectedRngCallCount = allelesPerChromosomePair * chromosomePairs;
        
        assertThat(product, is(notNullValue()));
        assertThat(rngMock.getCallCount("createRandomIntegerBetweenAnd"), is(expectedRngCallCount));
        assertThat(rngMock.getCallCount("createRandomBoolean"), is(expectedRngCallCount));        
    }
    
    @Test
    public void testGetChromosomePair_fromRandomlyCreatedSet_pairsAreReturned() {
        Genome product = new Genome.Random().build();
        
        assertThat(product.getChromosomePairOne(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwo(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairThree(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairFour(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairFive(), is(ChromosomePair.class));
        
        assertThat(product.getChromosomePairSix(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairSeven(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairEight(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairNine(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTen(), is(ChromosomePair.class));
        
        assertThat(product.getChromosomePairEleven(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwelve(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairThirteen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairFourteen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairFifteen(), is(ChromosomePair.class));  
        
        assertThat(product.getChromosomePairSixteen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairSeventeen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairEighteen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairNineteen(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwenty(), is(ChromosomePair.class));         
        
        assertThat(product.getChromosomePairTwentyone(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwentytwo(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwentythree(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwentyfour(), is(ChromosomePair.class));
        assertThat(product.getChromosomePairTwentyfive(), is(ChromosomePair.class)); 
    }
    
    @Test
    public void testGetMaximumHealthTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getMaximumHealthTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getMaximumHealthGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    public void testGetAttackTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getAttackTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getAttackGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }  
    
    @Test
    public void testGetDefenseTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getDefenseTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getDefenseGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    } 

    @Test
    public void testGetSpecialAttackTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getSpecialAttackTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getSpecialAttackGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }  
    
    @Test
    public void testGetSpecialDefenseTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getSpecialDefenseTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getSpecialDefenseGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }    
    
    @Test
    public void testGetAccuracyTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getAccuracyTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getAccuracyGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    } 
    
    @Test
    public void testGetEvasivenessTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getEvasivenessTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getEvasivenessGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }  
    
    @Test
    public void testGetCriticalHitFrequencyTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getCriticalHitFrequencyTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getCriticalHitFrequencyGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }  
    
    @Test
    public void testGetCriticalHitAvoidanceTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getCriticalHitAvoidanceTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getCriticalHitAvoidanceGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    public void testGetSwiftnessTotalGeneValue_isEqualToSumOfGeneValuesOfChromosomePairs() {
        Genome testCandidate = new Genome.Random().build();
        
        int actual = testCandidate.getSwiftnessTotalGeneValue();
        ChromosomePair[] chromosomePairs = getAllChromosomePairsFromGenome(testCandidate);
        
        int expected = 0;
        for (ChromosomePair chromosomePair : chromosomePairs) {
            expected += chromosomePair.getSwiftnessGeneValue();
        }
        
        assertThat(actual, is(equalTo(expected)));
    }     
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private ChromosomePair[] getAllChromosomePairsFromGenome(Genome source) {
        ChromosomePair[] result = new ChromosomePair[25];
        
        result[0] = source.getChromosomePairOne();
        result[1] = source.getChromosomePairTwo();
        result[2] = source.getChromosomePairThree();
        result[3] = source.getChromosomePairFour();
        result[4] = source.getChromosomePairFive();
        
        result[5] = source.getChromosomePairSix();
        result[6] = source.getChromosomePairSeven();
        result[7] = source.getChromosomePairEight();
        result[8] = source.getChromosomePairNine();
        result[9] = source.getChromosomePairTen();  
        
        result[10] = source.getChromosomePairEleven();
        result[11] = source.getChromosomePairTwelve();
        result[12] = source.getChromosomePairThirteen();
        result[13] = source.getChromosomePairFourteen();
        result[14] = source.getChromosomePairFifteen();  
        
        result[15] = source.getChromosomePairSixteen();
        result[16] = source.getChromosomePairSeventeen();
        result[17] = source.getChromosomePairEighteen();
        result[18] = source.getChromosomePairNineteen();
        result[19] = source.getChromosomePairTwenty();   
        
        result[20] = source.getChromosomePairTwentyone();
        result[21] = source.getChromosomePairTwentytwo();
        result[22] = source.getChromosomePairTwentythree();
        result[23] = source.getChromosomePairTwentyfour();
        result[24] = source.getChromosomePairTwentyfive();         
        
        return result;
    }
    
    //</editor-fold>
}