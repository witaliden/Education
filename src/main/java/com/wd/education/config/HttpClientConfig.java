package com.wd.education.config;

import com.wd.education.properties.BackendUserProperties;
import com.wd.education.properties.HttpClientProperties;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy
@EnableConfigurationProperties({HttpClientProperties.class, BackendUserProperties.class})
public class HttpClientConfig {

  private final HttpClientProperties httpClientProperties;

  @Autowired
  public HttpClientConfig(final HttpClientProperties httpClientProperties) {
    this.httpClientProperties = httpClientProperties;
  }

  @Bean
  public RestTemplate restTemplate(final RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .setConnectTimeout(Duration.ofMillis(httpClientProperties.getConnectTimeout()))
        .setReadTimeout(Duration.ofMillis(httpClientProperties.getReadTimeout()))
        .build();
  }
}
