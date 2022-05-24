package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.gsitm.ustra.java.core.utils.ApplicationContextProvider;
import com.gsitm.ustra.java.sample.config.annotations.DualMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDualMapperProxyHandler<T> implements InvocationHandler {

	private final CustomDualMapperVo beanInfo;
	private static final Map<String, Object> mapperCache = new ConcurrentHashMap<String, Object>();

	public CustomDualMapperProxyHandler(CustomDualMapperVo beanInfo) {
		this.beanInfo = beanInfo;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		String methodName = proxy.getClass().getName() + "." + method.getName();

		// 캐싱여부 체크하여 바로 실행
		if (this.mapperCache.get(methodName) != null) {
			return Proxy.getInvocationHandler(this.mapperCache.get(methodName)).invoke(proxy, method, args);
		}

		// DualMapper 어노테이션 정보
		DualMapper dm = this.beanInfo.getMapperInterface().getAnnotation(DualMapper.class);

		// DualMapper annotation 확인
		if (dm != null) {
			// DualMapper annotation이 있는 경우 DualMapper 값의 bean proxyHandler 실행
			Object to = ApplicationContextProvider.getBean(dm.value());
			CustomDualMapperProxyHandler<T> cph = (CustomDualMapperProxyHandler<T>)Proxy.getInvocationHandler(to);

			// DualMapper 대상 인터페이스에 동일한 method가 있는 경우 해당 프록시 실행
			for(Method targetMethod : cph.beanInfo.getMapperInterface().getMethods()) {
				// DualMapper 대상 인터페이스와 method 명이 같으면
				if (targetMethod.getName().equals(method.getName())) {
					// mapperProxy 캐시 처리
					this.mapperCache.put(methodName, cph.beanInfo.getBean());
					// DualMapper 대상 bean의 mapperProxy method 실행
					return Proxy.getInvocationHandler(cph.beanInfo.getBean()).invoke(proxy, method, args);

				}
			}
		}


		// mapperProxy 캐시 처리
		this.mapperCache.put(methodName, this.beanInfo.getBean());
		// DualMapper annotation 이 없거나, 해당 메소드가 없는 경우 원본 실행
		return Proxy.getInvocationHandler(this.beanInfo.getBean()).invoke(proxy, method, args);

	}

}
