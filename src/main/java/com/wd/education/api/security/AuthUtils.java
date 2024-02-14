package com.wd.education.api.security;

import com.wd.education.api.security.exception.PermissionException;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {
 
  private static final List<String> LIST_ALL_PERMISSIONS = List.of(
      "students.create_course",
      "students.delete_course",
      "students.update_course_info"
  );

  public String getBearerToken() {
    return "Bearer " + getToken();
  }

  public String getToken() {
    final var authenticatedUser = (AuthenticatedUser) getAuthentication().getPrincipal();
    return authenticatedUser.getToken();
  }

  public Long getUserId() {
    final var authenticatedUser = (AuthenticatedUser) getAuthentication().getPrincipal();
    return Optional.ofNullable(authenticatedUser.getUserId()).orElseThrow(
        () -> new PermissionException("User id does not exists in access token"));
  }

  public List<String> getPermissions() {
    final var authenticatedUser = (AuthenticatedUser) getAuthentication().getPrincipal();
    return authenticatedUser.getPermissions();
  }

  public List<String> getListAllPermissions() {
    return LIST_ALL_PERMISSIONS;
  }

  private Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}