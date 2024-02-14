package com.wd.education.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentIdValidator.class)
@Documented
public @interface StudentExistById {

  String message() default "{StudentExistById.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
