package com.hexagonal.api.application.adapters.persistence;

import com.hexagonal.api.core.domain.entity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

  private final ActivityRepositoryAdapter repository;

  public List<Activity> findAll() {
    return repository.findAll();
  }


}
