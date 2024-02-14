package com.wd.education.handler;

import static java.lang.String.format;
import static org.springframework.util.CollectionUtils.isEmpty;

import com.wd.education.api.dto.error.ApiErrorResponse;
import com.wd.education.api.security.exception.PermissionException;
import com.wd.education.core.exception.ImportFailedException;
import com.wd.education.core.exception.ResourceNotFoundException;
import java.nio.file.AccessDeniedException;
import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ApiErrorResponse handleValidationException(final ValidationException ex) {
    log.error("[EXCEPTION HANDLER EDUCATION] Error during request validation", ex);

    return new ApiErrorResponse("Error during request validation",
        HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
  }

  @ExceptionHandler({Exception.class,
      UnexpectedException.class})
  public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
    log.error("[EXCEPTION HANDLER EDUCATION] An internal error occurred: ", ex);

    return getObjectResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(JpaObjectRetrievalFailureException.class)
  public ResponseEntity<Object> handleJpaObjectRetrievalFailureException(
      final JpaObjectRetrievalFailureException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Object retrieval failure [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class,
      IllegalStateException.class})
  public ResponseEntity<Object> handleConstraintViolationException(final RuntimeException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Bad request [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ImportFailedException.class)
  public ResponseEntity<Object> handleImportFailedException(final RuntimeException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Bad request [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ResourceNotFoundException.class})
  public ResponseEntity<Object> handleResourceNotFoundException(
      final ResourceNotFoundException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Resource not found: [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<Object> handleAccessDeniedException(
      final AccessDeniedException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Access denied: [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(PermissionException.class)
  public ResponseEntity<Object> handlePermissionException(
      final PermissionException ex,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Unauthorized: [{}]", ex.getMessage());

    return getObjectResponseEntity(ex, request, HttpStatus.FORBIDDEN);
  }

  public ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    log.warn("[EXCEPTION HANDLER EDUCATION] Bad request: [{}]", ex.getMessage());
    final BindingResult bindingResult = ex.getBindingResult();
    String errorMsg = null;
    if (!isEmpty(bindingResult.getFieldErrors())) {
      errorMsg = bindingResult.getFieldErrors().stream()
          .map(error -> format("The parameter '%s' of value '%s' is invalid by reason: %s",
              error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
          .collect(Collectors.joining("; "));
    } else if (!isEmpty(bindingResult.getAllErrors())) {
      errorMsg = bindingResult.getAllErrors().stream()
          .map(error -> format("Object %s has error: %s",
              error.getObjectName(), error.getDefaultMessage()))
          .collect(Collectors.joining("; "));
    }
    final ApiErrorResponse errorResponse =
        new ApiErrorResponse(errorMsg, HttpStatus.BAD_REQUEST, LocalDateTime.now());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      final HttpMessageNotReadableException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    final Throwable mostSpecificCause = ex.getMostSpecificCause();
    String message = mostSpecificCause.getMessage();

    log.warn("[EXCEPTION HANDLER EDUCATION] Bad request: [{}]", message, ex);
    return getObjectResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Object> getObjectResponseEntity(final Exception ex,
      final WebRequest request,
      final HttpStatus httpStatus) {
    return handleExceptionInternal(
        ex,
        new ApiErrorResponse(ex.getMessage(), httpStatus, LocalDateTime.now()),
        new HttpHeaders(),
        httpStatus,
        request
    );
  }
}
