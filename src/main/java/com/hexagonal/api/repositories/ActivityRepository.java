package com.hexagonal.api.repositories;

import com.hexagonal.api.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByUserId(UUID userId);

    Optional<Activity> findByIdAndUserId(UUID id, UUID userId);
}
