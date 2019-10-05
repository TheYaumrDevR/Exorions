package de.ethasia.exorions.ioadapters.presenters.tests;

import org.junit.Test;
import static org.junit.Assert.*;

public class OverworldStatePresenterImplTest {

    @Test
    public void testPresentOverworldWithNewGameMap_startingMapDataIsPresent_initializesOverworldStateCorrectly() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_newGameMapIsNotPresent_throwsException() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapNameAttributeIsNotPresent_throwsCustomException() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_logicAttributeIsNotPresent_throwsCustomException() {}   
    
    @Test
    public void testPresentOverworldWithNewGameMap_visualsAttributeIsNotPresent_throwsCustomException() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapNameIsEmpty_throwsCustomException() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFilePathIsEmpty_throwsCustomException() {} 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapScenePathIsEmpty_throwsCustomException() {} 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorAttributesAreMissing_throwsCustomException() {}
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorAttributesHaveIncorrectType_throwsCustomException() {}      
}