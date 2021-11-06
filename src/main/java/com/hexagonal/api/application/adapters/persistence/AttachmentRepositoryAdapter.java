package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.application.adapters.persistence.jpa.AttachmentJpaRepository;
import com.hexagonal.api.application.adapters.persistence.model.AttachmentModel;
import com.hexagonal.api.core.domain.entity.Attachment;
import com.hexagonal.api.core.ports.outbound.repository.AttachmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AttachmentRepositoryAdapter implements AttachmentRepositoryPort {

  private final AttachmentJpaRepository repository;

  @Override
  public Attachment save(Attachment attachment) {
    var attach = new AttachmentModel(attachment);

    var saved = repository.save(attach);

    return new Attachment(
            saved.getId(),
            saved.getName(),
            saved.getType(),
            saved.getData(),
            saved.getUser().toDomain()

    );
  }

  @Override
  public Optional<Attachment> findById(UUID id) {
    var attach = repository.findById(id);

    return attach.map(attachModel -> new Attachment(
            attachModel.getId(),
            attachModel.getName(),
            attachModel.getType(),
            attachModel.getData(),
            attachModel.getUser().toDomain()
    ));
  }
}
