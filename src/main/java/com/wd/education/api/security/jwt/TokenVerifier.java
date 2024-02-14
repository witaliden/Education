package com.wd.education.api.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface TokenVerifier {

  DecodedJWT verify(final String token);
}
