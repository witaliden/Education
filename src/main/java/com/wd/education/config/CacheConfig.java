package com.wd.education.config;

import com.wd.education.core.cache.JwtKeyGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@ConditionalOnProperty(name = "students.cache.enabled", havingValue = "true")
public class CacheConfig {

  @Bean("jwtKeyGenerator")
  public KeyGenerator keyGenerator() {
    return new JwtKeyGenerator();
  }
}
