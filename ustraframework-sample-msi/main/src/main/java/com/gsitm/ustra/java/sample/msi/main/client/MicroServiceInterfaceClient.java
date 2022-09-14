package com.gsitm.ustra.java.sample.msi.main.client;

import java.lang.reflect.Method;

public interface MicroServiceInterfaceClient {
	Object request(Method method, Object[] args);
}
