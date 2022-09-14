package com.gsitm.ustra.java.sample.msi.main.client;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gsitm.ustra.java.sample.msi.main.annotations.MicroServiceInterfaceAnnotation;
import com.gsitm.ustra.java.sample.msi.main.model.MicroServiceInterfaceModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultMicroServiceInterfaceClient implements MicroServiceInterfaceClient {
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public Object request(Method method, Object[] args) {
        final MicroServiceInterfaceAnnotation annotation = method.getDeclaringClass().getAnnotation(MicroServiceInterfaceAnnotation.class);
        final String baseUrl = annotation.value();
        final String className = method.getDeclaringClass().getName();
        final String methodName = method.getName();
        final List<String> parameterList = Arrays.stream(method.getParameterTypes()).map(each -> each.getName()).collect(Collectors.toList());

        MicroServiceInterfaceModel param = new MicroServiceInterfaceModel();
        param.setClassName(className);
        param.setMethodName(methodName);
        param.setParameterNameList(parameterList);
        RequestEntity<MicroServiceInterfaceModel> entity = null;
        try {
            entity = new RequestEntity<>(param, HttpMethod.POST, new URI("http://localhost:8080/api/fo/od/msi"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);

        return baseUrl + " " + className + "." + methodName + "#" + StringUtils.join(parameterList, ",");
	}

}
