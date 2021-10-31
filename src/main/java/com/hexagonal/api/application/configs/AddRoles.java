package com.hexagonal.api.application.configs;

import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.application.adapters.persistence.jpa.RoleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddRoles implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private RoleJpaRepository repository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    var roles = repository.findAll();

    if (roles.isEmpty()) {
      repository.saveAll(List.of(
            new RoleModel("ROLE_USER"),
            new RoleModel("ROLE_TRAINER")
      ));
    }

  }
}
