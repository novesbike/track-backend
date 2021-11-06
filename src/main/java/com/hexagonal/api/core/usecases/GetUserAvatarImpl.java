package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.exception.ResourceNotFoundException;
import com.hexagonal.api.core.ports.inbound.GetUserAvatar;
import com.hexagonal.api.core.ports.outbound.SecurityPort;
import com.hexagonal.api.core.ports.outbound.repository.AttachmentRepositoryPort;

import javax.security.sasl.AuthenticationException;
import java.util.UUID;

public class GetUserAvatarImpl implements GetUserAvatar {

  private final AttachmentRepositoryPort repositoryPort;
  private final SecurityPort securityPort;

  public GetUserAvatarImpl(AttachmentRepositoryPort repositoryPort, SecurityPort securityPort) {
    this.repositoryPort = repositoryPort;
    this.securityPort = securityPort;
  }

  @Override
  public byte[] execute(UUID id) throws AuthenticationException {
    var avatar = repositoryPort
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Avatar não encontrado"));

    return avatar.getData();
  }
}
