package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.gsitm.ustra.java.sample.config.CustomDualBeanPostProcessor.TargetBeanInfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomProxyHandlerClass implements InvocationHandler {

//	private TargetBeanInfo beanInfo;
//
//    @Data
//    public static class TargetBeanInfo {
//    	private Object bean;
//    	private String beanName;
//    	private Class<?> mapperInterface;
//    }
//
//
//	public CustomProxyHandler(TargetBeanInfo beanInfo) {
//		this.beanInfo = beanInfo;
//	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// proxy object의 annotation 확인
		// log.info("===== invoke : {}", this.beanInfo.getBeanName());
		// TODO: DualMapper annotation 이 없는 경우 원본 실행
		// 원본 실행
		Proxy.getInvocationHandler(Proxy.getInvocationHandler(proxy)).invoke(proxy, method, args);

		// TODO: DualMapper annotation 이 있는 경우 DualMapper 실행
		// ApplicationContextProvider.getBean(annotaiton.class).

		return null;
	}
}
