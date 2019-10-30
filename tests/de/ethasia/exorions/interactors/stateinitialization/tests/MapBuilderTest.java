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
    public void testBuild_createdMapHasCorrectProperties() {
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
}