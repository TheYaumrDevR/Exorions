package de.ethasia.exorions.core.dialogs;

public class DialogEndnode implements DialogNode {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public DialogEndnode(String text) {
        
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Overrides">
    
    @Override
    public boolean isLeaf() {
        return true;
    }    
    
    //</editor-fold>
}