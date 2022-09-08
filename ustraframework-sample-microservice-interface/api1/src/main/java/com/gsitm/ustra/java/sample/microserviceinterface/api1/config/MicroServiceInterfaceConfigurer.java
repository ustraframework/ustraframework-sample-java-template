package com.gsitm.ustra.java.sample.microserviceinterface.api1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gsitm.ustra.java.sample.microserviceinterface.main.annotations.MicroServiceInterfaceAnnotation;
import com.gsitm.ustra.java.sample.microserviceinterface.main.client.DefaultMicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.microserviceinterface.main.config.MicroServiceInterfaceBeanDefinitionRegistryPostProcessor;
import com.gsitm.ustra.java.sample.microserviceinterface.main.config.MicroServiceInterfaceFactoryBean;

@Configuration
public class MicroServiceInterfaceConfigurer {
	@Bean
	public MicroServiceInterfaceBeanDefinitionRegistryPostProcessor microserviceInterfaceBeanDefinitionRegistryPostProcessor() {
		List<String> basePackageList = new ArrayList<String>();
		basePackageList.add("com.gsitm.ustra.java.sample.microserviceinterface.api1");

		return new MicroServiceInterfaceBeanDefinitionRegistryPostProcessor(
					basePackageList,
					MicroServiceInterfaceAnnotation.class,
					MicroServiceInterfaceFactoryBean.class,
					// 컴포넌트????
					new DefaultMicroServiceInterfaceClient()
				);
	}
}
