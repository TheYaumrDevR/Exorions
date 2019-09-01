package de.ethasia.exorions.core.dialogs;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

public class DialogOption {
    
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    
    private final DialogNode followUpNode;
    public DialogNode getFollowUpNode() {
        return followUpNode;
    }
    
    private final String text;
    public String getText() {
        return text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private DialogOption(Builder builder) {
        followUpNode = builder.followUpNode;
        text = builder.text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private DialogNode followUpNode;
        private String text;
        
        public Builder setText(String value) {
            text = value;
            return this;
        }
        
        public Builder setFollowUpNode(DialogNode value) {
            followUpNode = value;
            return this;
        }        
        
        public DialogOption build() {
            if (null == text || text.isEmpty() || null == followUpNode) {
                throw new NotAllPropertiesAreSetException();    
            }
            
            return new DialogOption(this);
        }
    }
    
    //</editor-fold>
}