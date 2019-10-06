package de.ethasia.exorions.core.dialogs;

public interface DialogOption {
    
    public String getText();
    public boolean endsCurrentDialog();
    public DialogNode getFollowUpNode();
    public void executeAnyCustomUseCase();
}