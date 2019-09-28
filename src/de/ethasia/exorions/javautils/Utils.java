package de.ethasia.exorions.javautils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static String convertExceptionStackTraceToString(Exception exception) {
        String result;
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        
        result = sw.toString();
        
        return result;
    }    
    
    //</editor-fold>
}