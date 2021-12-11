package com.hexagonal.api.repositories;

import com.hexagonal.api.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findByTrainerId(UUID trainerId);

    Optional<Group> findByIdAndTrainerId(UUID id, UUID trainerId);
}
