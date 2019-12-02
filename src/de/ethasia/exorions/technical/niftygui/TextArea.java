package de.ethasia.exorions.technical.niftygui;

import de.lessvoid.nifty.controls.NiftyControl;

public interface TextArea extends NiftyControl {
    
    public void appendLine(String text);
    public void setAutoScroll(boolean value);
    public void clearText();
}