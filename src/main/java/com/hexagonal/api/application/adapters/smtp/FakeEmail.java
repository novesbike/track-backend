package com.hexagonal.api.application.adapters.smtp;

import com.hexagonal.api.core.domain.entity.UserAuth;
import com.hexagonal.api.core.ports.outbound.EmailServicePort;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class FakeEmail implements EmailServicePort {

  @Override
  public void sendConfirmation(UserAuth account) {
    System.out.println("Email SENT");
  }

}
