package de.ethasia.exorions.core.breeding;

public class ChromosomeSet {
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    private final ChromosomePair chromosomePairOne;
    public ChromosomePair getChromosomePairOne() {
        return chromosomePairOne;
    }
    
    private final ChromosomePair chromosomePairTwo;
    public ChromosomePair getChromosomePairTwo() {
        return chromosomePairTwo;
    }
    
    private final ChromosomePair chromosomePairThree;
    public ChromosomePair getChromosomePairThree() {
        return chromosomePairThree;
    }
    
    private final ChromosomePair chromosomePairFour;
    public ChromosomePair getChromosomePairFour() {
        return chromosomePairFour;
    }
    
    private final ChromosomePair chromosomePairFive;
    public ChromosomePair getChromosomePairFive() {
        return chromosomePairFive;
    }

    private final ChromosomePair chromosomePairSix;
    public ChromosomePair getChromosomePairSix() {
        return chromosomePairSix;
    }
    
    private final ChromosomePair chromosomePairSeven;
    public ChromosomePair getChromosomePairSeven() {
        return chromosomePairSeven;
    } 

    private final ChromosomePair chromosomePairEight;
    public ChromosomePair getChromosomePairEight() {
        return chromosomePairEight;
    }   
    
    private final ChromosomePair chromosomePairNine;
    public ChromosomePair getChromosomePairNine() {
        return chromosomePairNine;
    }

    private final ChromosomePair chromosomePairTen;
    public ChromosomePair getChromosomePairTen() {
        return chromosomePairTen;
    } 
    
    private final ChromosomePair chromosomePairEleven;
    public ChromosomePair getChromosomePairEleven() {
        return chromosomePairEleven;
    } 

    private final ChromosomePair chromosomePairTwelve;
    public ChromosomePair getChromosomePairTwelve() {
        return chromosomePairTwelve;
    } 

    private final ChromosomePair chromosomePairThirteen;
    public ChromosomePair getChromosomePairThirteen() {
        return chromosomePairThirteen;
    } 

    private final ChromosomePair chromosomePairFourteen;
    public ChromosomePair getChromosomePairFourteen() {
        return chromosomePairFourteen;
    } 

    private final ChromosomePair chromosomePairFifteen;
    public ChromosomePair getChromosomePairFifteen() {
        return chromosomePairFifteen;
    }
    
    private final ChromosomePair chromosomePairSixteen;
    public ChromosomePair getChromosomePairSixteen() {
        return chromosomePairSixteen;
    } 

    private final ChromosomePair chromosomePairSeventeen;
    public ChromosomePair getChromosomePairSeventeen() {
        return chromosomePairSeventeen;
    } 

    private final ChromosomePair chromosomePairEighteen;
    public ChromosomePair getChromosomePairEighteen() {
        return chromosomePairEighteen;
    } 

    private final ChromosomePair chromosomePairNineteen;
    public ChromosomePair getChromosomePairNineteen() {
        return chromosomePairNineteen;
    } 

    private final ChromosomePair chromosomePairTwenty;
    public ChromosomePair getChromosomePairTwenty() {
        return chromosomePairTwenty;
    }     
    
    private final ChromosomePair chromosomePairTwentyone;
    public ChromosomePair getChromosomePairTwentyone() {
        return chromosomePairTwentyone;
    } 

    private final ChromosomePair chromosomePairTwentytwo;
    public ChromosomePair getChromosomePairTwentytwo() {
        return chromosomePairTwentytwo;
    } 

    private final ChromosomePair chromosomePairTwentythree;
    public ChromosomePair getChromosomePairTwentythree() {
        return chromosomePairTwentythree;
    } 

    private final ChromosomePair chromosomePairTwentyfour;
    public ChromosomePair getChromosomePairTwentyfour() {
        return chromosomePairTwentyfour;
    } 

    private final ChromosomePair chromosomePairTwentyfive;
    public ChromosomePair getChromosomePairTwentyfive() {
        return chromosomePairTwentyfive;
    }     
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private ChromosomeSet(Random builder) {
        chromosomePairOne = builder.chromosomePairOne;
        chromosomePairTwo = builder.chromosomePairTwo;
        chromosomePairThree = builder.chromosomePairThree;
        chromosomePairFour = builder.chromosomePairFour;
        chromosomePairFive = builder.chromosomePairFive;
        
        chromosomePairSix = builder.chromosomePairSix;
        chromosomePairSeven = builder.chromosomePairSeven;
        chromosomePairEight = builder.chromosomePairEight;
        chromosomePairNine = builder.chromosomePairNine;
        chromosomePairTen = builder.chromosomePairTen;
        
        chromosomePairEleven = builder.chromosomePairEleven;
        chromosomePairTwelve = builder.chromosomePairTwelve;
        chromosomePairThirteen = builder.chromosomePairThirteen;
        chromosomePairFourteen = builder.chromosomePairFourteen;
        chromosomePairFifteen = builder.chromosomePairFifteen;

        chromosomePairSixteen = builder.chromosomePairSixteen;
        chromosomePairSeventeen = builder.chromosomePairSeventeen;
        chromosomePairEighteen = builder.chromosomePairEighteen;
        chromosomePairNineteen = builder.chromosomePairNineteen;
        chromosomePairTwenty = builder.chromosomePairTwenty;  
        
        chromosomePairTwentyone = builder.chromosomePairTwentyone;
        chromosomePairTwentytwo = builder.chromosomePairTwentytwo;
        chromosomePairTwentythree = builder.chromosomePairTwentythree;
        chromosomePairTwentyfour = builder.chromosomePairTwentyfour;
        chromosomePairTwentyfive = builder.chromosomePairTwentyfive;         
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public int getMaximumHealthTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getMaximumHealthGeneValue();
        }
        
