package de.ethasia.exorions.core;

public class FastMath {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long MASK_EXTRACT_EXPONENT_FROM_DOUBLE_BITS = 0x7ff0000000000000L;
    private static final long MASK_EXTRACT_SIGNIFICAND_FROM_DOUBLE_BITS = 0x000fffffffffffffL;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static double pow(double base, double exponent) {
        if (0 == exponent) {
            return 1.0;
        }
        
        return FastMath.calculatePowerForNonTrivialCase(base, exponent);
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private static double calculatePowerForNonTrivialCase(double base, double exponent) {
        final long baseBits = Double.doubleToRawLongBits(base);
        final int baseRawExponent = (int)((baseBits & FastMath.MASK_EXTRACT_EXPONENT_FROM_DOUBLE_BITS) >> 52);
        final long baseRawSignificand = baseBits & FastMath.MASK_EXTRACT_SIGNIFICAND_FROM_DOUBLE_BITS;
        
        final long exponentBits = Double.doubleToRawLongBits(exponent);
        final int exponentRawExponent = (int)((exponentBits & FastMath.MASK_EXTRACT_EXPONENT_FROM_DOUBLE_BITS) >> 52);
        final long exponentRawSignificand = exponentBits & FastMath.MASK_EXTRACT_SIGNIFICAND_FROM_DOUBLE_BITS;
        
        if (exponentRawExponent > 1085) {
            return calculatePowerForLargeIntegralValueOrSpecialValueExponent(exponent, baseRawExponent, baseRawSignificand, exponentRawExponent, exponentRawSignificand);
        } else {
            return calculatePowerForNormalExponent(exponentRawExponent);
        }
    }    
    
    private static double calculatePowerForLargeIntegralValueOrSpecialValueExponent(double exponent, 
            int baseRawExponent, 
            long baseRawSignificand, 
            int exponentRawExponent, 
            long exponentRawSignificand) {
        
        if ((exponentRawExponent == 2047 && exponentRawSignificand != 0)
            || (baseRawExponent == 2047 && baseRawSignificand != 0)) {
            return Double.NaN;
        } else if (baseRawExponent == 1023 && baseRawSignificand == 0) {
            return calculatePowerForBasePlusMinusOneAndExponentEvenOrInfinite(exponentRawExponent);
        } else {
            return calculatePowerForInfinityOrNearInfinityExponent(exponent, baseRawExponent);
        }
    }
    
    private static double calculatePowerForBasePlusMinusOneAndExponentEvenOrInfinite(int exponentRawExponent) {
        if (exponentRawExponent == 2047) {
            return Double.NaN;
        }
            
        return 1.0;
    }
    
    private static double calculatePowerForInfinityOrNearInfinityExponent(double exponent, int baseRawExponent) {
        if ((exponent > 0) ^ (baseRawExponent < 1023)) {
            return Double.POSITIVE_INFINITY;
        }
            
        return +0.0;
    }
    
    private static double calculatePowerForNormalExponent(int exponentRawExponent) {
        if (exponentRawExponent >= 1023) {
            calculatePowerWithPossibleIntegralValueNormalNumberExponent();
        } else {
            
        }    
        
        return 0.0;
    }    
    
    private static double calculatePowerWithPossibleIntegralValueNormalNumberExponent() {
        return 0.0;
    }
    
    //</editor-fold>
}