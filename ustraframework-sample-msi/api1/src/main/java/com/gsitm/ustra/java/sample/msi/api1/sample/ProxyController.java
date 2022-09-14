package com.gsitm.ustra.java.sample.msi.api1.sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsitm.ustra.java.sample.msi.api1.sample.JsonRpcRequest.Params;

@RestController
public class ProxyController {
	@Autowired
	private ApplicationContext ac;

	@PostMapping("/api/fo/od/proxy")
	public JsonRpcResponse request(@RequestBody JsonRpcRequest request) {
		final Params params = request.getParams();
		Object bean = discoveryBean(params);
		Method method = discoveryMethod(params);
		Object[] paramObjects = buildParamObject(params);
		Object result = null;
		JsonRpcResponse jsonRpcResponse = null;
		try {
			result = method.invoke(bean, paramObjects);
			jsonRpcResponse = new JsonRpcResponse();
			jsonRpcResponse.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonRpcResponse;
	}

	// json(Params) -> object
	private Object[] buildParamObject(Params params) {

		return new Object[] {"Y"};
	}

	private Method discoveryMethod(Params params) {
		final String className = params.getClassName();
		Class<?> clazz = null;
		try {
			clazz = ac.getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		final String methodName = params.getMethodName();
		final String[] parameterNames = params.getArgumentNameList();
		final String[] parameterClassNames = params.getArgumentClassList();
		final List<Class<?>> paramClasses = Stream.of(parameterClassNames).map(each -> {
			try {
				return ac.getClassLoader().loadClass(each);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		Method[] methods = clazz.getDeclaredMethods();
		Method targetMethod = null;
		for(Method each : methods) {
			String name = each.getName();
			if (!StringUtils.equals(name, methodName)) {
				continue;
			}

			if (each.getParameterCount() != parameterNames.length) {
				continue;
			}

			List<Class<?>> methodParamTypeList = Stream.of(each.getParameterTypes()).collect(Collectors.toList());
			if (!ListUtils.isEqualList(methodParamTypeList, paramClasses)) {
				continue;
			}

			targetMethod = each;
			break;
		}

		if (targetMethod == null) {
			// 404 응답
			throw new RuntimeException();
		}

		return targetMethod;
	}

	private Object discoveryBean(Params params){
		final String className = params.getClassName();
		Class<?> clazz = null;
		Object bean = null;
		try {
			clazz = ac.getClassLoader().loadClass(className);
			bean = ac.getBean(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return bean;
	}



}
