package com.wd.education.api.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasPermission(null, 'students.create_course')"
    + " AND (@studentVerifier.checkAssign(#studentId)"
    + " OR @studentVerifier.isHr()"
    + " OR @studentVerifier.isRoot()"
    + " OR @studentVerifier.admin(#studentId))")
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EducationAuthorization {

}
