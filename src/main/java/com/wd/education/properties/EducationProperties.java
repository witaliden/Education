package com.wd.education.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "students.education")
public class EducationProperties {

  private String sharepointResponsesUrl;
  private String sharepointCoursesUrl;

}