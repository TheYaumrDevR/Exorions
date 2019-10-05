package de.ethasia.exorions.ioadapters.mocks;

import com.jme3.app.Application;
import com.jme3.app.state.AppState;
import com.jme3.app.state.AppStateManager;

public class AppStateManagerMock extends AppStateManager {
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AppStateManagerMock(Application app) {
        super(app);
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="AppStateManager Overrides">
    
    @Override
    public boolean detach(AppState state) {
        return false;
    }
    
    @Override
    public boolean attach(AppState state) {
        return false;
    }
    
    //</editor-fold>
}