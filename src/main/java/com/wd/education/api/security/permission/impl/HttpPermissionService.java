package com.wd.education.api.security.permission.impl;

import com.wd.education.api.security.AuthUtils;
import com.wd.education.api.security.exception.PermissionException;
import com.wd.education.api.security.permission.PermissionService;
import com.wd.education.properties.SecurityProperties;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@AutoLoggingEnabled
@RequiredArgsConstructor
@ConditionalOnProperty(name = "students.client-type", havingValue = "rest")
public class HttpPermissionService implements PermissionService {

  private final RestTemplate restTemplate;
  private final SecurityProperties securityProperties;
  private final AuthUtils authUtils;

  @Override
  @Cacheable(value = "students-jwk-cache", keyGenerator = "jwtKeyGenerator")
  public List<String> getPermissions(final String token, final String permissionsUrl) {
    try {
      final var headers = new HttpHeaders();
      headers.setBearerAuth(token);
      final var entity = new HttpEntity<>(headers);
      final var response = restTemplate
          .exchange(permissionsUrl, HttpMethod.GET, entity, String[].class);
      final var permissions = response.getBody();

      return permissions == null || permissions.length == 0
          ? Collections.emptyList()
          : Arrays.asList(permissions);
    } catch (final RuntimeException exception) {
      if (securityProperties.isEnabled()) {
        throw new PermissionException("Can't get permissions", exception);
      } else {
        return authUtils.getListAllPermissions();
      }
    }
  }
}
