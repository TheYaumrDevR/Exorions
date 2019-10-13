package de.ethasia.exorions.core.dialogs;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

import java.util.LinkedList;
import java.util.List;

public class DialogWithOptions implements DialogNode {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String text;
    
    @Override
    public String getText() {
        return text;
    }
    
    private final List<DialogOption> options;
    public List<DialogOption> getOptions() {
        return options;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private DialogWithOptions(Builder builder) {
        text = builder.text;
        options = builder.dialogOptions;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean isLeaf() {
        return false;
    }    
    
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String text;
        private final List<DialogOption> dialogOptions;
        
        public Builder() {
            dialogOptions = new LinkedList<>();
        }
        
        public Builder setText(String value) {
            text = value;
            return this;
        }
        
        public Builder addDialogOption(DialogOption value) {
            dialogOptions.add(value);
            return this;
        }        
        
        public DialogWithOptions build() {
            if (dialogOptions.size() < 2) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new DialogWithOptions(this);
        }
    }
    
    //</editor-fold>
}