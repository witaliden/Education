package com.wd.education.core.repository;

import com.wd.education.core.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends JpaRepository<Course, Long> {

  Optional<Course> findCourseByIdAndEmployeeId(Long courseId, Long employeeId);

  List<Course> findByEmployeeId(Long employeeId);
}
