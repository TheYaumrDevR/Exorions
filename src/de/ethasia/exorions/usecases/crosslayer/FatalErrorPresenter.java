package de.ethasia.exorions.usecases.crosslayer;

public interface FatalErrorPresenter {
    
    public void showFatalError(String error, String cause, String stackTrace);
}