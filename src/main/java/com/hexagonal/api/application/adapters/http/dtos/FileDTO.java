package com.hexagonal.api.application.adapters.http.dtos;

import com.hexagonal.api.application.adapters.persistence.model.AttachmentModel;
import lombok.Data;

@Data
public class FileDTO {
  private String name;
  private String url;
  private String type;
  private long size;

  public FileDTO(AttachmentModel storage, String url) {
    this.name = storage.getName();
    this.type = storage.getType();
    this.size = storage.getData().length;
    this.url = url;
  }
}
