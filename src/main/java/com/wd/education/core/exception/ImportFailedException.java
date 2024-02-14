package com.wd.education.core.exception;

public class ImportFailedException extends RuntimeException {

  public ImportFailedException(final String message) {
    super(message);
  }

  public ImportFailedException(final String message, final Exception ex) {
    super(message, ex);
  }
}
