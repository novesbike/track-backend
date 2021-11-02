package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.persistence.ActivityService;
import com.hexagonal.api.application.adapters.http.dtos.ActivityDTO;
import com.hexagonal.api.application.adapters.http.dtos.ActivityDetailedDTO;
import com.hexagonal.api.application.adapters.http.dtos.SaveActivityDTO;
import com.hexagonal.api.core.ports.inbound.GetActivityHistory;
import com.hexagonal.api.core.ports.inbound.SaveMyActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/activities")
public class ActivityController {

  private final SaveMyActivity saveMyActivity;
  private final GetActivityHistory getActivityHistory;
  private final ActivityService service;

  @PostMapping
  public ResponseEntity<ActivityDetailedDTO> saveActivity(@RequestBody SaveActivityDTO activity) {
    var saved = saveMyActivity.execute(
            activity.getTitle(),
            activity.getDescription(),
            activity.getDate(),
            activity.getTiming(),
            activity.getAverageSpeed(),
            activity.getDistance(),
            activity.getElevation(),
            activity.getCoordinates()
    );

    return new ResponseEntity<>(new ActivityDetailedDTO(saved), HttpStatus.CREATED);
  }


  @GetMapping
  public ResponseEntity<List<ActivityDTO>> getActivityHistory() {
    var activityList = getActivityHistory.execute();
    var dto = activityList.stream().map(ActivityDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok(dto);
  }

  // TODO: REMOVER
  @GetMapping("/all")
  public ResponseEntity<List<ActivityDTO>> fetchAll() {
    var list = service.findAll();
    var dto = list.stream().map(ActivityDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok(dto);
  }


}
