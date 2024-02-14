package com.wd.education.api.security.provider;

import com.wd.education.api.security.AuthenticatedUser;
import com.wd.education.api.security.JwtAuthenticationToken;
import com.wd.education.api.security.exception.JwtVerificationException;
import com.wd.education.api.security.jwt.TokenVerifier;
import com.wd.education.api.security.permission.PermissionService;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Used for checking the token from the request and supply the UserDetails if the token is valid.
 */
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  private final TokenVerifier tokenVerifier;
  private final String permissionsUrl;
  private final PermissionService permissionService;

  public JwtAuthenticationProvider(
      final TokenVerifier tokenVerifier,
      final String permissionsUrl,
      final PermissionService permissionService
  ) {
    this.tokenVerifier = tokenVerifier;
    this.permissionsUrl = permissionsUrl;
    this.permissionService = permissionService;
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  protected void additionalAuthenticationChecks(
      final UserDetails userDetails,
      final UsernamePasswordAuthenticationToken authentication
  ) {
    // Do nothing
  }

  @Override
  protected UserDetails retrieveUser(
      final String username,
      final UsernamePasswordAuthenticationToken authentication
  ) {
    final var jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    final var token = jwtAuthenticationToken.getToken();

    try {
      final var jwt = tokenVerifier.verify(token);
      final var claimUserId = jwt.getClaims().get("students_user_id");
      final var userId = claimUserId == null ? null :
          Long.valueOf(claimUserId.asString().replaceAll("\"", ""));
      return new AuthenticatedUser(jwt.getSubject(), token, userId,
          permissionService.getPermissions(token, permissionsUrl), Collections
          .emptyList());
    } catch (final Exception e) {
      throw new JwtVerificationException(e.getMessage());
    }
  }
}
