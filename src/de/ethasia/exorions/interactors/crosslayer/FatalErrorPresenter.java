package de.ethasia.exorions.interactors.crosslayer;

public interface FatalErrorPresenter {
    
    public void showFatalError(String error, String cause, String stackTrace);
}