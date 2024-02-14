package com.wd.education.core.service.date;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class DateTimeServiceImpl implements DateTimeService {

  private final Clock clock;

  @Override
  public LocalDate getLocalDate() {
    return LocalDate.now(clock);
  }

  @Override
  public LocalDateTime getLocalDateTime() {
    return LocalDateTime.now(clock);
  }

}
