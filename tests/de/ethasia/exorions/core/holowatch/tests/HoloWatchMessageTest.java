package de.ethasia.exorions.core.holowatch.tests;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import de.ethasia.exorions.core.holowatch.HoloWatchMessage;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HoloWatchMessageTest {
    
    @Test
    public void testBuilderBuild_fieldsAreInProduct() {
        Date dateTimeReceived = new Date();
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product, is(notNullValue()));
        assertThat(product.getTitle(), is(equalTo("Foo")));
        assertThat(product.getMessageText(), is(equalTo("Lorem ipsum bar")));
        assertThat(product.getSender(), is(equalTo("Random Guy")));
        assertThat(product.getDateTimeReceived(), is(dateTimeReceived));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_titleIsNotSet_throwsException() {
        Date dateTimeReceived = new Date();
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product, is(notNullValue()));
    }
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_messageTextIsNotSet_throwsException() {
        Date dateTimeReceived = new Date();
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product, is(notNullValue()));
    }  
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_senderIsNotSet_throwsException() {
        Date dateTimeReceived = new Date();
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product, is(notNullValue()));
    }  
    
    @Test(expected = NotAllPropertiesAreSetException.class)
    public void testBuilderBuild_dateTimeReceivedIsNotSet_throwsException() {
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .build();
        
        assertThat(product, is(notNullValue()));
    }     
}