package com.wd.education.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.is4")
public class SecurityOauth2Properties {

  private String username;
  private String password;
}
