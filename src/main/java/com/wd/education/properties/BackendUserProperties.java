package com.wd.education.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("api.backend.security")
public class BackendUserProperties {

  private String clientId;
  private String grantType;
  private String username;
  private String password;
  private String clientSecret;
  private String scope;
  private String url;
}
