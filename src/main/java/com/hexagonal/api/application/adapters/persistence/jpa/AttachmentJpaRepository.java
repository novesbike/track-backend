package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.AttachmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentJpaRepository extends JpaRepository<AttachmentModel, UUID> {
}
