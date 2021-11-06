package com.hexagonal.api.core.usecases;

import com.hexagonal.api.core.domain.entity.Attachment;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.domain.exception.BusinessRuleException;
import com.hexagonal.api.core.domain.valueobjects.File;
import com.hexagonal.api.core.ports.inbound.UpdateProfile;
import com.hexagonal.api.core.ports.outbound.repository.AttachmentRepositoryPort;
import com.hexagonal.api.core.ports.outbound.repository.UserRepositoryPort;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.UUID;

public class UpdateProfileImpl implements UpdateProfile {

  private final AttachmentRepositoryPort attachmentRepositoryPort;
  private final UserRepositoryPort userRepositoryPort;

  public UpdateProfileImpl(AttachmentRepositoryPort attachmentRepositoryPort, UserRepositoryPort userRepositoryPort) {
    this.attachmentRepositoryPort = attachmentRepositoryPort;
    this.userRepositoryPort = userRepositoryPort;
  }

  @Override
  public User execute(UUID idUser, String name, File file, String requestURL) throws IOException {

    var user = userRepositoryPort.findUserById(idUser)
            .orElseThrow(() -> new BusinessRuleException("Usuário não encontrado"));

    user.setName(name);

    if (file != null) {
      var avatar = storage(file, user);
      user.setAvatar(String.format("%s/%s", requestURL, avatar.getId()));
    }

    return userRepositoryPort.save(user);
  }

  @Override
  public Attachment storage(File file, User user) throws IOException {
    if (file == null) return null;

    var fileName = StringUtils.cleanPath(file.getOriginalFilename());
    var attachment = new Attachment(fileName, file.getContentType(), file.getBytes(), user);
    return attachmentRepositoryPort.save(attachment);
  }
}
