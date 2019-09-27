package de.ethasia.exorions.ioadapters.presenters.animations;

import de.ethasia.exorions.core.maps.MoveDirections;
import de.ethasia.exorions.ioadapters.presenters.ActionInCurrentStateNotPossibleException;

public class CharacterWalkingAnimation {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    
    private MoveDirections facingDirection;
    private boolean isWalking;
    private Steps currentStep;
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    public CharacterWalkingAnimation() {
        facingDirection = MoveDirections.DOWN;
        currentStep = Steps.RIGHT;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    public int getAnimationFrameId() {
        int offsetFromStandingFrame = getOffsetFromStandingFrame();
        
        switch (facingDirection) {
            case DOWN:
                return 0 + offsetFromStandingFrame;
            case RIGHT:
                return 3 + offsetFromStandingFrame;
            case UP:
                return 6 + offsetFromStandingFrame;
            case LEFT:
                return 9 + offsetFromStandingFrame;
        }
        
        return 0;
    }
    
    public void setFacingDirection(MoveDirections value) {
        if (isWalking) {
            throw new ActionInCurrentStateNotPossibleException("A character can only turn when standing.");
        }
        
        facingDirection = value;
    }
    
    public void startWalking() {
        if (isWalking) {
            throw new ActionInCurrentStateNotPossibleException("The character is already walking.");
        }
        
        isWalking = true;
        
        if (Steps.RIGHT == currentStep) {
            currentStep = Steps.LEFT;
        } else {
            currentStep = Steps.RIGHT;
        }
    }
    
    public void stopWalking() {
        isWalking = false;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private int getOffsetFromStandingFrame() {
        if (isWalking) {
            if (Steps.RIGHT == currentStep) {
                return 2;
            }
            
            return 1;
        }
        
        return 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Inner Classes">
    
    private enum Steps {
        LEFT,
        RIGHT
    }
    
    //</editor-fold>
}