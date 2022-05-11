package com.gsitm.ustra.java.sample.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.ProxyCachingConfiguration;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableConfigurationProperties({ CustomProxyCachingProperties.class })
@ConditionalOnProperty(name = "ustra.data.redis.cache.bypass", havingValue = "true")
@Slf4j
public class CustomProxyCachingConfiguration {
	// TODO: BeanDefinitionRegistryPostProcessor 를 구현하여, bean을 교체하도록 처리

}
