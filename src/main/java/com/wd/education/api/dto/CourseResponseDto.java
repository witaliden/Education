package com.wd.education.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponseDto {

  private Long id;
  private String title;
  private LocalDate date;
  private String country;
  private String comment;

}
