package ru.sample.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sample.command.exception.UnavailableCommandException;
import ru.sample.synth.core.errorhandling.SyntheticHumanGlobalExceptionHandler;

@RestControllerAdvice
public class BishopExceptionHandler extends SyntheticHumanGlobalExceptionHandler {

  @ExceptionHandler(UnavailableCommandException.class)
  public ErrorResponse handleExecutionQueueIsFullException(UnavailableCommandException e) {
    return super.buildErrorResponse(e, HttpStatus.BAD_REQUEST, "COMMAND IS UNAVAILABLE");
  }

}
