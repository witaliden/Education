package com.wd.education.api.security.exception;

import java.io.Serial;

public class InvalidAccessTokenException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 7793253995825054076L;
  private static final String ERROR_GETTING_TOKEN = "Error getting access token from %s";

  public InvalidAccessTokenException(final String resourceName) {
    super(String.format(ERROR_GETTING_TOKEN, resourceName));
  }
}
