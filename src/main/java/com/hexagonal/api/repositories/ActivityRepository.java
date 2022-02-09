package com.hexagonal.api.repositories;

import com.hexagonal.api.models.ActivityStats;
import com.hexagonal.api.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, UUID> {

    Optional<Activity> findByIdAndUserId(UUID id, UUID userId);

    @Query("SELECT u FROM Activity u WHERE u.user.id = ?1 ORDER BY u.createdAt DESC")
    List<Activity> findByUserId(UUID userId);

    @Query("SELECT new com.hexagonal.api.models.ActivityStats(AVG(a.averageSpeed), SUM(a.distance)) "
            + "FROM Activity a WHERE a.user.id = ?1")
    ActivityStats getMyStats(UUID userId);
}
