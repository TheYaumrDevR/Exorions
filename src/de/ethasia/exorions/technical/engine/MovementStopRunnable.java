package de.ethasia.exorions.technical.engine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovementStopRunnable implements Runnable {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final ExecutorService MOVEMENT_STOP_THREAD_POOL = Executors.newFixedThreadPool(2);    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final long sleepTime;
    private final PlayerCharacterAvatar characterAvatar;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MovementStopRunnable(long sleepTime, PlayerCharacterAvatar characterAvatar) {
        this.sleepTime = sleepTime;
        this.characterAvatar = characterAvatar;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void startMovementStopTimer(long stopAfter, PlayerCharacterAvatar characterAvatar) {
        Runnable movementStopRunnable = new MovementStopRunnable(stopAfter, characterAvatar);
        MOVEMENT_STOP_THREAD_POOL.execute(movementStopRunnable);
    }
    
    //</editor-fold>    

    //<editor-fold defaultstate="collapsed" desc="Runnable Overrides">
    
    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime - 50);
        } catch (InterruptedException ex) {}

        characterAvatar.stopSpriteMovingAnimation();
        
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {} 
        
        characterAvatar.stopMoving();
    }    
    
    //</editor-fold>    
}