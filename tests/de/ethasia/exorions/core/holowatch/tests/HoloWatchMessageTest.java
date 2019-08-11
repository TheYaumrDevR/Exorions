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
    
    @Test
    public void testBuilderBuild_titleIsNotSet_titleIsDefaultValue() {
        Date dateTimeReceived = new Date();
        String titleDefaultValue = "(No Title)";
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setMessageText("Lorem ipsum bar")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product.getTitle(), is(equalTo(titleDefaultValue)));
        assertThat(product.getSender(), is(equalTo("Random Guy")));
        assertThat(product.getMessageText(), is(equalTo("Lorem ipsum bar")));
        assertThat(product.getDateTimeReceived(), is(dateTimeReceived));
    }
    
    @Test
    public void testBuilderBuild_messageTextIsNotSet_messageTextIsEmpty() {
        Date dateTimeReceived = new Date();
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setSender("Random Guy")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product.getTitle(), is(equalTo("Foo")));
        assertThat(product.getSender(), is(equalTo("Random Guy")));
        assertThat(product.getMessageText(), is(equalTo("")));
        assertThat(product.getDateTimeReceived(), is(dateTimeReceived));
    }  
    
    @Test
    public void testBuilderBuild_senderIsNotSet_senderIsDefaultValue() {
        Date dateTimeReceived = new Date();
        String defaultSender = "Unknown";
        
        HoloWatchMessage product = new HoloWatchMessage.Builder()
            .setTitle("Foo")
            .setMessageText("Lorem ipsum bar")
            .setDateTimeReceived(dateTimeReceived)
            .build();
        
        assertThat(product.getTitle(), is(equalTo("Foo")));
        assertThat(product.getSender(), is(equalTo(defaultSender)));
        assertThat(product.getMessageText(), is(equalTo("Lorem ipsum bar")));
        assertThat(product.getDateTimeReceived(), is(dateTimeReceived));
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