package com.wd.education.core.service.course;

import com.wd.education.api.dto.CourseRequestDto;
import com.wd.education.api.dto.CourseResponseDto;
import java.util.List;

public interface CourseService {

  CourseResponseDto get(Long courseId, Long employeeId);

  List<CourseResponseDto> getAll(long employeeId);

  CourseResponseDto save(CourseRequestDto courseRequestDto);

  CourseResponseDto update(long courseId, CourseRequestDto courseRequestDto);

  void delete(long courseId);
}
