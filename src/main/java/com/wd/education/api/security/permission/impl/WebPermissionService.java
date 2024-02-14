package com.wd.education.api.security.permission.impl;

import com.wd.education.api.security.AuthUtils;
import com.wd.education.api.security.exception.PermissionException;
import com.wd.education.api.security.permission.PermissionService;
import com.wd.education.properties.HttpClientProperties;
import com.wd.education.properties.SecurityProperties;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
//@AutoLoggingEnabled
@RequiredArgsConstructor
@ConditionalOnProperty(name = "students.client-type", havingValue = "webflux")
public class WebPermissionService implements PermissionService {

  private static final long CONNECTION_TIMEOUT_MULTIPLIER = 2L;

  private final AuthUtils authUtils;

  private final SecurityProperties securityProperties;

  private final HttpClientProperties httpClientProperties;

  @Override
  @Cacheable(value = "students-jwk-cache", keyGenerator = "jwtKeyGenerator")
  public List<String> getPermissions(final String token, final String permissionsUrl) {
    try {
      final long mills = CONNECTION_TIMEOUT_MULTIPLIER * httpClientProperties.getConnectTimeout();
      final var permissions = WebClient.create()
          .get()
          .uri(permissionsUrl)
          .header("Authorization", "Bearer " + token)
          .retrieve()
          .bodyToMono(String[].class)
          .block(Duration.ofMillis(mills));

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
