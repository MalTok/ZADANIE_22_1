package pl.mt.task;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    public static final String SITE_OWNER_EMAIL_ADDRESS = "***@byom.de";
    public static final String SERVICE_EMAIL_ADDRESS = "***@op.pl";
    public static final String ENCODING = "utf-8";

    private final JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlMessage(String to, String from, String replyTo, String subject, String messageText) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, ENCODING);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setReplyTo(replyTo);
        helper.setSubject(subject);
        helper.setText(messageText, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendEmail(Message message) throws MessagingException {
        String replyTo = message.getSenderEmail();
        String subject = "Nowa wiadomość od " + message.getSender();
        String messageText = message.getText();
        sendHtmlMessage(SITE_OWNER_EMAIL_ADDRESS, SERVICE_EMAIL_ADDRESS, replyTo, subject, messageText);
    }

    public void sendAutoReply(Message message) throws MessagingException {
        String to = message.getSenderEmail();
        String replyTo = "no@reply"; //fikcyjna wartość, nie może być nullem
        String subject = message.getSender() + " dziękujemy za kontakt!";
        String messageText = "Niebawem dostaniesz odpowiedź na poniższą wiadomość:<br/>----------<br/>"
                + message.getText();
        sendHtmlMessage(to, SERVICE_EMAIL_ADDRESS, replyTo, subject, messageText);

    }
}
