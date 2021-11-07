package com.hexagonal.api.core.ports.inbound;

import javax.security.sasl.AuthenticationException;
import java.util.UUID;

public interface GetUserAvatar {
  byte[] execute(UUID id) throws AuthenticationException;
}
