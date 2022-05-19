package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.Proxy;

public class CustomMapperFactory {

	public static <T> T getMapper(Class<T> clazz) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new CustomProxyHandlerClass());
	}


}
