package com.hexagonal.api.core.ports.outbound;

import com.hexagonal.api.core.domain.entity.UserAuth;

public interface EmailServicePort {
  void sendConfirmation(UserAuth account);
}
