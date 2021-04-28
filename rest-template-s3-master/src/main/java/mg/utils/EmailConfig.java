package mg.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailConfig
{
    public static String secretCode;

    /**
     * Method to send email to user
     * @param hostEmail
     */
    public void SendEmail(String hostEmail)
    {
        secretCode = TokenGenerator.getAlphaNumericString();
        final String username = @email;
        final String password = @password;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(@email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hostEmail));
            message.setSubject("Two-Step Verification");
            message.setText("<h1>Your one-time login code: " + secretCode + ". " + "<br/>" + "Please enter this code to login, the code only lasts for 15 minutes.</h1>", "utf-8", "html");
            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void RequestInit(String hostEmail)
    {
        final String username = @email;
        final String password = @password;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(@email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hostEmail));
            message.setSubject("Registration process");
            message.setText("<h1>Your registration form way is under way. Please wait for the email.</h1>", "utf-8", "html");
            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void RequestComplete(String hostEmail)
    {
        final String username = @email;
        final String password = @password;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(@email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(hostEmail));
            message.setSubject("Registration process");
            message.setText("<h1>Registration complete: Please click on the link to open registration form.</h1>" + "<h1>http://localhost:3000/Register</h1>", "utf-8", "html");
            Transport.send(message);
        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }
}
