package de.ethasia.exorions.ioadapters.presenters;

import de.ethasia.exorions.interactors.crosslayer.PlayerAvatarMovementPresenter;
import de.ethasia.exorions.ioadapters.crosslayer.SoundEffects;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.technical.engine.PlayerCharacterAvatar;

public class PlayerAvatarMovementPresenterImpl implements PlayerAvatarMovementPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private final PlayerCharacterAvatar playerCharacterVisual;
    private final SoundEffects soundEffects;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public PlayerAvatarMovementPresenterImpl() {
        playerCharacterVisual = PlayerCharacterAvatar.getCurrentInstance();
        soundEffects = TechnicalsFactory.getInstance().createSoundEffects();
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PlayerAvatarMovementPresenter Overrides">
    
    @Override
    public void movePlayerAvatarDown(short newX, short newY, short newZ) {
        setupPositionCorrectionForPlayerCharacterVisual(newX, newY, newZ);
        
        if (null != playerCharacterVisual) {
            playerCharacterVisual.moveDown();
        }
    }

    @Override
    public void movePlayerAvatarRight(short newX, short newY, short newZ) {
        setupPositionCorrectionForPlayerCharacterVisual(newX, newY, newZ);
        
        if (null != playerCharacterVisual) {
            playerCharacterVisual.moveRight();
        }        
    }

    @Override
    public void movePlayerAvatarUp(short newX, short newY, short newZ) {
        setupPositionCorrectionForPlayerCharacterVisual(newX, newY, newZ);
        
        if (null != playerCharacterVisual) {
            playerCharacterVisual.moveUp();
        }        
    }

    @Override
    public void movePlayerAvatarLeft(short newX, short newY, short newZ) {
        setupPositionCorrectionForPlayerCharacterVisual(newX, newY, newZ);
        
        if (null != playerCharacterVisual) {
            playerCharacterVisual.moveLeft();
        }        
    }

    @Override
    public void stepDownWithoutMoving() {
        if (null != playerCharacterVisual) {
            playerCharacterVisual.showStepDownAnimation();
        }
        
        playCollisionSoundEffect();   
    }

    @Override
    public void stepRightWithoutMoving() {
        if (null != playerCharacterVisual) {
            playerCharacterVisual.showStepRightAnimation();
        }
        
        playCollisionSoundEffect();          
    }

    @Override
    public void stepUpWithoutMoving() {
        if (null != playerCharacterVisual) {
            playerCharacterVisual.showStepUpAnimation();
        }
        
        playCollisionSoundEffect();         
    }

    @Override
    public void stepLeftWithoutMoving() {
        if (null != playerCharacterVisual) {
            playerCharacterVisual.showStepLeftAnimation();
        }
        
        playCollisionSoundEffect();      
    }  
    
    @Override
    public void teleportTo(short newX, short newY, short newZ) {
        float engineX = transformTilePositionToJmePosition(newX);
        float engineY = transformTilePositionToJmePosition(newY);
        float engineZ = transformTilePositionToJmePosition(newZ);
        
        if (null != playerCharacterVisual) {
            playerCharacterVisual.teleportTo(engineX, engineY, engineZ);
        } else {
             PlayerCharacterAvatar.Builder.getCurrentInstance()
                    .setPositionX(engineX)
                    .setPositionY(engineY)
                    .setPositionZ(engineZ);
        }      
    }
    
    @Override
    public boolean canShowNextMovement() {
        if (null != playerCharacterVisual) {
            return !playerCharacterVisual.isShowingAnimation();
        }
        
        return true;
    }    
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private void setupPositionCorrectionForPlayerCharacterVisual(int newPosX, int newPosY, int newPosZ) {
        if (null != playerCharacterVisual) {
            playerCharacterVisual.setPositionToApplyAfterNextMovementForVisualCorrection(transformTilePositionToJmePosition(newPosX), 
                    transformTilePositionToJmePosition(newPosY), 
                    transformTilePositionToJmePosition(newPosZ));             
        }       
    }
    
    private float transformTilePositionToJmePosition(int tilePosition) {
        return tilePosition * 0.8f;
    }
    
    private void playCollisionSoundEffect() {
        if (null != soundEffects) {
            soundEffects.playCollisionSoundEffect();
        }        
    }
    
    //</editor-fold>
}