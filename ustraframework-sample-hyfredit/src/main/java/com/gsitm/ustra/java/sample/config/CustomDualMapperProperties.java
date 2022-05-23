package com.gsitm.ustra.java.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(value=CustomDualMapperProperties.PREFIX, ignoreUnknownFields = true)
public class CustomDualMapperProperties {
	public static final String PREFIX = "app.mybatis";

	private Boolean dualMapper;
}
