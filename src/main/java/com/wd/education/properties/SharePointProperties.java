package com.wd.education.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "students.sharepoint")
public class SharePointProperties extends ImportDataServiceProperties {

}
