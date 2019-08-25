package de.ethasia.exorions.core.quests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

public class Quest {
    
    //<editor-fold defaultstate="collapsed" desc="Getters">
    
    private final String title;
    public String getTitle() {
        return title;
    }
    
    private final String currentDescription;
    public String getCurrentDescription() {
        return currentDescription;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private Quest(Builder builder) {
        title = builder.title;
        currentDescription = builder.currentDescription;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String title;
        private String currentDescription;
        
        public Builder setTitle(String value) {
            title = value;
            return this;
        }
        
        public Builder setCurrentDescription(String value) {
            currentDescription = value;
            return this;
        }
        
        public Quest build() {
            if (!allPropertiesAreSet()) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new Quest(this);
        }
        
        private boolean allPropertiesAreSet() {
            return null != title && !title.equals("") && null != currentDescription && !currentDescription.equals("");
        }
    }

    //</editor-fold>
}