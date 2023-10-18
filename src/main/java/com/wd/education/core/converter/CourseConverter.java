package com.wd.education.core.converter;

import com.wd.education.api.dto.CourseRequestDto;
import com.wd.education.api.dto.CourseResponseDto;
import com.wd.education.core.model.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseConverter {
    Course toCourseEntity(CourseRequestDto courseRequestDto);

    CourseResponseDto toCourseResponseDto(Course course);
}
