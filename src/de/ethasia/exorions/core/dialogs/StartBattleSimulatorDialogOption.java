package de.ethasia.exorions.core.dialogs;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;

public class StartBattleSimulatorDialogOption implements DialogOption {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String text;
    
    @Override
    public String getText() {
        return text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    
    private StartBattleSimulatorDialogOption(Builder builder) {
        text = builder.text;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Implementations">
    
    @Override
    public boolean endsCurrentDialog() {
        return true;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String text;
        
        public Builder setText(String value) {
            text = value;
            return this;
        }
        
        public StartBattleSimulatorDialogOption build() {
            if (null == text || text.isEmpty()) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new StartBattleSimulatorDialogOption(this);
        }
    }
    
    //</editor-fold>
}