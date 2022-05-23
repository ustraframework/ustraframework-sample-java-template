package com.gsitm.ustra.java.sample.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({ CustomDualMapperProperties.class })
@ConditionalOnProperty(name = "app.mybatis.dual-mapper", havingValue = "true")
@Configuration
public class CustomDualMapperConfiguration {
	@Bean
	public CustomDualMapperBeanPostProcessor customDualMapperBeanPostProcessor() {
		return new CustomDualMapperBeanPostProcessor();
	}
}
