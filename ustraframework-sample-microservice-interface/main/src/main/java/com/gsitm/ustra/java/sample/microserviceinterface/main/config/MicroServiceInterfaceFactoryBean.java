package com.gsitm.ustra.java.sample.microserviceinterface.main.config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MicroServiceInterfaceFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> beanClass;

    private final InvocationHandler invocationHandler;

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { this.beanClass }, invocationHandler);
    }

    @Override
    public Class<T> getObjectType() {
        return this.beanClass;
    }
}
