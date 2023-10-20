package com.wd.education.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Where(clause = "deleted = 'false'")
@Table(name = "courses", indexes = @Index(columnList = "employee_id", name = "idx_course_employee_id"))
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "date", nullable = false)
  private LocalDate date;

  @Column(name = "employee_id", nullable = false)
  private long employeeId;

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

  @Column(name = "country")
  private boolean country;

  @CreatedDate
  @Column(name = "created", nullable = false, updatable = false)
  private LocalDate created;

  @LastModifiedDate
  @Column(name = "updated")
  private LocalDate updated;

  @Column(name = "comment")
  private String comment;
}
