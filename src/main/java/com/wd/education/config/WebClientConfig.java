package com.wd.education.config;

import static org.springframework.security.oauth2.client.OAuth2AuthorizationContext.PASSWORD_ATTRIBUTE_NAME;
import static org.springframework.security.oauth2.client.OAuth2AuthorizationContext.USERNAME_ATTRIBUTE_NAME;

import com.wd.education.properties.HttpClientProperties;
import com.wd.education.properties.SecurityOauth2Properties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {

  @Bean
  public OAuth2AuthorizedClientManager authorizedClientManager(
      final ClientRegistrationRepository clientRegistrationRepository,
      final OAuth2AuthorizedClientService authorizedClientService,
      final SecurityOauth2Properties securityOauth2Properties) {
    final var authorizedClientProvider =
        OAuth2AuthorizedClientProviderBuilder.builder()
            .password()
            .build();

    final var authorizedClientManager =
        new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository,
            authorizedClientService);

    authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
    authorizedClientManager.setContextAttributesMapper(
        request -> Map.of(USERNAME_ATTRIBUTE_NAME, securityOauth2Properties.getUsername(),
            PASSWORD_ATTRIBUTE_NAME, securityOauth2Properties.getPassword()));

    return authorizedClientManager;
  }

  @Bean
  public WebClient backendUserWebClient(final OAuth2AuthorizedClientManager authorizedClientManager,
      final HttpClientProperties properties) {
    final var oauth2Client =
        new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
    oauth2Client.setDefaultClientRegistrationId("is4");

    final var strategies = ExchangeStrategies.builder()
        .codecs(clientDefaultCodecsConfigurer -> {
          clientDefaultCodecsConfigurer.defaultCodecs()
              .jackson2JsonEncoder(new Jackson2JsonEncoder());
          clientDefaultCodecsConfigurer.defaultCodecs()
              .jackson2JsonDecoder(new Jackson2JsonDecoder());
          clientDefaultCodecsConfigurer.defaultCodecs().maxInMemorySize(-1);
        }).build();

    final var httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout())
        .responseTimeout(Duration.ofMillis(properties.getReadTimeout()))
        .doOnConnected(conn ->
            conn.addHandlerLast(
                    new ReadTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS))
                .addHandlerLast(
                    new WriteTimeoutHandler(properties.getReadTimeout(), TimeUnit.MILLISECONDS)));

    return WebClient.builder()
        .apply(oauth2Client.oauth2Configuration())
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .exchangeStrategies(strategies)
        .build();
  }
}
