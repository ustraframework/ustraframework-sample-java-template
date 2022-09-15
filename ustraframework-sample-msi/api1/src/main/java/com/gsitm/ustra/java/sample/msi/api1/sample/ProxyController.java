package com.gsitm.ustra.java.sample.msi.api1.sample;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsitm.ustra.java.sample.msi.api1.sample.JsonRpcRequest.Params;

@RestController
public class ProxyController {
	@Autowired
	private ApplicationContext ac;

	@PostMapping("/api/fo/od/proxy")
	public JsonRpcResponse request(@RequestBody JsonRpcRequest request) {
		final Params params = request.getParams();

		Object[] paramObjects = buildParamObject(params);	// 요청 파라미터 역직렬화 (= 해당 타입의 객체로 생성)
		Object bean = discoveryBean(params);	// 컴포넌트 추출
		Method method = discoveryMethod(params);

		JsonRpcResponse jsonRpcResponse = null;
		try {
			Object resultObject = method.invoke(bean, paramObjects);	// 메소드 실행

			ObjectMapper objectMapper = new ObjectMapper();
			String result = objectMapper.writeValueAsString(resultObject);	// 결과 직렬화 (Object -> String)
			jsonRpcResponse = new JsonRpcResponse();
			jsonRpcResponse.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonRpcResponse;
	}


	private Object[] buildParamObject(Params params) {

		final String[] parameterClassNames = params.getArgumentClassList();
		// Class list 추출
		final List<Class<?>> paramClasses = Stream.of(parameterClassNames).map(each -> {
			try {
				return ac.getClassLoader().loadClass(each);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());

		// 파라미터 값 list
		final String[] arguStringList = params.getArgumentList();

		final Object[] arguList = new Object[arguStringList.length];
		for (int i=0; i<arguStringList.length; i++) {
			Class<?> arguClass = paramClasses.get(i);
			String arguString = arguStringList[i];	// json 형식의 String

			ObjectMapper objectMapper = new ObjectMapper();
			Object argu = null;
	        try {
	        	// 역직렬화 : 파라미터 값(json 형식의 String)을 파라미터 타입(Class)의 객체로 생성
	        	argu = objectMapper.readValue(arguString, arguClass);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
			arguList[i] = argu;
		}

		return arguList;
	}

	private Method discoveryMethod(Params params) {

		final String methodName = params.getMethodName();
		final String[] parameterNames = params.getArgumentNameList();
		final String[] parameterClassNames = params.getArgumentClassList();
		final List<Class<?>> paramClasses = Stream.of(parameterClassNames).map(each -> {
			Class<?> result  = null;
			try {
				result = ac.getClassLoader().loadClass(each);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return result;
		}).collect(Collectors.toList());
		final String className = params.getClassName();
		Class<?> clazz = null;
		try {
			clazz = ac.getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
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
