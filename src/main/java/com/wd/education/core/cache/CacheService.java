package com.wd.education.core.cache;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
@ConditionalOnProperty(name = "students.cache.enabled", havingValue = "true")
public class CacheService {

  private final CacheManager cacheManager;

  @EventListener(ApplicationReadyEvent.class)
  public void invalidateCaches() {
    log.info("Caches invalidating...");

    cacheManager.getCacheNames().stream()
        .map(cacheManager::getCache)
        .map(Optional::ofNullable)
        .peek(mayBeCache -> log.info("Invalidating [{}] cache.",
            mayBeCache.map(Cache::getName).orElse("not found")))
        .forEach(mayBeCache -> mayBeCache.ifPresent(Cache::invalidate));

    log.info("Caches invalidated.");
  }
}