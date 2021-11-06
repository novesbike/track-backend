package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.core.domain.valueobjects.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileAdapter implements File {

  private MultipartFile file;

  public FileAdapter(MultipartFile file) {
    this.file = file;
  }

  @Override
  public String getOriginalFilename() {
    return file.getOriginalFilename();
  }

  @Override
  public String getContentType() {
    return file.getContentType();
  }

  @Override
  public byte[] getBytes() throws IOException {
    return file.getBytes();
  }
}
