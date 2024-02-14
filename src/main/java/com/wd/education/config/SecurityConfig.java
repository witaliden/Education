package com.wd.education.config;/*

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

  private final SecurityProperties securityProperties;
  private final RequestMatcher protectedUrls;
  private final CustomPermissionEvaluator customPermissionEvaluator;
  private final ObjectMapper jacksonObjectMapper;
  private final PermissionService permissionService;
  private final DateTimeService dateTimeService;

  @Autowired
  public SecurityConfig(
      final SecurityProperties securityProperties,
      final CustomPermissionEvaluator customPermissionEvaluator,
      final ObjectMapper jacksonObjectMapper,
      final PermissionService permissionService,
      final DateTimeService dateTimeService
  ) {
    this.securityProperties = securityProperties;
    this.customPermissionEvaluator = customPermissionEvaluator;
    this.jacksonObjectMapper = jacksonObjectMapper;
    this.permissionService = permissionService;
    this.dateTimeService = dateTimeService;
    this.protectedUrls = new NegatedRequestMatcher(securityProperties.getPublicUrls());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf().disable()
        .authenticationManager(authenticationManager())
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
        .and()
        .authorizeRequests(auth -> auth
            .requestMatchers(protectedUrls).authenticated()
            .expressionHandler(permissionHandler()))
        .addFilterBefore(authenticationTokenFilterBean(),
            UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new NullAuthenticatedSessionStrategy();
  }

  @Bean
  public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
    final var authenticationTokenFilter = new JwtAuthenticationTokenFilter(protectedUrls);

    authenticationTokenFilter.setAuthenticationManager(authenticationManager());
    authenticationTokenFilter
        .setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
    authenticationTokenFilter
        .setAuthenticationFailureHandler(
            new JwtAuthenticationFailureHandler(jacksonObjectMapper, dateTimeService));

    return authenticationTokenFilter;
  }

  private AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider()));
  }

  private SecurityExpressionHandler<FilterInvocation> permissionHandler() {
    final var handler = new DefaultWebSecurityExpressionHandler();
    handler.setPermissionEvaluator(customPermissionEvaluator);
    return handler;
  }

  private JwtAuthenticationProvider jwtAuthenticationProvider() {
    return new JwtAuthenticationProvider(new WithoutVerificationTokenVerifier(),
        securityProperties.getPermissionsUrl(), permissionService);
  }

}
*/
