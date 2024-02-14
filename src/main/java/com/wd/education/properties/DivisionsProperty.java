package com.wd.education.properties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "divisions")
public class DivisionsProperty {

  private List<Long> tech;

  private List<Long> adc;

}
