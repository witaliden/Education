package com.wd.education.core.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final String LIST_ERROR_MESSAGE = "%s not found";
  private static final String SINGLE_ERROR_MESSAGE = "%s not found with %s: %s";

  public ResourceNotFoundException(final String message) {
    super(LIST_ERROR_MESSAGE.formatted(message));
  }

  public ResourceNotFoundException(final String resourceName, final String fieldName,
      final Object fieldValue) {
    super(SINGLE_ERROR_MESSAGE.formatted(resourceName, fieldName, fieldValue));
  }
}
