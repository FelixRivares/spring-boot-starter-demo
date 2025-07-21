package ru.sample.synth.core.command.service.exception;

public class ExecutionQueueIsFullException extends Exception {
  public ExecutionQueueIsFullException(String message, Exception e) {
    super(message, e);
  }
}
