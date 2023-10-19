package com.wd.education.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class CourseRequestDto {

    @NotEmpty
    @Size(max = 255)
    private String title;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotNull
    @Positive
    private Long employeeId;

    @Size(max = 255)
    private String country;

    @Size(max = 255)
    private String comment;
}
