package de.ethasia.exorions.interactors.mocks;

import de.ethasia.exorions.interactors.crosslayer.PlayerAvatarMovementPresenter;

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
    
    private static int lastTeleportToParameterX;
    public static int getLastTeleportToParameterX() {
        return lastTeleportToParameterX;
    }    
    
    private static int lastTeleportToParameterY;
    public static int getLastTeleportToParameterY() {
        return lastTeleportToParameterY;
    }      
    
    private static int lastTeleportToParameterZ;
    public static int getLastTeleportToParameterZ() {
        return lastTeleportToParameterZ;
    }      
    
    private static boolean canShowNextMovementValueToReturn;
    public static void setCanShowNextMovementValueToReturn(boolean value) {
        canShowNextMovementValueToReturn = value;
    }
    
    public static void resetCallData() {
        lastPosX = 0;
        lastPosY = 0;
        lastPosZ = 0;
        lastTeleportToParameterX = 0;
        lastTeleportToParameterY = 0;
        lastTeleportToParameterZ = 0;
        moveDownCallCount = 0;
        moveRightCallCount = 0;
        moveUpCallCount = 0;
        moveLeftCallCount = 0;
        stepDownWithoutMovingCallCount = 0;
        stepRightWithoutMovingCallCount = 0;
        stepUpWithoutMovingCallCount = 0;
        stepLeftWithoutMovingCallCount = 0;
        canShowNextMovementValueToReturn = true;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PlayerAvatarMovementPresenter Overrides">
    
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
    
    @Override
    public boolean canShowNextMovement() {
        return canShowNextMovementValueToReturn;
    }    
    
    @Override
    public void teleportTo(short newX, short newY, short newZ) {
        lastTeleportToParameterX = newX;
        lastTeleportToParameterY = newY;
        lastTeleportToParameterZ = newZ;
    }    
    
    //</editor-fold>
}