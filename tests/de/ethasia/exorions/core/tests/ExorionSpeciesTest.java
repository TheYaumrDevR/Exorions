package de.ethasia.exorions.core.tests;

import de.ethasia.exorions.core.AbilityLearningRequirements;
import de.ethasia.exorions.core.ExorionSpecies;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ExorionSpeciesTest {

    @Test
    public void testBuilderBuild_nothingIsSet_emptySpeciesIsCreated() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate.build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test
    public void testBuilderBuild_exorionNameIsSet_nameIsContainedInProduct() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate.setName("Fookachu").build();
        
        assertThat(product.getName(), is(equalTo("Fookachu")));
    }
    
    @Test
    public void testBuilderBuild_learningRequirementsAreSet_theyAreContainedInProduct() {
        ExorionSpecies.Builder testCandidate = new ExorionSpecies.Builder();
        
        ExorionSpecies product = testCandidate
            .setFulfilledLearningRequirements(AbilityLearningRequirements.TAIL)
            .setFulfilledLearningRequirements(AbilityLearningRequirements.LOCOMOTION)
            .build();
        Set<AbilityLearningRequirements> fulfilledLearningRequirements = product.getFulfilledLearningRequirements();
        
        assertThat(fulfilledLearningRequirements, hasItems(AbilityLearningRequirements.TAIL, AbilityLearningRequirements.LOCOMOTION));
    }  
}
