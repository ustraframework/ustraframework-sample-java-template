package com.gsitm.ustra.java.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomPostProcessorConfiguration {

	@Bean
	public CustomDualBeanPostProcessor customDualBeanPostProcessor() {
		return new CustomDualBeanPostProcessor();
	}
}
