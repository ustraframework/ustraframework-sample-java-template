package com.gsitm.ustra.java.sample.properties;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = AWSProperties.PREFIX, ignoreInvalidFields = true, ignoreUnknownFields = true)
public class AWSProperties {
	public static final String PREFIX = "cloud.aws";

	private Map<String, String> credentials;

	private String regionName;

	private S3 s3;

	@Data
	public static class S3{
		/**
		 * AWS S3 bucket 목록
		 */
		private Map<String, String> bucketType;
	}
}
