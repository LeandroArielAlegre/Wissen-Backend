package com.api.wissenapi.models;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailManager {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public MailManager(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMessage(String email, String messageEmail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            message.setSubject("Prueba de correo");
            helper.setTo(email);
            helper.setText(messageEmail, true);
            helper.setFrom(sender);
            javaMailSender.send(message);
            System.out.println("Correo enviado exitosamente a: " + email);
        } catch (MessagingException e) {
            System.err.println("Error al enviar correo a: " + email + " - " + e.getMessage());
            throw new RuntimeException("Fallo al enviar el correo: " + e.getMessage(), e);
        }
    }
}