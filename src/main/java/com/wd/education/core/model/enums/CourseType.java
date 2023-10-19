package com.wd.education.core.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum CourseType {
    PROGRAMMING(1, "Programming course"),
    FRAMEWORK(2, "Framework course"),
    SECURITY(3, "Security course"),
    LANGUAGE(4, "Foreign language course"),
    CLIENT(5, "Client oriented skill");

    public static final Map<Integer, CourseType> COURSE_TYPE = getCourses();

    private final Integer id;
    private final String name;

    private static Map<Integer, CourseType> getCourses() {
        return Arrays.stream(CourseType.values()).collect(
                Collectors.toMap(CourseType::getId, Function.identity()));
    }
}
