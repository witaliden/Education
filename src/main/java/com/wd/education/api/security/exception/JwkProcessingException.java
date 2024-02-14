package com.wd.education.api.security.exception;

import java.io.Serial;

public class JwkProcessingException extends JwtVerificationException {

  @Serial
  private static final long serialVersionUID = -9543442343L;

  public JwkProcessingException(final String message) {
    super(message);
  }

  public JwkProcessingException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
