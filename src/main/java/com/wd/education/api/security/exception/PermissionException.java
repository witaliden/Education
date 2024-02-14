package com.wd.education.api.security.exception;

public class PermissionException extends RuntimeException {

  public PermissionException(final String message) {
    super(message);
  }

  public PermissionException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
