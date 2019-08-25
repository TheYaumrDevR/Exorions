package de.ethasia.exorions.core.quests.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.quests.Quest;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuestTests {

    @Test
    public void testBuild_setTitleCurrentDescription_valuesAreInProduct() {
        Quest.Builder questBuilder = new Quest.Builder();
        Quest product = questBuilder.setTitle("First Quest")
                .setCurrentDescription("This is a test description for the first quest.")
                .build();
        
        assertThat(product, is(not(nullValue())));
        assertThat(product.getTitle(), is(equalTo("First Quest")));
        assertThat(product.getCurrentDescription(), is(equalTo("This is a test description for the first quest.")));
    } 
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuild_titleIsNotSet_throwsException() {
        Quest.Builder questBuilder = new Quest.Builder();
        Quest product = questBuilder.setCurrentDescription("This is a test description for the first quest.")
                .build();        
    } 

    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuild_currentDescriptionIsNotSet_throwsException() {
        Quest.Builder questBuilder = new Quest.Builder();
        Quest product = questBuilder.setTitle("First Quest")
                .build();        
    }     
}
