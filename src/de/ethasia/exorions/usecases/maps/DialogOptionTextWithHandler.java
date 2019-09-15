package de.ethasia.exorions.usecases.maps;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

public class DialogOptionTextWithHandler {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String text;
    public String getText() {
        return text;
    }
    
    private final DialogOptionSelectionHandler selectionHandler;
    public DialogOptionSelectionHandler getSelectionHandler() {
        return selectionHandler;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private DialogOptionTextWithHandler(Builder builder) {
        text = builder.text;
        selectionHandler = builder.selectionHandler;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String text;
        private DialogOptionSelectionHandler selectionHandler;
        
        public Builder setText(String value) {
            text = value;
            return this;
        }
        
        public Builder setSelectionHandler(DialogOptionSelectionHandler value) {
            selectionHandler = value;
            return this;
        }
        
        public DialogOptionTextWithHandler build() {
            if (null == text 
                    || text.isEmpty() 
                    || null == selectionHandler) {
                
                throw new NotAllPropertiesAreSetException();
            }
            
            return new DialogOptionTextWithHandler(this);
        }
    }
    
    //</editor-fold>
}