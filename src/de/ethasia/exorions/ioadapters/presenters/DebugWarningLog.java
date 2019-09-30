package de.ethasia.exorions.ioadapters.presenters;

import java.util.LinkedList;
import java.util.List;

public class DebugWarningLog {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private static final List<LogEntry> logEntries = new LinkedList<>();
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public static void addLogEntry(long timeStamp, String message) {
        LogEntry newEntry = new LogEntry(timeStamp, message);
        logEntries.add(newEntry);
    }
    
    //</editor-fold>
            
    //<editor-fold defaultstate="collapsed" desc="Helper Objects">
            
    private static class LogEntry {
        
        private final long timeStamp;
        private final String message;
        
        public LogEntry(long timeStamp, String message) {
            this.timeStamp = timeStamp;
            this.message = message;
        }
    }
    
    //</editor-fold>
}