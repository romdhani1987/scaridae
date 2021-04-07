package fr.romdhani.scaridae.utils.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Creates an email client utility
 *
 * @author aromdhani
 */
public class EmailClient {
    private final static Logger logger = LogManager.getLogger(EmailClient.class);

    private EmailClient() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Send an email.
     *
     * @param emailData The email data holds the email client properties.
     */
    public static void send(EmailData emailData) {
        try {
            Session session = createSession(emailData.getHost(), String.valueOf(emailData.getPort()), emailData.isUseTls(), emailData.isAuth(),
                    emailData.getSenderEmail(), emailData.getSenderPass());
            // Create message using session
            MimeMessage message = new MimeMessage(session);
            prepareEmailMessage(message, emailData.getSenderEmail(), emailData.getTo(), emailData.getMessageTitle(), emailData.getMessageAsHtml());
            // Sending message
            Transport.send(message);
        } catch (MessagingException e) {
            logger.warn("Failed to send an email to the user!", e);
        }
    }

    private static void prepareEmailMessage(MimeMessage message, String sender, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession(String host, String port, boolean useTls, boolean auth, String senderEmail, String senderPass) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", String.valueOf(useTls));
        props.put("mail.smtp.auth", String.valueOf(auth));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPass);
            }
        });
        return session;
    }

}
