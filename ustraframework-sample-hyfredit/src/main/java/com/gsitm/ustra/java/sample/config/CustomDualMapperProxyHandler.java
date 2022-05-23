package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.gsitm.ustra.java.core.utils.ApplicationContextProvider;
import com.gsitm.ustra.java.sample.config.annotations.DualMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDualMapperProxyHandler<T> implements InvocationHandler {

	private CustomDualMapperVo beanInfo;

	public CustomDualMapperProxyHandler(CustomDualMapperVo beanInfo) {
		this.beanInfo = beanInfo;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// DualMapper annotation 확인
		if (this.beanInfo.getMapperInterface().getAnnotation(DualMapper.class) != null) {
			// DualMapper annotation이 있는 경우 DualMapper 값의 bean 실행
			Object to = ApplicationContextProvider.getBean(
					this.beanInfo.getMapperInterface().getAnnotation(DualMapper.class).beanName(),
					this.beanInfo.getMapperInterface().getAnnotation(DualMapper.class).clazz()
			);
			CustomDualMapperProxyHandler<T> cph = (CustomDualMapperProxyHandler<T>)Proxy.getInvocationHandler(to);
			return Proxy.getInvocationHandler(cph.beanInfo.getBean()).invoke(proxy, method, args);
		}
		else {
			// DualMapper annotation 이 없는 경우 원본 실행
			return Proxy.getInvocationHandler(this.beanInfo.getBean()).invoke(proxy, method, args);
		}

	}

}
