package com.hexagonal.api.application.adapters.http.controller;

import com.hexagonal.api.application.adapters.http.dtos.ActivityDetailedDTO;
import com.hexagonal.api.application.adapters.http.dtos.MyActivityHistoryDTO;
import com.hexagonal.api.application.adapters.http.dtos.SaveActivityDTO;
import com.hexagonal.api.core.domain.valueobjects.ActivityStats;
import com.hexagonal.api.core.ports.inbound.FetchAllUsers;
import com.hexagonal.api.core.ports.inbound.GetActivityHistory;
import com.hexagonal.api.core.ports.inbound.GetMyStats;
import com.hexagonal.api.core.ports.inbound.SaveMyActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/activities")
public class ActivityController {

  private final SaveMyActivity saveMyActivity;
  private final GetActivityHistory getActivityHistory;
  private final GetMyStats getMyStats;

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


  @GetMapping("/me")
  public ResponseEntity<MyActivityHistoryDTO> getActivityHistory() {
    var activityList = getActivityHistory.execute();
    var dto = new MyActivityHistoryDTO(activityList);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/stats")
  public ResponseEntity<ActivityStats> getMyStats(@PathVariable UUID id) {
    return ResponseEntity.ok(getMyStats.execute());
  }

}
