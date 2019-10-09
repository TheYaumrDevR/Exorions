package de.ethasia.exorions.ioadapters.presenters.tests;

import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.ioadapters.crosslayer.GameStatesFactory;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.ioadapters.mocks.AppStateManagerMock;
import de.ethasia.exorions.ioadapters.mocks.EngineMapDataBuilderMock;
import de.ethasia.exorions.ioadapters.mocks.MapsMock;
import de.ethasia.exorions.ioadapters.mocks.MockGameStatesFactory;
import de.ethasia.exorions.ioadapters.mocks.MockTechnicalsFactory;
import de.ethasia.exorions.ioadapters.presenters.OverworldStatePresenterImpl;
import de.ethasia.exorions.technical.jmegamestates.EvocriGameState;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OverworldStatePresenterImplTest {

    @BeforeClass
    public static void setupDependencies() {
        TechnicalsFactory.setInstance(new MockTechnicalsFactory());
        GameStatesFactory.setInstance(new MockGameStatesFactory());
        EvocriGameState.setStateManager(new AppStateManagerMock(null));
    }
    
    @Before
    public void resetSharedState() {
        MapsMock.resetMock();
        EngineMapDataBuilderMock.resetMockCounters();
    }
    
    @Test
    public void testPresentOverworldWithNewGameMap_startingMapDataIsPresent_initializesOverworldStateCorrectly() {        
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(MapsMock.getLastReadMapLogicPath(), is(equalTo("assets/Maps/Interior/StartingRoom.xml")));
        assertThat(MapsMock.getLastReadMapVisualsPath(), is(equalTo("Scenes/Player Room/PlayerRoom.j3o")));
        assertThat(EngineMapDataBuilderMock.getLastSetFloorWidth(), is(4));
        assertThat(EngineMapDataBuilderMock.getLastSetFloorDepth(), is(5));
        assertThat(EngineMapDataBuilderMock.getLastSetFloorX(), is(1));
        assertThat(EngineMapDataBuilderMock.getLastSetFloorY(), is(0));
        assertThat(EngineMapDataBuilderMock.getLastSetFloorZ(), is(1));
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(1));
    }
    
    @Test
    public void testPresentOverworldWithNewGameMap_twoFloorsAreDefined_newFloorIsCalledTwice() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"5\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(2));
    }
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testPresentOverworldWithNewGameMap_newGameMapIsNotPresent_throwsException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<map name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"5\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();    
    }
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testPresentOverworldWithNewGameMap_mapNameAttributeIsNotPresent_throwsCustomException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();    
    }
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testPresentOverworldWithNewGameMap_logicAttributeIsNotPresent_throwsCustomException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"5\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();    
    }   
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testPresentOverworldWithNewGameMap_visualsAttributeIsNotPresent_throwsCustomException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();    
    }
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorWidthAttributeMissing_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();    
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));
    }
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorLengthAttributeMissing_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    } 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorXPosAttributeMissing_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    } 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorYPosAttributeMissing_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    } 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorZPosAttributeMissing_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    }
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorWidthHasIncorrectType_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"a\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    }  
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorLengthHasIncorrectType_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"f\" x=\"1\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    } 
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorXPosAttributeHasIncorrectType_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"h\" y=\"0\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    }   
    
    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorYPosAttributeHasIncorrectType_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"i\" z=\"1\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    }

    @Test
    public void testPresentOverworldWithNewGameMap_mapFloorZPosAttributeHasIncorrectType_throwsNoException() {
        OverworldStatePresenterImpl testCandidate = new OverworldStatePresenterImpl();
        
        String mapListXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<maps>" +
                "<interiors>" +
                    "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>" +
                "</interiors>" +
            "</maps>";
        
        String startingMapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<map>" +
                "<floors>" +
                    "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"p\"/>" +
                "</floors>" +
                "<collisions>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"2\"/>" +
                    "<c w=\"2\" l=\"1\" h=\"0\" x=\"3\" y=\"0\" z=\"4\"/>" +
                    "<c w=\"1\" l=\"1\" h=\"0\" x=\"4\" y=\"0\" z=\"3\"/>" +
                    "<c w=\"4\" l=\"1\" h=\"0\" x=\"1\" y=\"0\" z=\"0\"/>" +
                    "<c w=\"3\" l=\"1\" h=\"0\" x=\"2\" y=\"0\" z=\"5\"/>" +
                    "<c w=\"1\" l=\"5\" h=\"0\" x=\"0\" y=\"0\" z=\"1\"/>" +
                    "<c w=\"1\" l=\"4\" h=\"0\" x=\"5\" y=\"0\" z=\"1\"/>" +
                "</collisions>" +
            "</map>";

        MapsMock.setMapListXml(mapListXml);
        MapsMock.setMapLogicXml(startingMapXml);
            
        testCandidate.presentOverworldWithNewGameMap();
        
        assertThat(EngineMapDataBuilderMock.getNewFloorCallCount(), is(0));        
    }    
}