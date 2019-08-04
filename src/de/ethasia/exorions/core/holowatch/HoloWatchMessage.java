package de.ethasia.exorions.core.holowatch;

import de.ethasia.exorions.core.general.NotAllPropertiesAreSetException;
import java.util.Comparator;
import java.util.Date;

public class HoloWatchMessage {
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    
    private final String title;
    public String getTitle() {
        return title;
    }
    
    private final String messageText;
    public String getMessageText() {
        return messageText;
    }
    
    private final String sender;
    public String getSender() {
        return sender;
    }
    
    private final Date dateTimeReceived;
    public Date getDateTimeReceived() {
        return dateTimeReceived;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    
    private HoloWatchMessage(Builder builder) {
        title = builder.title;
        messageText = builder.messageText;
        sender = builder.sender;
        dateTimeReceived = builder.dateTimeReceived;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Static Methods">
    
    public static Comparator<HoloWatchMessage> getSortComparator() {
        return (HoloWatchMessage messageOne, HoloWatchMessage messageTwo) -> {
            if (messageOne.getDateTimeReceived().before(messageTwo.getDateTimeReceived())) {
                return 1;
            } else if (messageOne.getDateTimeReceived().after(messageTwo.getDateTimeReceived())) {
                return -1;
            }
            
            return 0;
        };
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Builder">
    
    public static class Builder {
        
        private String title;
        private String messageText;
        private String sender;
        private Date dateTimeReceived;
        
        public Builder setTitle(String value) {
            title = value;
            return this;
        }
        
        public Builder setMessageText(String value) {
            messageText = value;
            return this;
        }
        
        public Builder setSender(String value) {
            sender = value;
            return this;
        }
        
        public Builder setDateTimeReceived(Date value) {
            dateTimeReceived = value;
            return this;
        }
    
        public HoloWatchMessage build() {
            if (!allPropertiesAreSet()) {
                throw new NotAllPropertiesAreSetException();
            }
            
            return new HoloWatchMessage(this);
        }
        
        private boolean allPropertiesAreSet() {
            return null != title 
                && !title.isEmpty()
                && null != messageText
                && !messageText.isEmpty()
                && null != sender
                && !sender.isEmpty()
                && null != dateTimeReceived;       
        }
    }
    
    //</editor-fold>
}