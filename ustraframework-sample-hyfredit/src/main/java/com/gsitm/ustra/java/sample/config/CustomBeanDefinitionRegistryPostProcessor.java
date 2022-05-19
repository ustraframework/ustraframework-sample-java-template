package com.gsitm.ustra.java.sample.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.bf(beanFactory);
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		this.bdr(registry);
	}

	private void bf(ConfigurableListableBeanFactory beanFactory) {
		log.info("===== postProcessBeanFactory : ");
	}

	private void bdr(BeanDefinitionRegistry registry) {

		for (String name : registry.getBeanDefinitionNames()) {
			log.info("===== BeanDefinitionRegistry : {}", name);
		}

		log.info("===== postProcessBeanDefinitionRegistry : ");
	}

}
