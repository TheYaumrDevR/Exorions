package de.ethasia.exorions.ioadapters.gateways.tests;

import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.crosslayer.MapDataCouldNotBeLoadedException;
import de.ethasia.exorions.interactors.crosslayer.MapTileDataMalformedException;
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
    
    @Test
    public void testFindFloorTileDefinitions_dependenciesAreCalled() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
        
        assertThat(MapsMock.getLastReadMapLogicPath(), is(equalTo("test floors")));
    }
    
    @Test
    public void testFindFloorTileDefinitions_sourceXmlIsCorrect_resultHasCorrectProperties() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        DefinitionsForUndistinguishableMapTiles result = testCandidate.findFloorTileDefinitions("test floors");
        DefinitionsForUndistinguishableMapTiles expected = new DefinitionsForUndistinguishableMapTiles();
        expected.addNewDefinitionWidthLengthHeightXyz(4, 5, 1, 1, 0, 1);
        
        assertThat(expected, is(equalTo(result)));
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_widthAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }    
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_lengthAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_xAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_yAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_zAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    } 
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_widthAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"ac\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }  
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_lengthAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"gz\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_xAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"svre\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_yAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"ggs4w2\" z=\"1\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindFloorTileDefinitions_zAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"3123.0\"/>\n" +
                    "</floors>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findFloorTileDefinitions("test floors");
    }    
    
    @Test
    public void testFindCollisionTileDefinitions_dependenciesAreCalled() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
        
        assertThat(MapsMock.getLastReadMapLogicPath(), is(equalTo("test collisions")));
    }
    
    @Test
    public void testFindCollisionTileDefinitions_sourceXmlIsCorrect_resultHasCorrectProperties() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        DefinitionsForUndistinguishableMapTiles result = testCandidate.findCollisionTileDefinitions("test collisions");
        DefinitionsForUndistinguishableMapTiles expected = new DefinitionsForUndistinguishableMapTiles();
        expected.addNewDefinitionWidthLengthHeightXyz(3, 1, 1, 1, 0, 1);
        expected.addNewDefinitionWidthLengthHeightXyz(1, 1, 1, 3, 0, 2);
        
        assertThat(expected, is(equalTo(result)));
    }   
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_widthAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }    
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_lengthAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_heightAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }    

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_xAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_yAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_zAttributeIsNotPresent_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" />\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_widthAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"nw\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }  
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_lengthAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"hjf\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_heightAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"0.9\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }    

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_xAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"iuö\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_yAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"ccdsa\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testFindCollisionTileDefinitions_zAttributeHasIncorrectValue_throwsException() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\".l\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(xml);
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.findCollisionTileDefinitions("test collisions");
    }    
    
    @Test
    public void testGetMapDimensionX_dimensionIsPresent_isReadProperly() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        int mapDimensionX = testCandidate.getMapDimensionX("mapDefinitionPath");
        
        assertThat(mapDimensionX, is(6));
    }
    
    @Test
    public void testGetMapDimensionX_callsMapInterfaceWithMapPath() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionX("mapDefinitionPath");
        
        assertThat(MapsMock.getLastReadMapLogicPath(), is(equalTo("mapDefinitionPath")));
    }  
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testGetMapDimensionX_mapNodeIsNotPresent_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<noMap dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</noMap>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionX("mapDefinitionPath");        
    }    
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testGetMapDimensionX_dimensionIsNotPresent_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionX("mapDefinitionPath");
    }

    @Test(expected = MapTileDataMalformedException.class)
    public void testGetMapDimensionX_dimensionDefinitionIsMalformed_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6asd\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionX("mapDefinitionPath");
    }      
    
    @Test
    public void testGetMapDimensionZ_dimensionIsPresent_isReadProperly() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        int mapDimensionZ = testCandidate.getMapDimensionZ("mapDefinitionPath");
        
        assertThat(mapDimensionZ, is(7));
    }
    
    @Test
    public void testGetMapDimensionZ_callsMapInterfaceWithMapPath() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionZ("mapDefinitionPath");
        
        assertThat(MapsMock.getLastReadMapLogicPath(), is(equalTo("mapDefinitionPath")));
    } 
    
    @Test(expected = MapDataCouldNotBeLoadedException.class)
    public void testGetMapDimensionZ_mapNodeIsNotPresent_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<noMap dimX=\"6\" dimZ=\"7\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</noMap>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionZ("mapDefinitionPath");        
    }    
    
    @Test(expected = MapTileDataMalformedException.class)
    public void testGetMapDimensionZ_dimensionIsNotPresent_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionZ("mapDefinitionPath");
    }    

    @Test(expected = MapTileDataMalformedException.class)
    public void testGetMapDimensionZ_dimensionDefinitionIsMalformed_throwsException() {
        String mapLogicXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<map dimX=\"6\" dimZ=\"fasdg\">\n" +
                    "<floors>\n" +
                        "<f w=\"4\" l=\"5\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                    "</floors>\n" +
                    "<collisions>\n" +
                        "<c w=\"3\" l=\"1\" h=\"1\" x=\"1\" y=\"0\" z=\"1\"/>\n" +
                        "<c w=\"1\" l=\"1\" h=\"1\" x=\"3\" y=\"0\" z=\"2\"/>\n" +
                    "</collisions>\n" +
                "</map>";
        MapsMock.setMapLogicXml(mapLogicXml);
        
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        testCandidate.getMapDimensionZ("mapDefinitionPath");
    }    
    
    @Test
    public void testCaching_worksAsExpected() {
        MapsMock.setMapLogicXml(getStandardMapLogicXml());
        MapDefinitionsGatewayImpl testCandidate = new MapDefinitionsGatewayImpl();
        
        DefinitionsForUndistinguishableMapTiles floorTileDefinitions = testCandidate.findFloorTileDefinitions("mapDefinitionPath");
        DefinitionsForUndistinguishableMapTiles collisionTileDefinitions = testCandidate.findCollisionTileDefinitions("mapDefinitionPath");
        int mapDimensionX = testCandidate.getMapDimensionX("mapDefinitionPath");
        int mapDimensionZ = testCandidate.getMapDimensionZ("mapDefinitionPath");
        
        DefinitionsForUndistinguishableMapTiles expectedFloors = new DefinitionsForUndistinguishableMapTiles();
        expectedFloors.addNewDefinitionWidthLengthHeightXyz(4, 5, 1, 1, 0, 1);   
        
        DefinitionsForUndistinguishableMapTiles expectedCollisions = new DefinitionsForUndistinguishableMapTiles();
        expectedCollisions.addNewDefinitionWidthLengthHeightXyz(3, 1, 1, 1, 0, 1);      
        expectedCollisions.addNewDefinitionWidthLengthHeightXyz(1, 1, 1, 3, 0, 2); 
        
        DefinitionsForUndistinguishableMapTiles floorTileDefinitionsAfterCacheReset = testCandidate.findFloorTileDefinitions("cacheReset");
        
        assertThat(mapDimensionX, is(6));
        assertThat(mapDimensionZ, is(7));
        assertThat(expectedFloors, is(equalTo(floorTileDefinitions)));
        assertThat(expectedCollisions, is(equalTo(collisionTileDefinitions)));
        assertThat(expectedFloors, is(equalTo(floorTileDefinitionsAfterCacheReset)));
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
                    "</collisions>\n" +
                "</map>";
    }
    
    //</editor-fold>
}