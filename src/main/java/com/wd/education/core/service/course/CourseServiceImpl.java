package com.wd.education.core.service.course;

import com.wd.education.api.dto.CourseRequestDto;
import com.wd.education.api.dto.CourseResponseDto;
import com.wd.education.core.service.converter.CourseConverter;
import com.wd.education.core.exception.ResourceNotFoundException;
import com.wd.education.core.model.Course;
import com.wd.education.core.repository.CoursesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CourseServiceImpl implements CourseService {

  private final CoursesRepository repository;
  private final CourseConverter converter;

  @Override
  @Transactional
  public CourseResponseDto save(final CourseRequestDto courseRequestDto) {
    final var course = converter.toCourseEntity(courseRequestDto);
    return converter.toCourseResponseDto(repository.save(course));
  }

  @Override
  @Transactional(readOnly = true)
  public CourseResponseDto get(final Long courseId, final Long employeeId) {
    final var course = repository.findCourseByIdAndEmployeeId(courseId, employeeId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Course with id [%s] for employee with id [%s]".formatted(courseId, employeeId)));
    return converter.toCourseResponseDto(course);
  }

  @Override
  @Transactional(readOnly = true)
  public List<CourseResponseDto> getAll(final long employeeId) {
    return repository.findByEmployeeId(employeeId).stream()
        .map(converter::toCourseResponseDto).toList();
  }

  @Override
  @Transactional
  public CourseResponseDto update(final long courseId, final CourseRequestDto courseRequestDto) {
    final var courseFromDb = getCourseFromDataSource(courseId);
    updateCourseFields(courseFromDb, courseRequestDto);
    final var updatedCourse = repository.save(courseFromDb);
    return converter.toCourseResponseDto(updatedCourse);
  }

  @Override
  @Transactional
  public void delete(final long courseId) {
    final var course = getCourseFromDataSource(courseId);
    course.setDeleted(true);
    repository.save(course);
  }

  private Course getCourseFromDataSource(final Long courseId) {
    return repository.findById(courseId)
        .orElseThrow(
            () -> new ResourceNotFoundException("Course", "id", courseId));
  }

  private void updateCourseFields(final Course source, final CourseRequestDto target) {
    //TODO: update the method after dtos end entity
  }
}
