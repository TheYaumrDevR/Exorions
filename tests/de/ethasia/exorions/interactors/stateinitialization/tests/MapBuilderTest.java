package de.ethasia.exorions.interactors.stateinitialization.tests;

import de.ethasia.exorions.core.maps.InteriorMap;
import de.ethasia.exorions.interactors.crosslayer.DefinitionsForUndistinguishableMapTiles;
import de.ethasia.exorions.interactors.stateinitialization.MapBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class MapBuilderTest {
    
    @Test
    public void testBuild_setCollisionDefinitions_createdMapHasCorrectCollisionTilePositions() {
        DefinitionsForUndistinguishableMapTiles definitions = new DefinitionsForUndistinguishableMapTiles();
        definitions.addNewDefinitionWidthLengthHeightXyz(3, 1, 1, 1, 0, 1);
        definitions.addNewDefinitionWidthLengthHeightXyz(1, 1, 5, 3, 0, 2);
        
        MapBuilder testCandidate = new MapBuilder();
        InteriorMap result = testCandidate
                .withDimensions((short)6, (short)7)
                .withCollisionDefinitions(definitions)
                .build();
        
        assertThat(result, is(not(nullValue())));
        assertThat(result.tileAtIsColliding((short)1, (short)0, (short)2), is(false));
        assertThat(result.tileAtIsColliding((short)1, (short)0, (short)1), is(true));
        assertThat(result.tileAtIsColliding((short)2, (short)0, (short)1), is(true));
        assertThat(result.tileAtIsColliding((short)3, (short)0, (short)1), is(true));
        assertThat(result.tileAtIsColliding((short)3, (short)4, (short)2), is(true));
        assertThat(result.tileAtIsColliding((short)3, (short)5, (short)2), is(false));
    }
    
    @Test
    public void testBuild_setFloorDefinitions_createdMapHasCorrectFloorDefinitions() {
        DefinitionsForUndistinguishableMapTiles definitions = new DefinitionsForUndistinguishableMapTiles();
        definitions.addNewDefinitionWidthLengthHeightXyz(4, 5, 1, 1, 0, 1);
        
        MapBuilder testCandidate = new MapBuilder();
        InteriorMap result = testCandidate
                .withDimensions((short)6, (short)7)
                .withFloorDefinitions(definitions)
                .build();   
        
        assertThat(result, is(not(nullValue())));
        assertThat(result.tileAtIsGround((short)1, (short)0, (short)1), is(true));
        assertThat(result.tileAtIsGround((short)2, (short)0, (short)1), is(true));
        assertThat(result.tileAtIsGround((short)2, (short)0, (short)2), is(true));
        assertThat(result.tileAtIsGround((short)2, (short)1, (short)2), is(false));
        assertThat(result.tileAtIsGround((short)5, (short)0, (short)1), is(false));
        assertThat(result.tileAtIsGround((short)1, (short)0, (short)5), is(true));
        assertThat(result.tileAtIsGround((short)1, (short)0, (short)6), is(false));
    }
}