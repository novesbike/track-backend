package com.hexagonal.api.core.domain.valueobjects;

import java.io.IOException;

public interface File {
  String getOriginalFilename();

  String getContentType();

  byte[] getBytes() throws IOException;
}
