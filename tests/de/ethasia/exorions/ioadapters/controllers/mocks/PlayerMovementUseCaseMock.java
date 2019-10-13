package de.ethasia.exorions.ioadapters.controllers.mocks;

import de.ethasia.exorions.interactors.crosslayer.PlayerMovementUseCase;

public class PlayerMovementUseCaseMock implements PlayerMovementUseCase {
    
    //<editor-fold defaultstate="collapsed" desc="Static Properties">
    
    private static int moveDownCallCount;
    private static int moveRightCallCount;
    private static int moveUpCallCount;
    private static int moveLeftCallCount;
    
    public static int getMoveDownCallCount() {
        return moveDownCallCount;
    }
    
    public static int getMoveRightCallCount() {
        return moveRightCallCount;
    }
    
    public static int getMoveUpCallCount() {
        return moveUpCallCount;
    }
    
    public static int getMoveLeftCallCount() {
        return moveLeftCallCount;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static void resetCallCounts() {
        moveDownCallCount = 0;
        moveRightCallCount = 0;
        moveUpCallCount = 0;
        moveLeftCallCount = 0;
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PlayerMovementUseCase Overrides">
    
    @Override
    public void moveDown() {
        moveDownCallCount++;
    }

    @Override
    public void moveRight() {
        moveRightCallCount++;
    }

    @Override
    public void moveUp() {
        moveUpCallCount++;
    }

    @Override
    public void moveLeft() {
        moveLeftCallCount++;
    }    
    
    //</editor-fold>    
}