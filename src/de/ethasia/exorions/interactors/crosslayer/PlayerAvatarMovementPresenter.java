package de.ethasia.exorions.interactors.crosslayer;

public interface PlayerAvatarMovementPresenter {
    
    public void movePlayerAvatarDown(short newX, short newY, short newZ);
    public void movePlayerAvatarRight(short newX, short newY, short newZ);
    public void movePlayerAvatarUp(short newX, short newY, short newZ);
    public void movePlayerAvatarLeft(short newX, short newY, short newZ);
    public void stepDownWithoutMoving();
    public void stepRightWithoutMoving();
    public void stepUpWithoutMoving();
    public void stepLeftWithoutMoving();
    public boolean canShowNextMovement();
}