package com.hexagonal.api.application.adapters.web.controller;

import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.core.domain.entity.Role;
import com.hexagonal.api.core.ports.inbound.CreateRole;
import com.hexagonal.api.core.ports.inbound.FetchAllRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
public class RoleController {

  private final CreateRole createRole;
  private final FetchAllRoles fetchAllRoles;


  @PostMapping
  public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel role) {
    var roleCreated = createRole.execute(role.getName(), role.getDescription());
    return new ResponseEntity<>(new RoleModel(roleCreated), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<RoleModel>> fetchAllRoles() {
    var roles = fetchAllRoles.execute();
    return ResponseEntity.ok(roles.stream().map(RoleModel::new).collect(Collectors.toList()));
  }

}
