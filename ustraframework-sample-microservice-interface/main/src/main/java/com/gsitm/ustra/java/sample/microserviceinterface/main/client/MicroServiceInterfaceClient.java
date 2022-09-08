package com.gsitm.ustra.java.sample.microserviceinterface.main.client;

import java.lang.reflect.Method;

public interface MicroServiceInterfaceClient {
	Object request(Method method, Object[] args);
}
