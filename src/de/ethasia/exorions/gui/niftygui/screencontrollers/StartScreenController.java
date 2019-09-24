package de.ethasia.exorions.gui.niftygui.screencontrollers;

import de.ethasia.exorions.gui.niftygui.NiftyGuiScreens;
import de.ethasia.exorions.ioadapters.presenters.GuiScreens;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class StartScreenController implements ScreenController {
    
    //<editor-fold defaultstate="collapsed" desc="Control IDs">
    
    private static final String NEW_GAME_BUTTON_ID = "#newGameButton";
    private static final String LOAD_GAME_BUTTON_ID = "#loadGameButton";
    private static final String OPTIONS_BUTTON_ID = "#openOptionsButton";
    private static final String CREDITS_BUTTON_ID = "#openCreditsButton";
    private static final String QUIT_GAME_BUTTON_ID = "#quitButton";
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private Button loadGameButton;
    private Button optionsButton;
    private Button creditsButton;
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="ScreenController Implementations">
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        loadGameButton = screen.findNiftyControl(LOAD_GAME_BUTTON_ID, Button.class);
        optionsButton = screen.findNiftyControl(OPTIONS_BUTTON_ID, Button.class);
        creditsButton = screen.findNiftyControl(CREDITS_BUTTON_ID, Button.class);
    }

    @Override
    public void onStartScreen() {
        if (null != loadGameButton) {
            loadGameButton.setEnabled(false);
        }
        
        if (null != optionsButton) {
            optionsButton.setEnabled(false);
        } 
        
        if (null != creditsButton) {
            creditsButton.setEnabled(false);
        }          
    }

    @Override
    public void onEndScreen() {
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Click Handlers">
    
    public void onQuitButtonClicked() {
        System.exit(0);
    }
    
    public void onNewGameButtonClicked() {
        NiftyGuiScreens.gotoScreen(GuiScreens.OVERWORLD);
    }
    
    //</editor-fold>
}