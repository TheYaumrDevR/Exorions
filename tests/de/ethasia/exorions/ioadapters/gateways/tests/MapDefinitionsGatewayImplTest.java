package de.ethasia.exorions.ioadapters.gateways.tests;

import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.stateinitialization.MapMetaData;
import de.ethasia.exorions.ioadapters.crosslayer.TechnicalsFactory;
import de.ethasia.exorions.ioadapters.gateways.MapDefinitionsGatewayImpl;
import de.ethasia.exorions.ioadapters.mocks.MapsMock;
import de.ethasia.exorions.ioadapters.mocks.MockTechnicalsFactory;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class MapDefinitionsGatewayImplTest {
    
    @BeforeClass
    public static void initDependencies() {
        TechnicalsFactory.setInstance(new MockTechnicalsFactory());
    }
    
    @Before
    public void resetSharedState() {
        MapsMock.resetMock();
    }
    
    @Test
    public void testTryToRetrieveMetaDataForNewGameMap_dependenciesAreCalled() {
        MapsMock.setMapListXml(getStandardMapListXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();
        
        assertThat(MapsMock.getReadMapListCallCount(), is(1));
    }
    
    @Test
    public void testTryToRetrieveMetaDataForNewGameMap_xmlIsCorrect_startGameMapMetadataIsRead() {
        MapsMock.setMapListXml(getStandardMapListXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        MapMetaData result = testCandidate.tryToRetrieveMetaDataForNewGameMap();

        assertThat(result.getMapName(), is(equalTo("Player Starting Room")));
        assertThat(result.getLogicFilePath(), is(equalTo("assets/Maps/Interior/StartingRoom.xml")));
        assertThat(result.getVisualFilePath(), is(equalTo("Scenes/Player Room/PlayerRoom.j3o")));
    }
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_newGameMapNotFound_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    }
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_mapNameIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    }

    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_visualsPathIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"Player Starting Room\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    } 

    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_logicInformationPathIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    }

    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_mapNameIsEmpty_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    } 
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_visualsPathIsEmpty_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"Player Starting Room\" visuals=\"\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    } 
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testTryToRetrieveMetaDataForNewGameMap_logicInformationPathIsEmpty_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
        MapsMock.setMapListXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.tryToRetrieveMetaDataForNewGameMap();        
    }    
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    
    private String getStandardMapListXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<maps>\n" +
                    "<interiors>\n" +
                        "<newGameMap name=\"Player Starting Room\" visuals=\"Scenes/Player Room/PlayerRoom.j3o\" logic=\"assets/Maps/Interior/StartingRoom.xml\"/>\n" +
                    "</interiors>\n" +
                "</maps>";
    }
    
    private String getStandardMapLogicXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                        "<c w=\"2\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"4\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"4\" y=\"0\" z=\"3\"/>\n" +
                        "<c w=\"4\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"0\"/>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"2\" y=\"0\" z=\"5\"/>\n" +
                        "<c w=\"1\" l=\"5\" h=\"1\" x=\"0\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"4\" h=\"1\" x=\"5\" y=\"0\" z=\"1\"/>\n" +
                    "</collisions>\n" +
                "</map>";
    }
    
    //</editor-fold>
}