package com.gsitm.ustra.java.sample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(value=CustomProxyCachingProperties.PREFIX, ignoreUnknownFields = true)
public class CustomProxyCachingProperties {
	public static final String PREFIX = "ustra.data.redis";

	private Cache cache;
	private Connection connection;

	@Data
	public static class Cache {

		/**
		 * 캐시 매니저 활성화 여부
		 */
		private boolean enabled;

		/**
		 * 캐시 설정 무시 여부
		 */
		private boolean bypass;

		/**
		 * 기본 유지 초수
		 */
		private Integer expireSeconds = 600;
	}

	@Data
	public static class Connection {

		/**
		 * 호스트 명
		 */
		private String hostName;

		/**
		 * 포트
		 */
		private Integer port;

		/**
		 * 비밀번호
		 */
		private String password;
	}

}
