package pl.chomiczek.projekt_kosowski_rynski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SenderMail {

/*    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.connectiontimeout=5000
    spring.mail.properties.mail.smtp.timeout=5000
    spring.mail.properties.mail.smtp.writetimeout=5000
    spring.mail.properties.mail.smtp.starttls.enable=true*/

    private JavaMailSender javaMailSender;

    @Autowired
    public SenderMail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String content, String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Chomiczek workout detector");
        msg.setText(content);
        javaMailSender.send(msg);
    }
}