        return result;
    }
    
    public int getAttackTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getAttackGeneValue();
        }
        
        return result;
    }    
    
    public int getDefenseTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getDefenseGeneValue();
        }
        
        return result;
    }
    
    public int getSpecialAttackTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getSpecialAttackGeneValue();
        }
        
        return result;
    }
    
    public int getSpecialDefenseTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getSpecialDefenseGeneValue();
        }
        
        return result;
    }   
    
    public int getAccuracyTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getAccuracyGeneValue();
        }
        
        return result;
    }

    public int getEvasivenessTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getEvasivenessGeneValue();
        }
        
        return result;
    }  
    
    public int getCriticalHitFrequencyTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getCriticalHitFrequencyGeneValue();
        }
        
        return result;
    } 

    public int getCriticalHitAvoidanceTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getCriticalHitAvoidanceGeneValue();
        }
        
        return result;
    }    
    
    public int getSwiftnessTotalGeneValue() {
        int result = 0;
        
        ChromosomePair[] chromosomePairs = getAllChromosomePairs();
        for (ChromosomePair chromosomePair : chromosomePairs) {
            result += chromosomePair.getSwiftnessGeneValue();
        }
        
        return result;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private ChromosomePair[] getAllChromosomePairs() {
        ChromosomePair[] result = new ChromosomePair[25];
        
        result[0] = getChromosomePairOne();
        result[1] = getChromosomePairTwo();
        result[2] = getChromosomePairThree();
        result[3] = getChromosomePairFour();
        result[4] = getChromosomePairFive();
        
        result[5] = getChromosomePairSix();
        result[6] = getChromosomePairSeven();
        result[7] = getChromosomePairEight();
        result[8] = getChromosomePairNine();
        result[9] = getChromosomePairTen();  
        
        result[10] = getChromosomePairEleven();
        result[11] = getChromosomePairTwelve();
        result[12] = getChromosomePairThirteen();
        result[13] = getChromosomePairFourteen();
        result[14] = getChromosomePairFifteen();  
        
        result[15] = getChromosomePairSixteen();
        result[16] = getChromosomePairSeventeen();
        result[17] = getChromosomePairEighteen();
        result[18] = getChromosomePairNineteen();
        result[19] = getChromosomePairTwenty();   
        
        result[20] = getChromosomePairTwentyone();
        result[21] = getChromosomePairTwentytwo();
        result[22] = getChromosomePairTwentythree();
        result[23] = getChromosomePairTwentyfour();
        result[24] = getChromosomePairTwentyfive();         
        
        return result;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Random Builder">
    
    public static class Random {
        
        private ChromosomePair chromosomePairOne;
        private ChromosomePair chromosomePairTwo;
        private ChromosomePair chromosomePairThree;
        private ChromosomePair chromosomePairFour;
        private ChromosomePair chromosomePairFive;
        
        private ChromosomePair chromosomePairSix;
        private ChromosomePair chromosomePairSeven;
        private ChromosomePair chromosomePairEight;
        private ChromosomePair chromosomePairNine;
        private ChromosomePair chromosomePairTen;
        
        private ChromosomePair chromosomePairEleven;
        private ChromosomePair chromosomePairTwelve;
        private ChromosomePair chromosomePairThirteen;
        private ChromosomePair chromosomePairFourteen;
        private ChromosomePair chromosomePairFifteen;
        
        private ChromosomePair chromosomePairSixteen;
        private ChromosomePair chromosomePairSeventeen;
        private ChromosomePair chromosomePairEighteen;
        private ChromosomePair chromosomePairNineteen;
        private ChromosomePair chromosomePairTwenty;
        
        private ChromosomePair chromosomePairTwentyone;
        private ChromosomePair chromosomePairTwentytwo;
        private ChromosomePair chromosomePairTwentythree;
        private ChromosomePair chromosomePairTwentyfour;
        private ChromosomePair chromosomePairTwentyfive;
        
        public ChromosomeSet build() {
            chromosomePairOne = new ChromosomePair.Random().build();
            chromosomePairTwo = new ChromosomePair.Random().build();
            chromosomePairThree = new ChromosomePair.Random().build();
            chromosomePairFour = new ChromosomePair.Random().build();
            chromosomePairFive = new ChromosomePair.Random().build();
            
            chromosomePairSix = new ChromosomePair.Random().build();
            chromosomePairSeven = new ChromosomePair.Random().build();
            chromosomePairEight = new ChromosomePair.Random().build();
            chromosomePairNine = new ChromosomePair.Random().build();
            chromosomePairTen = new ChromosomePair.Random().build();
            
            chromosomePairEleven = new ChromosomePair.Random().build();
            chromosomePairTwelve = new ChromosomePair.Random().build();
            chromosomePairThirteen = new ChromosomePair.Random().build();
            chromosomePairFourteen = new ChromosomePair.Random().build();
            chromosomePairFifteen = new ChromosomePair.Random().build();
            
            chromosomePairSixteen = new ChromosomePair.Random().build();
            chromosomePairSeventeen = new ChromosomePair.Random().build();
            chromosomePairEighteen = new ChromosomePair.Random().build();
            chromosomePairNineteen = new ChromosomePair.Random().build();
            chromosomePairTwenty = new ChromosomePair.Random().build();
            
            chromosomePairTwentyone = new ChromosomePair.Random().build();
            chromosomePairTwentytwo = new ChromosomePair.Random().build();
            chromosomePairTwentythree = new ChromosomePair.Random().build();
            chromosomePairTwentyfour = new ChromosomePair.Random().build();
            chromosomePairTwentyfive = new ChromosomePair.Random().build();
            
            return new ChromosomeSet(this);
        }
    }
    
    //</editor-fold>
}