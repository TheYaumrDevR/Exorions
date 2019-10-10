package de.ethasia.exorions.usecases.mocks;

import de.ethasia.exorions.usecases.crosslayerinterfaces.PlayerAvatarMovementPresenter;

public class PlayerAvatarMovementPresenterMock implements PlayerAvatarMovementPresenter {
    
    //<editor-fold defaultstate="collapsed" desc="Mock Properties">
    
    private static int lastPosX;
    public static int getLastPosX() {
        return lastPosX;
    }
    
    private static int lastPosY;
    public static int getLastPosY() {
        return lastPosY;
    }
    
    private static int lastPosZ;
    public static int getLastPosZ() {
        return lastPosZ;
    }
    
    private static int moveDownCallCount;
    public static int getMoveDownCallCount() {
        return moveDownCallCount;
    }
    
    private static int moveRightCallCount;
    public static int getMoveRightCallCount() {
        return moveRightCallCount;
    }
    
    private static int moveUpCallCount;
    public static int getMoveUpCallCount() {
        return moveUpCallCount;
    }
    
    private static int moveLeftCallCount;
    public static int getMoveLeftCallCount() {
        return moveLeftCallCount;
    }
    
    private static int stepDownWithoutMovingCallCount;
    public static int getStepDownWithoutMovingCallCount() {
        return stepDownWithoutMovingCallCount;
    }
    
    private static int stepRightWithoutMovingCallCount;
    public static int getStepRightWithoutMovingCallCount() {
        return stepRightWithoutMovingCallCount;
    }
    
    private static int stepUpWithoutMovingCallCount;
    public static int getStepUpWithoutMovingCallCount() {
        return stepUpWithoutMovingCallCount;
    }
    
    private static int stepLeftWithoutMovingCallCount;
    public static int getStepLeftWithoutMovingCallCount() {
        return stepLeftWithoutMovingCallCount;
    }
    
    public static void resetCallData() {
        lastPosX = 0;
        lastPosY = 0;
        lastPosZ = 0;
        moveDownCallCount = 0;
        moveRightCallCount = 0;
        moveUpCallCount = 0;
        moveLeftCallCount = 0;
        stepDownWithoutMovingCallCount = 0;
        stepRightWithoutMovingCallCount = 0;
        stepUpWithoutMovingCallCount = 0;
        stepLeftWithoutMovingCallCount = 0;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PlayerAvatarMovementPresenterOverrides">
    
    @Override
    public void movePlayerAvatarDown(short newX, short newY, short newZ) {
        lastPosX = newX;
        lastPosY = newY;
        lastPosZ = newZ;
        moveDownCallCount++;
    }

    @Override
    public void movePlayerAvatarRight(short newX, short newY, short newZ) {
        lastPosX = newX;
        lastPosY = newY;
        lastPosZ = newZ;
        moveRightCallCount++;
    }

    @Override
    public void movePlayerAvatarUp(short newX, short newY, short newZ) {
        lastPosX = newX;
        lastPosY = newY;
        lastPosZ = newZ;
        moveUpCallCount++;
    }

    @Override
    public void movePlayerAvatarLeft(short newX, short newY, short newZ) {
        lastPosX = newX;
        lastPosY = newY;
        lastPosZ = newZ;
        moveLeftCallCount++;
    }

    @Override
    public void stepDownWithoutMoving() {
        stepDownWithoutMovingCallCount++;
    }

    @Override
    public void stepRightWithoutMoving() {
        stepRightWithoutMovingCallCount++;
    }

    @Override
    public void stepUpWithoutMoving() {
        stepUpWithoutMovingCallCount++;
    }

    @Override
    public void stepLeftWithoutMoving() {
        stepLeftWithoutMovingCallCount++;
    }    
    
    //</editor-fold>
}