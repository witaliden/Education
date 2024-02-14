package com.wd.education.api.controller;

import com.wd.education.api.annotation.ApiResponseWithContent;
import com.wd.education.api.annotation.ApiResponseWithoutContent;
import com.wd.education.api.dto.CourseRequestDto;
import com.wd.education.api.dto.CourseResponseDto;
import com.wd.education.core.service.course.CourseServiceImpl;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/courses")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class CoursesController {

  private final CourseServiceImpl courseService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  @ApiResponseWithContent
  public CourseResponseDto createCourse(
      @Valid @RequestBody final CourseRequestDto courseRequestDto) {
    log.debug("Create course for employee with id {{}}", courseRequestDto.getEmployeeId());
    return courseService.save(courseRequestDto);
  }

  @GetMapping(value = "/{courseId}")
  @ApiResponseWithContent
  public CourseResponseDto getCourse(
      @PathVariable("courseId") final long courseId, @RequestParam final long employeeId) {
    log.debug("Requested course with id:{} for employee with id [{}]", courseId, employeeId);
    return courseService.get(courseId, employeeId);
  }

  @GetMapping
  @ApiResponseWithContent
  public List<CourseResponseDto> getCourses(@RequestParam("employeeId") final long employeeId) {
    log.debug("Requested course for employee with id: {}", employeeId);
    return courseService.getAll(employeeId);
  }

  @PutMapping(value = "/{courseId}")
  @ApiResponseWithContent
  public CourseResponseDto updateCourse(
      @PathVariable(name = "courseId") final long courseId,
      @Valid @RequestBody final CourseRequestDto courseRequestDto) {
    log.debug("Update course with id [{}] with courseRequestDto: {}", courseId, courseRequestDto);
    return courseService.update(courseId, courseRequestDto);
  }

  @DeleteMapping(value = "/{courseId}")
  @ApiResponseWithoutContent
  public void deleteCourse(@PathVariable(name = "courseId") final long courseId) {
    log.debug("Delete course with id [{}]", courseId);
    courseService.delete(courseId);
  }
}