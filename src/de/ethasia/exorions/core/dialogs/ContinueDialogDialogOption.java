package de.ethasia.exorions.core.dialogs;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

public class ContinueDialogDialogOption implements DialogOption {
    
    //<editor-fold defaultstate="collapsed" desc="Accessors">
    
    private final DialogNode followUpNode;
    public DialogNode getFollowUpNode() {
        return followUpNode;
    }
    
    private final String text;
    
    @Override
    public String getText() {
        return text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private ContinueDialogDialogOption(Builder builder) {
        followUpNode = builder.followUpNode;
        text = builder.text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementation">
    
    @Override
    public boolean endsCurrentDialog() {
        return false;
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
        
        public ContinueDialogDialogOption build() {
            if (null == text || text.isEmpty() || null == followUpNode) {
                throw new NotAllPropertiesAreSetException();    
            }
            
            return new ContinueDialogDialogOption(this);
        }
    }
    
    //</editor-fold>
}