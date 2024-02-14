package com.wd.education.core.client;

import com.wd.education.api.dto.CourseResponseDto;
import com.wd.education.core.model.enums.CourseType;
import java.util.List;
import java.util.Set;

public interface StudentClient {

  CourseResponseDto getStudentCoursesDtoByType(Set<CourseType> studentStatusTypes, int size,
      int page);

  boolean existById(Long id);

  List<CourseResponseDto> findStudentEmails(Set<String> emails);

  CourseResponseDto getStudentImportDto(int size, int page);

}
