package pl.chomiczek.projekt_kosowski_rynski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SenderMail {

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
