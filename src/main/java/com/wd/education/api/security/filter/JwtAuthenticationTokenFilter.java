package com.wd.education.api.security.filter;/*

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

  private static final String TOKEN_PREFIX = "Bearer";
  private static final String AUTHORIZATION_HEADER = "Authorization";

  public JwtAuthenticationTokenFilter(
      final RequestMatcher requiresAuthenticationRequestMatcher
  ) {
    super(requiresAuthenticationRequestMatcher);
  }

  @Override
  public Authentication attemptAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response
  ) {
    final String authToken = getJwtFromRequest(request);
    final JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

    return getAuthenticationManager().authenticate(authRequest);
  }

  @Override
  protected void successfulAuthentication(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final FilterChain chain,
      final Authentication authResult
  ) throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }

  */
/**
 * Retrieves jwt token from request without any validation. NOTE: Request can contain jws token, on
 * this case we don't validate token, we just retrieve payload.
 *
 * @param request provide request information for HTTP servlets
 * @return JWT token
 *//*

  private String getJwtFromRequest(final HttpServletRequest request) {
    final String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

    if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith(TOKEN_PREFIX)) {
      throw new JwtVerificationException("No JWT token found in request headers");
    }

    return bearerToken.substring(TOKEN_PREFIX.length()).trim();
  }
}
*/
