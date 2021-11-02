package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ActivityJpaRepository extends JpaRepository<ActivityModel, UUID> {

  @Query("SELECT u FROM ActivityModel u WHERE u.user.id = ?1")
  List<ActivityModel> findByUserId(UUID userId);
}
