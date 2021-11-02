package com.hexagonal.api.application.adapters.smtp;

import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


//@Service
//@Profile("dev")
@AllArgsConstructor
public class EmailService implements EmailServicePort {

  private final JavaMailSender javaMailSender;

  @Async
  @Override
  public void sendConfirmation(String name, String email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Registro Completo!");
    message.setFrom("NovesClub@gmail.com");
    message.setText("Bem vindo " + name + "!\n\nPara confirmar sua conta, clique no link: "
            + "https://floating-headland-47248.herokuapp.com/users/confirm-account?token=blablabla");

    javaMailSender.send(message);
  }
}
