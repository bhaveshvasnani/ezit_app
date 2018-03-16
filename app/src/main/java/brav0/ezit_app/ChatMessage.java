package brav0.ezit_app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 29-Jun-17.
 */

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageTime;

    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        /*final long timestamp = new Date().getTime();
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        final String timeString =
                new SimpleDateFormat("HH:mm").format(cal.getTime());
        */
        Date cDate = new Date();
        messageTime = new SimpleDateFormat("EEE, MMM dd, yyyy h:mm a").format(cDate);
    }

    public ChatMessage(){

    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
}
