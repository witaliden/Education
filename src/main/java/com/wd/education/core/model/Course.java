package com.wd.education.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "courses", indexes = @Index(columnList = "employee_id", name = "idx_courses_employee_id"))
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "employee_id", nullable = false)
    private long employeeId;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}
