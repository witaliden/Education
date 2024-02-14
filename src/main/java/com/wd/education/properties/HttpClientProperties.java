package com.wd.education.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "rest")
public class HttpClientProperties {

  private int connectTimeout;
  private int readTimeout;

}
