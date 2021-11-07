package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.persistence.model.RoleModel;
import com.hexagonal.api.core.ports.inbound.CreateRole;
import com.hexagonal.api.core.ports.inbound.FetchAllRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/roles")
public class RoleController {

  private final CreateRole createRole;
  private final FetchAllRoles fetchAllRoles;


  @PreAuthorize("hasAnyRole('ADMIN')")
  @PostMapping
  public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel role) {
    var roleCreated = createRole.execute(role.getName(), role.getDescription());
    return new ResponseEntity<>(new RoleModel(roleCreated), HttpStatus.CREATED);
  }

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<RoleModel>> fetchAllRoles() {
    var roles = fetchAllRoles.execute();
    return ResponseEntity.ok(roles.stream().map(RoleModel::new).collect(Collectors.toList()));
  }

}
