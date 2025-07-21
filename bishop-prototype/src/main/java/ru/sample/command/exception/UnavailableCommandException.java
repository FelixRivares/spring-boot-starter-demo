package ru.sample.command.exception;

public class UnavailableCommandException extends Exception {

  public UnavailableCommandException(String message) {
    super(message);
  }

}
