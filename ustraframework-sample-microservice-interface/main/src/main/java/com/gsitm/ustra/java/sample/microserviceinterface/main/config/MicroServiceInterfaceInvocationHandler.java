package com.gsitm.ustra.java.sample.microserviceinterface.main.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.gsitm.ustra.java.sample.microserviceinterface.main.client.MicroServiceInterfaceClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MicroServiceInterfaceInvocationHandler implements InvocationHandler {
    private final MicroServiceInterfaceClient microserviceInterfaceClient;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return microserviceInterfaceClient.request(method, args);
    }
}
