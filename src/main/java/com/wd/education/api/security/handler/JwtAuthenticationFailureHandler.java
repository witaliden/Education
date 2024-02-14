package com.wd.education.api.security.handler;

/* import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

  private final ObjectMapper objectMapper;
  private final DateTimeService dateTimeService;

  public JwtAuthenticationFailureHandler(
      final ObjectMapper objectMapper,
      final DateTimeService dateTimeService
  ) {
    this.objectMapper = objectMapper;
    this.dateTimeService = dateTimeService;
  }

  @Override
  public void onAuthenticationFailure(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final AuthenticationException exception
  ) throws IOException {
    log.debug("JWT:'" + request.getHeader("Authorization") + "'");
    log.error("Pre-authenticated entry point called, rejecting access: Unauthorized.", exception);

    final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
        exception.getMessage(),
        HttpStatus.UNAUTHORIZED,
        dateTimeService.getLocalDateTime()
    );

    // This is invoked when user tries to access a secured REST resource without supplying any credentials
    // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getOutputStream().println(objectMapper.writeValueAsString(apiErrorResponse));
    response.getOutputStream().flush();
  }
}
*/