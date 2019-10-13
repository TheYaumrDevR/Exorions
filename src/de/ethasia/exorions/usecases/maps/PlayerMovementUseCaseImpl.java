package de.ethasia.exorions.usecases.maps;

import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerAvatarMovementPresenter;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerMovementUseCase;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;

public class PlayerMovementUseCaseImpl implements PlayerMovementUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final PlayerAvatarMovementPresenter playerAvatarMovementPresenter;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public PlayerMovementUseCaseImpl() {
        playerAvatarMovementPresenter = PresentersFactory.getInstance().createPlayerAvatarMovementPresenter();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PlayerMovementUseCase Overrides">
    
    @Override
    public void moveDown() {
        Player player = Player.getInstance();
        
        if (player.isBusy()) {
            return;
        }
        
        if (player.willMoveTo(MoveDirections.DOWN)) {
            player.moveTo(MoveDirections.DOWN);
            playerAvatarMovementPresenter.movePlayerAvatarDown(player.getPositionX(), player.getPositionY(), player.getPositionZ());            
        } else {
            playerAvatarMovementPresenter.stepDownWithoutMoving();
        }
    }
    
    @Override
    public void moveRight() {
        Player player = Player.getInstance();
        
        if (player.isBusy()) {
            return;
        }
        
        if (player.willMoveTo(MoveDirections.RIGHT)) {
            player.moveTo(MoveDirections.RIGHT);
            playerAvatarMovementPresenter.movePlayerAvatarRight(player.getPositionX(), player.getPositionY(), player.getPositionZ());            
        } else {
            playerAvatarMovementPresenter.stepRightWithoutMoving();
        }
    }    
    
    @Override
    public void moveUp() {
        Player player = Player.getInstance();
        
        if (player.isBusy()) {
            return;
        }
        
        if (player.willMoveTo(MoveDirections.UP)) {
            player.moveTo(MoveDirections.UP);
            playerAvatarMovementPresenter.movePlayerAvatarUp(player.getPositionX(), player.getPositionY(), player.getPositionZ());            
        } else {
            playerAvatarMovementPresenter.stepUpWithoutMoving();
        }
    }
    
    @Override
    public void moveLeft() {
        Player player = Player.getInstance();
        
        if (player.isBusy()) {
            return;
        }
        
        if (player.willMoveTo(MoveDirections.LEFT)) {
            player.moveTo(MoveDirections.LEFT);
            playerAvatarMovementPresenter.movePlayerAvatarLeft(player.getPositionX(), player.getPositionY(), player.getPositionZ());            
        } else {
            playerAvatarMovementPresenter.stepLeftWithoutMoving();
        }
    }    
    
    //</editor-fold>
}