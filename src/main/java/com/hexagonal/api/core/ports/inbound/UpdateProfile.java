package com.hexagonal.api.core.ports.inbound;

import com.hexagonal.api.core.domain.entity.Attachment;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.domain.valueobjects.File;

import java.io.IOException;
import java.util.UUID;

public interface UpdateProfile {
  User execute(UUID idUser, String name, File file, String requestURL) throws IOException;

  Attachment storage(File file, User user) throws IOException;
}
