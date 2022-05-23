package com.gsitm.ustra.java.sample.config;

import lombok.Data;

@Data
public class CustomDualMapperVo {
	private Object bean;
	private String beanName;
	private Class<?> mapperInterface;
}
