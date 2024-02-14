package com.wd.education.api.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class studentVerifier {

  private final AuthUtils authUtils;

  public boolean isMayor() {
    return authUtils.getPermissions().contains("students.mayor");
  }
  public boolean isAdmin(final Long studentId) {
    return authUtils.getUserId().equals(studentId);
  }

}
