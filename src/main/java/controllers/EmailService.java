package controllers;

import model.Customer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

  public EmailService() { }

  static final String username = "ae00cd271c09de";
  static final String pass = "72dcf21228e5fa";

  public static void sendEmail(String toEmail, String subject, String messageToSend, String fromEmail) {

  String to = toEmail;
  String host = "smtp.mailtrap.io";
  String from = fromEmail;
  Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "false");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "2525");
    props.put("mail.smtp.connectiontimeout", "t1");
    props.put("mail.smtp.timeout", "t2");
    props.put("mail.smtp.ssl.protocols", "TLSv1,TLSv1.1,TLSv1.2");

  Session ses = Session.getInstance(props,

  new Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() {
      return new PasswordAuthentication(username, pass);
    }
  });

  Message message = null;
    try {
    message = new MimeMessage(ses);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));
    message.setSubject(subject);
    message.setContent(messageToSend, "text/html");
    Transport.send(message);
  } catch (MessagingException e) {
    throw new RuntimeException(e);
  }

  }
}
