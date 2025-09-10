package utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendMail {

    public static void sendReport(String reportPath, String toEmail) throws Exception {
        String fromEmail = "devalanka1997@gmail.com";
        String password = "flyb fxuw dnig wjdi";  // Gmail App Password if 2FA

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create Session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Create Message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Automation Test Report");

        // Body + Attachment
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText("Please find attached the automation report.");

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(reportPath);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        // Send
        Transport.send(message);
        System.out.println("Report sent successfully to " + toEmail);
    }
}
