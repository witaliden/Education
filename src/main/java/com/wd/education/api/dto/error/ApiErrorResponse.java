package com.wd.education.api.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Schema(description = "Error response structure")
public class ApiErrorResponse {

  @Schema(example = "018-01-11T16:34:55.5789457")
  private final LocalDateTime timestamp;
  @Schema(example = "401")
  private final int status;
  @Schema(example = "Can't get permissions")
  private final String message;
  @Schema(example = "Unauthorized")
  private final String exception;

  public ApiErrorResponse(
      final String message,
      final HttpStatus status,
      final LocalDateTime timestamp) {
    this.timestamp = timestamp;
    this.status = status.value();
    this.exception = status.getReasonPhrase();
    this.message = message;
  }
}
