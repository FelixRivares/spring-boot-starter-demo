package ru.sample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sample.db.TimeEntity;
import ru.sample.db.TimeRepository;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TimeToDatabaseService {

  private final TimeRepository timeRepository;

  public String saveAndGetCurrentTime() {
    final var currentTime = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
    final var currentTimeEntity = new TimeEntity();
    currentTimeEntity.setTime(currentTime);
    timeRepository.save(currentTimeEntity);
    return currentTime;
  }


}
