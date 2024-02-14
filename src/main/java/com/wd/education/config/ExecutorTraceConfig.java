package com.wd.education.config;

import brave.baggage.BaggageFields;
import brave.baggage.CorrelationScopeConfig;
import brave.baggage.CorrelationScopeCustomizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ExecutorTraceConfig {

  private static final int MIN_THREADS = 0;
  private static final int MAX_THREADS = 1;
  private static final int KEEP_ALIVE = 0;
  private static final int QUEUE_SIZE = 10;

  private final BeanFactory beanFactory;

  @Bean
  public Executor traceableExecutor() {
    final var executor = new ThreadPoolExecutor(MIN_THREADS, MAX_THREADS, KEEP_ALIVE,
        TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(QUEUE_SIZE));

    return new LazyTraceExecutor(beanFactory, executor);
  }

  @Bean
  public CorrelationScopeCustomizer createCorrelationScopeCustomizer() {
    return builder -> builder.add(
        CorrelationScopeConfig.SingleCorrelationField.create(BaggageFields.PARENT_ID));
  }
}
