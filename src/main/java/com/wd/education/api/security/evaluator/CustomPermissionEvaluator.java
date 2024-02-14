package com.wd.education.api.security.evaluator;

import com.wd.education.api.security.AuthenticatedUser;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

  @Override
  public boolean hasPermission(
      final Authentication auth,
      final Object targetDomainObject,
      final Object permission
  ) {
    if (auth == null || !(permission instanceof String)) {
      return false;
    }
    return hasPrivilege(auth, permission.toString());
  }

  @Override
  public boolean hasPermission(
      final Authentication auth,
      final Serializable targetId,
      final String targetType,
      final Object permission
  ) {
    if (auth == null || !(permission instanceof String)) {
      return false;
    }
    return hasPrivilege(auth, permission.toString());
  }

  private boolean hasPrivilege(final Authentication auth, final String permission) {
    final var user = (AuthenticatedUser) auth.getPrincipal();
    return user.getPermissions().contains(permission);
  }
}
