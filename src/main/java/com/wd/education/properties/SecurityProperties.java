package com.wd.education.properties;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

  private boolean enabled;
  private List<String> publicUrls;
  private String issuer;
  private String jwkUri;
  private String audience;
  private String dtoPackage;
  private String permissionsUrl;


  public OrRequestMatcher getPublicUrls() {
    return new OrRequestMatcher(
        this.publicUrls.stream()
            .map(AntPathRequestMatcher::new)
            .collect(Collectors.toList())
    );
  }
}
