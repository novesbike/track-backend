package com.hexagonal.api.application.adapters.persistence.model;

import com.hexagonal.api.core.domain.entity.Attachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class AttachmentModel {

  @Id
  @GeneratedValue(generator = "uuid")
  private UUID id;

  private String name;

  private String type;

  @Lob
  private byte[] data;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserModel user;


  public AttachmentModel(Attachment attachment) {
    this.name = attachment.getName();
    this.type = attachment.getType();
    this.data = attachment.getData();
    this.user = new UserModel(attachment.getUser());
  }
}
