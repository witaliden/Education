package com.wd.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.wd.education")
public class EducationApplication {

  public static void main(String[] args) {
    SpringApplication.run(EducationApplication.class, args);
  }

}
