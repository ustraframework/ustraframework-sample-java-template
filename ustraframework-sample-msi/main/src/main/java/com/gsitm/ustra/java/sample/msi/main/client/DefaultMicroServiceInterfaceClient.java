package com.gsitm.ustra.java.sample.msi.main.client;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsitm.ustra.java.sample.msi.main.annotations.MicroServiceInterfaceAnnotation;
import com.gsitm.ustra.java.sample.msi.main.model.JsonRpcRequest;
import com.gsitm.ustra.java.sample.msi.main.model.JsonRpcRequest.Params;
import com.gsitm.ustra.java.sample.msi.main.model.JsonRpcResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultMicroServiceInterfaceClient implements MicroServiceInterfaceClient {
	private RestTemplate restTemplate = new RestTemplate();

	private final Map<String, String> baseUriMap;

	@Override
	public Object request(Method method, Object[] args) {
        final MicroServiceInterfaceAnnotation annotation = method.getDeclaringClass().getAnnotation(MicroServiceInterfaceAnnotation.class);	// 서비스 인터페이스의 어노테이션
        final String annotationValue = annotation.value();	// 어노테이션 value의 서버 식별값
        final String baseUrl = baseUriMap.get(annotationValue);	// annotationValue으로 yml에서 URL을 가져와야함
        final String className = method.getDeclaringClass().getName();	// 서비스 인터페이스명
        final String methodName = method.getName();	// 메소드명
        final String[] argumentClassList = Arrays.stream(method.getParameterTypes()).map(each -> each.getName()).toArray(String[]::new);	// 파라미터 타입명 리스트
		final String[] arguStringList = new String[args.length];	// 파라미터 값 list
		for (int i=0; i<args.length; i++) {
			Object arguObject = args[i];

			ObjectMapper objectMapper = new ObjectMapper();
			String argu = null;
	        try {
	        	argu = objectMapper.writeValueAsString(arguObject);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        arguStringList[i] = argu;
		}

        JsonRpcRequest request = new JsonRpcRequest();
        Params params = new Params();
        params.setClassName(className);
        params.setMethodName(methodName);
        params.setArgumentClassList(argumentClassList);
        params.setArgumentList(arguStringList);
        request.setParams(params);

        RequestEntity<JsonRpcRequest> entity = null;
        try {
            entity = new RequestEntity<>(request, HttpMethod.POST, new URI(baseUrl));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // From ProxyController
        ResponseEntity<JsonRpcResponse> response = restTemplate.exchange(entity, JsonRpcResponse.class);
        String resultString = response.getBody().getResult(); // 역직렬화 : response (응답 json String) -> ex. List<String>
		ObjectMapper objectMapper = new ObjectMapper();
		Object result = null;
    	// 역직렬화 : 파라미터 값(json 형식의 String)을 파라미터 타입(Class)의 객체로 생성
    	try {
			result = objectMapper.readValue(resultString, method.getReturnType());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
	}

}
