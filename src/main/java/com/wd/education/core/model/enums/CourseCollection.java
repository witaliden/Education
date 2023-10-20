package com.wd.education.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.EnumSet;

import static com.wd.education.core.model.enums.CourseType.*;

@AllArgsConstructor
@Getter
public enum CourseCollection {
  TECH(PROGRAMMING, FRAMEWORK, SECURITY),
  SOFT(LANGUAGE, CLIENT);

  private final EnumSet<CourseType> courses;

  CourseCollection(final CourseType... courseTypes) {
    this.courses = EnumSet.noneOf(CourseType.class);
    courses.addAll(Arrays.asList(courseTypes));
  }
}
