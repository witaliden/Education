package com.wd.education.api.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wd.education.api.security.exception.JwkProcessingException;
import java.util.Optional;

public class WithoutVerificationTokenVerifier implements TokenVerifier {

  @Override
  public DecodedJWT verify(final String token) {
    return Optional.ofNullable(token)
        .filter(t -> !t.isBlank())
        .map(JWT::decode)
        .orElseThrow(() -> new JwkProcessingException("JWT token can not be null"));
  }
}
