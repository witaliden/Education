package com.wd.education.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportDataServiceProperties {

  private Long securityCourseId;
  private Long gdprAwarenessCourseId;
  private Long owaspAwarenessCourseId;
  private Long adcPolicyCourseId;
  private Long codeOfConductCourseId;

  private String apiGetSecurityResponsesUrl;
  private String apiGetSecurityCoursesUrl;

  private String apiGetGdprResponsesUrl;
  private String apiGetGdprCoursesUrl;

  private String apiGetOwaspResponsesUrl;
  private String apiGetOwaspCoursesUrl;

  private String apiGetAdcPolicyResponsesUrl;
  private String apiGetAdcPolicyCoursesUrl;

  private String apiGetCodeOfConductResponsesUrl;
  private String apiGetCodeOfConductCoursesUrl;

  private String authUrl;
  private String grantType;
  private String clientId;
  private String clientSecret;
  private String resource;
}
