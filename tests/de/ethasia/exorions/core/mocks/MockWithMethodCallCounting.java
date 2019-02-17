package de.ethasia.exorions.core.mocks;

import java.util.HashMap;
import java.util.Map;

public abstract class MockWithMethodCallCounting {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
   
    private final Map<String, Integer> methodNameByCallCount;    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MockWithMethodCallCounting() {
        methodNameByCallCount = new HashMap<>();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public void reset() {
        methodNameByCallCount.clear();
    }
    
    public int getCallCount(String methodName) {
        if (null == methodNameByCallCount.get(methodName)) {
            return 0;
        }
        
        return methodNameByCallCount.get(methodName);
    }
    
    protected void incrementCallCountForMethodName(String methodName) {
        if (null != methodNameByCallCount.get(methodName)) {
            int currentCallCount = methodNameByCallCount.get(methodName);
            methodNameByCallCount.put(methodName, currentCallCount + 1);
        } else {
            methodNameByCallCount.put(methodName, 1);
        }
    }    
    
    //</editor-fold>
}