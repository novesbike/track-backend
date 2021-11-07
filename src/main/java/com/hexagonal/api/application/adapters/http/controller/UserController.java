package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.http.dtos.UserUpdatedDTO;
import com.hexagonal.api.application.adapters.persistence.FileAdapter;
import com.hexagonal.api.core.domain.entity.User;
import com.hexagonal.api.core.ports.inbound.FetchAllUsers;
import com.hexagonal.api.core.ports.inbound.UpdateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

  private final FetchAllUsers fetchAllUsers;
  private final UpdateProfile updateProfile;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping
  public ResponseEntity<List<User>> index() {
    return ResponseEntity.ok(fetchAllUsers.execute());
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserUpdatedDTO> uploadFile(
          @PathVariable("id") UUID idUser,
          @RequestParam(value = "file", required = false) MultipartFile file,
          @RequestParam(value = "username", required = false) String name

  ) throws IOException {

    String fileDownloadUri = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .path("/v1/avatar")
            .toUriString();

    var attachment = file != null ? new FileAdapter(file) : null;

    var updated = updateProfile.execute(
            idUser,
            name,
            attachment,
            fileDownloadUri
    );

    return ResponseEntity.status(HttpStatus.OK).body(new UserUpdatedDTO(updated));
  }
}
