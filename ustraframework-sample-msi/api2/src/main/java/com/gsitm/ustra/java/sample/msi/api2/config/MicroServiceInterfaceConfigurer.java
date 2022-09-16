package com.gsitm.ustra.java.sample.msi.api2.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gsitm.ustra.java.sample.msi.main.annotations.MicroServiceInterfaceAnnotation;
import com.gsitm.ustra.java.sample.msi.main.client.DefaultMicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.msi.main.client.MicroServiceInterfaceClient;
import com.gsitm.ustra.java.sample.msi.main.config.MicroServiceInterfaceBeanDefinitionRegistryPostProcessor;
import com.gsitm.ustra.java.sample.msi.main.config.MicroServiceInterfaceFactoryBean;

@Configuration
public class MicroServiceInterfaceConfigurer {
	@Bean
	private DefaultMicroServiceInterfaceClient microServiceInterfaceClient() {
		final HashMap<String, String> baseUriMap = new HashMap<>();
		baseUriMap.put("", "");	// <<<<<<<<<<<<<<
		return new DefaultMicroServiceInterfaceClient(baseUriMap);
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

