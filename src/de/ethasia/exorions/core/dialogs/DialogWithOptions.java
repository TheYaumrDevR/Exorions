package de.ethasia.exorions.core.dialogs;

public class DialogWithOptions implements DialogNode {
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        public Builder setText(String value) {
            return this;
        }
        
        public Builder addDialogOption(DialogOption value) {
            return this;
        }        
        
        public DialogWithOptions build() {
            return new DialogWithOptions();
        }
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean isLeaf() {
        return false;
    }    
    
    //</editor-fold>
}