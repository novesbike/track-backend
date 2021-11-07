package com.hexagonal.api.application.adapters.persistence.jpa;

import com.hexagonal.api.application.adapters.persistence.model.ActivityModel;
import com.hexagonal.api.core.domain.valueobjects.ActivityStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ActivityJpaRepository extends JpaRepository<ActivityModel, UUID> {

  @Query("SELECT u FROM ActivityModel u WHERE u.user.id = ?1 ORDER BY u.date DESC")
  List<ActivityModel> findByUserId(UUID userId);

  @Query("SELECT new com.hexagonal.api.core.domain.valueobjects.ActivityStats(AVG(a.averageSpeed), SUM(a.distance)) "
          + "FROM ActivityModel a WHERE a.user.id = ?1")
  ActivityStats getMyStats(UUID userId);
}
