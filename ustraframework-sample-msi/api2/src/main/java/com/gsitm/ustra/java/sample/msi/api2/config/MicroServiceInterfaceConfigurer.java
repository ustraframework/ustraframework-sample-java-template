package com.gsitm.ustra.java.sample.msi.api2.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.gsitm.ustra.java.core.utils.UstraEnvironmentUtils;
import com.gsitm.ustra.java.sample.msi.main.annotations.MicroServiceInterfaceAnnotation;
import com.gsitm.ustra.java.sample.msi.main.client.DefaultMicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.msi.main.client.MicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.msi.main.config.MicroServiceInterfaceBeanDefinitionRegistryPostProcessor;
import com.gsitm.ustra.java.sample.msi.main.config.MicroServiceInterfaceFactoryBean;

@EnableConfigurationProperties(BaseUrlProperties.class)
@Configuration
public class MicroServiceInterfaceConfigurer implements EnvironmentAware{
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;

	}

	@Bean
	public DefaultMicroServiceInterfaceClient microServiceInterfaceClient() {
		BaseUrlProperties baseUrlProperties = UstraEnvironmentUtils.bindProperties(BaseUrlProperties.class, BaseUrlProperties.PREFIX, environment);
		return new DefaultMicroServiceInterfaceClient(baseUrlProperties.getProxyUri());
	}

	@Bean
	public MicroServiceInterfaceBeanDefinitionRegistryPostProcessor microserviceInterfaceBeanDefinitionRegistryPostProcessor(MicroServiceInterfaceClient microServiceInterfaceClient) {
		List<String> basePackageList = new ArrayList<String>();
		basePackageList.add("com.gsitm.ustra.java.sample.msi");

		return new MicroServiceInterfaceBeanDefinitionRegistryPostProcessor(
					basePackageList,
					MicroServiceInterfaceAnnotation.class,
					MicroServiceInterfaceFactoryBean.class,
					microServiceInterfaceClient
				);
	}



}

