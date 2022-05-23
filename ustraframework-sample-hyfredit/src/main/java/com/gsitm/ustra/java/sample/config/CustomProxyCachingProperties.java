package com.gsitm.ustra.java.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(value=CustomProxyCachingProperties.PREFIX, ignoreUnknownFields = true)
public class CustomProxyCachingProperties {
	public static final String PREFIX = "app.redis";

	private Boolean bypass;
}
