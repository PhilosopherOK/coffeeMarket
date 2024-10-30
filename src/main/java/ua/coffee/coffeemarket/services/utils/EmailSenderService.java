package ua.coffee.coffeemarket.services.utils;


import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("antiinvokermage@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}