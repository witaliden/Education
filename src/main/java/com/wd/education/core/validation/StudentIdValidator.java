package com.wd.education.core.validation;

import com.wd.education.core.client.StudentClient;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentIdValidator implements ConstraintValidator<StudentExistById, Long> {

  private final StudentClient studentClient;

  @Override
  public boolean isValid(final Long id, final ConstraintValidatorContext context) {
    return id != null || studentClient.existById(id);
  }
}
