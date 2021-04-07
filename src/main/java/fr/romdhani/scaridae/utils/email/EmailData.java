package fr.romdhani.scaridae.utils.email;

import lombok.Data;

/**
 * Email data holds the data used to send an email.
 */
@Data
public class EmailData {
    private String senderEmail = "";
    private String to = "";
    private String senderPass = "";
    private String messageTitle = "";
    private String messageAsHtml = "";
    private String host = "";
    private int port = 0;
    private boolean useTls = true;
    private boolean auth = true;

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }


}
