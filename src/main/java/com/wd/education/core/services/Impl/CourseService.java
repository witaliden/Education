package com.wd.education.core.services.Impl;

import com.wd.education.api.dto.CourseRequestDto;
import com.wd.education.api.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

  CourseResponseDto get(long courseId, long employeeId);

  List<CourseResponseDto> getAll(long employeeId);

  CourseResponseDto save(CourseRequestDto courseRequestDto);

  CourseResponseDto update(long courseId, CourseRequestDto courseRequestDto);

  void delete(long courseId);
}
