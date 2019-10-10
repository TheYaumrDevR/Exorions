package de.ethasia.exorions.usecases.maps;

import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.core.maps.Player;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerAvatarMovementPresenter;
import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerMovementUseCase;
import de.ethasia.exorions.usecases.interfaces.PresentersFactory;

public class PlayerMovementUseCaseImpl implements PlayerMovementUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final PlayerAvatarMovementPresenter playerAvatarMovementUc;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public PlayerMovementUseCaseImpl() {
        playerAvatarMovementUc = PresentersFactory.getInstance().createPlayerAvatarMovementPresenter();
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PlayerMovementUseCase Overrides">
    
    @Override
    public void moveDown() {
        Player player = Player.getInstance();
        player.moveTo(MoveDirections.DOWN);
        playerAvatarMovementUc.movePlayerAvatarDown(player.getPositionX(), player.getPositionY(), player.getPositionZ());
    }
    
    @Override
    public void moveRight() {
        Player player = Player.getInstance();
        player.moveTo(MoveDirections.RIGHT);
        playerAvatarMovementUc.movePlayerAvatarRight(player.getPositionX(), player.getPositionY(), player.getPositionZ());
    }    
    
    @Override
    public void moveUp() {
        Player player = Player.getInstance();
        player.moveTo(MoveDirections.UP);
        playerAvatarMovementUc.movePlayerAvatarUp(player.getPositionX(), player.getPositionY(), player.getPositionZ());
    }
    
    @Override
    public void moveLeft() {
        Player player = Player.getInstance();
        player.moveTo(MoveDirections.LEFT);
        playerAvatarMovementUc.movePlayerAvatarLeft(player.getPositionX(), player.getPositionY(), player.getPositionZ());
    }    
    
    //</editor-fold>
}