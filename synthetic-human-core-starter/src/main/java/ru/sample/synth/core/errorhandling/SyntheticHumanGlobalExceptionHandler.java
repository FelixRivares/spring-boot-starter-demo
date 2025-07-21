package ru.sample.synth.core.errorhandling;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sample.synth.core.command.service.exception.ExecutionQueueIsFullException;

import java.net.URI;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
// TODO добавить обработку общих ошибок (ее тут не хватает)
public class SyntheticHumanGlobalExceptionHandler {

  @ExceptionHandler(ExecutionQueueIsFullException.class)
  public ErrorResponse handleExecutionQueueIsFullException(ExecutionQueueIsFullException e) {
    return buildErrorResponse(e, HttpStatus.TOO_MANY_REQUESTS, "ANDROID EXECUTION QUEUE IS FULL");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
    return buildErrorResponse(e, HttpStatus.BAD_REQUEST, "Invalid input data format");
  }

  public ErrorResponse buildErrorResponse(Exception ex, HttpStatus statusCode, String detail) {
    StringBuilder details = new StringBuilder();
    details.append(ex.getMessage());
    if (ex.getCause() != null) {
      details.append(" | ");
      details.append("Caused by: [%s]".formatted(ex.getCause().getMessage()));
    }
    logError(ex, statusCode);
    return ErrorResponse.builder(ex, statusCode, details.toString())
        .detail(detail)
        .type(URI.create("/%s/%s".formatted("android", "error")))
        .property("Error class", ex.getClass())
        .property("timestamp", Instant.now())
        .build();
  }

  private void logError(Exception ex, HttpStatusCode statusCode) {
    if (statusCode.is5xxServerError()) {
      log.error("Internal error", ex);
    } else {
      log.debug("User error", ex);
    }
  }

}
