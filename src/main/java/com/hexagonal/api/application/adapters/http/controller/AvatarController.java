package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.core.ports.inbound.GetUserAvatar;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/avatar")
public class AvatarController {

  private final GetUserAvatar getUserAvatar;


  @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws AuthenticationException {
    return ResponseEntity.ok().body(getUserAvatar.execute(id));
  }

}
