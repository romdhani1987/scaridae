package fr.romdhani.scaridae.utils.email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;

/**
 * Creates an email client. To send an email.
 *
 * @author aromdhani
 */
public class EmailClient {
    private static String senderEmail = "";//To change with your sender email
    private static String to = "";//To change with your sender email
    private static String senderPassword = "";//To change with your sender password
    private static String messageTitle = "";
    private static String messageAsHtml = "";
    private static String host = "";
    private static String port = "";
    private static Boolean useTls = true;
    private static Boolean auth = true;

    public static String getTo() {
        return to;
    }

    public static void setTo(String to) {
        EmailClient.to = to;
    }

    public static void setSenderEmail(String senderEmail) {
        EmailClient.senderEmail = senderEmail;
    }

    public static void setSenderPassword(String senderPassword) {
        EmailClient.senderPassword = senderPassword;
    }

    public static String getSenderEmail() {
        return senderEmail;
    }

    public static String getSenderPassword() {
        return senderPassword;
    }

    public static String getMessageTitle() {
        return messageTitle;
    }

    public static void setMessageTitle(String messageTitle) {
        EmailClient.messageTitle = messageTitle;
    }

    public static String getMessageAsHtml() {
        return messageAsHtml;
    }

    public static void setMessageAsHtml(String messageAsHtml) {
        EmailClient.messageAsHtml = messageAsHtml;
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        EmailClient.host = host;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        EmailClient.port = port;
    }

    public static Boolean getUseTls() {
        return useTls;
    }

    public static void setUseTls(Boolean useTls) {
        EmailClient.useTls = useTls;
    }

    public static Boolean getAuth() {
        return auth;
    }

    public static void setAuth(Boolean auth) {
        EmailClient.auth = auth;
    }

    /**
     * Send an email
     */
    public static void send() {
        send("");
    }

    /**
     * Send an email
     *
     * @param title the title of the email
     * @param html  the message, it could be inhtml format.
     */
    public static void send(String title, String html) {
        try {

            Session session = createSession();
            //create message using session
            MimeMessage message = new MimeMessage(session);
            EmailClient.messageTitle = title;
            EmailClient.messageAsHtml = html;
            prepareEmailMessage(message, to, messageTitle, messageAsHtml);

            //sending message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send an email
     *
     * @param serieName the serie name
     */
    public static void send(String serieName) {
        try {

            Session session = createSession();
            //create message using session
            MimeMessage message = new MimeMessage(session);
            EmailClient.messageTitle = String.format(messageTitle, serieName);
            String newMessage = String.format(messageAsHtml, serieName);
            prepareEmailMessage(message, to, messageTitle, newMessage);
            //sending message
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * PRepare the message
     *
     * @param message the Mime message
     * @param to      to
     * @param title   the title of the email
     * @param html    the message
     * @throws MessagingException
     */
    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    /**
     * Create a session
     *
     * @return session
     */
    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", useTls.toString());
        props.put("mail.smtp.auth", auth.toString());

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }

    /**
     * Reset email client properties
     */
    public static void reset() {
        Arrays.asList(senderEmail, senderPassword, to, messageTitle, messageAsHtml).forEach(field -> field = "");
    }

    /**
     * Gets infos about the current email client.
     *
     * @return Email client
     */
    public static String getInfos() {
        StringBuilder str = new StringBuilder();
        str.append("Sender: ").append(senderEmail).append("; ")
                .append("To: ").append(to).append("; ")
                .append("Title: ").append(messageTitle).append("; ")
                .append("Message: ").append(messageAsHtml);
        return str.toString();
    }
}
