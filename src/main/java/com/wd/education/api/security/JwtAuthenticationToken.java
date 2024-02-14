package com.wd.education.api.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Holder for JWT token from the request. Other fields aren't used but necessary to comply to the
 * contracts of AbstractUserDetailsAuthenticationProvider.
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = -157160408685625946L;

  private final String token;

  public JwtAuthenticationToken(final String token) {
    super(null, null);
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}

