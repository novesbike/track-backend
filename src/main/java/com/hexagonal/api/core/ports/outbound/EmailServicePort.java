package com.hexagonal.api.core.ports.outbound;

public interface EmailServicePort {
  void sendConfirmation(String name, String email);
}
