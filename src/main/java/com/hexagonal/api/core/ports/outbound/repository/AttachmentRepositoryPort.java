package com.hexagonal.api.core.ports.outbound.repository;

import com.hexagonal.api.core.domain.entity.Attachment;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepositoryPort {
  Attachment save(Attachment attachment);
  Optional<Attachment> findById(UUID id);
}
