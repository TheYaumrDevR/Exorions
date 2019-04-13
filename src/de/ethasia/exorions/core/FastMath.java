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
        final long baseRawMantissa = baseBits & FastMath.MASK_EXTRACT_SIGNIFICAND_FROM_DOUBLE_BITS;
        
        final long exponentBits = Double.doubleToRawLongBits(exponent);
        final int exponentRawExponent = (int)((exponentBits & FastMath.MASK_EXTRACT_EXPONENT_FROM_DOUBLE_BITS) >> 52);
        final long exponentRawMantissa = exponentBits & FastMath.MASK_EXTRACT_SIGNIFICAND_FROM_DOUBLE_BITS;
        
        return 0.0;
    }    
    
    //</editor-fold>
}