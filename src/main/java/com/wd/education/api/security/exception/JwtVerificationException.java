package com.wd.education.api.security.exception;

import java.io.Serial;
import org.springframework.security.core.AuthenticationException;

public class JwtVerificationException extends AuthenticationException {

  @Serial
  private static final long serialVersionUID = -9876543345541L;

  public JwtVerificationException(final String msg) {
    this(msg, null);
  }

  public JwtVerificationException(final String msg, final Throwable cause) {
    super(msg, cause);
  }
}
