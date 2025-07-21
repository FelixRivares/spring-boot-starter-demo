package ru.sample.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sample.service.TimeToDatabaseService;

@RestController
@RequiredArgsConstructor
public class TimeController {

  private final TimeToDatabaseService timeToDatabaseService;

  @GetMapping("/time")
  public String currentTime() {
    return timeToDatabaseService.saveAndGetCurrentTime();
  }

}
