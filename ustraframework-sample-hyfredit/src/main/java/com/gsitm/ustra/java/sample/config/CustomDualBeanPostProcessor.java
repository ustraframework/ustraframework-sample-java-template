package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.apache.ibatis.binding.MapperProxy;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ClassUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDualBeanPostProcessor implements DestructionAwareBeanPostProcessor {

	@Override
    public void postProcessBeforeDestruction(Object bean, String beanName)
      throws BeansException {
        this.process(bean, beanName);
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
        this.process(bean,  beanName);
        return bean;
    }

	private void process(Object bean, String beanName) {
        // Object proxy = this.getTargetObject(bean);
        log.info("===== beanName : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
        // MapperFactoryBean.getObjectType

        if ("sampleTemplateMapper".equals(beanName)) {
//        	log.info("isJdkDynamicProxy : {}", AopUtils.isJdkDynamicProxy(bean));
//        	log.info("isCglibProxy : {}", AopUtils.isCglibProxy(bean));
        	log.info("isProxyClass : {}", Proxy.isProxyClass(bean.getClass()));


        }

        if (Proxy.isProxyClass(bean.getClass()) ) {
        	InvocationHandler handler = Proxy.getInvocationHandler(bean);
        	if (handler instanceof MapperProxy) {
        		MapperProxy mapperProxy = (MapperProxy)handler;
        		try {
					Field field = mapperProxy.getClass().getDeclaredField("mapperInterface");
					field.setAccessible(true);


					Class<?> mapperInterfaceClass = (Class<?>)field.get(mapperProxy);
					log.info("mapperInterfaceClass : {}", mapperInterfaceClass);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        }

//        if (bean instanceof MapperProxy) {
//        	Object targetObject = this.getTargetObject(bean);
//        	log.warn("targetObject : {}", targetObject.getClass());
//        }

//        org.apache.ibatis.binding.MapperProxy
//
//        if (proxy.getClass().getName() == "org.mybatis.spring.mapper.MapperFactoryBean") {
//        	MapperFactoryBean mfb = (MapperFactoryBean) proxy;
//
//        	log.info("===== mapper ", ((MapperFactoryBean) proxy).getObjectType().getMethods()[0].toString() );
//        	mfb.getMapperInterface().getMethods();
//        	DualMapper annotation = AnnotationUtils.getAnnotation(mfb.getMapperInterface().getMethods()[0], DualMapper.class);
//        	log.info("===== method {}", annotation.mapperMethod());
//
//        }
//        if (annotation == null)
//            return;
//        log.info("{}: processing bean of type {} during {}",
//          this.getClass().getSimpleName(), proxy.getClass().getName(), action);
//
//        Class<?> proxyMapper = annotation.clazz();
//        String proxyMethod = annotation.mapperMethod();
//
//
//        try {
//        	log.info("매퍼 : {}, 메소드 : {}", proxyMapper.getClass().getName(), proxyMethod);
//
//
//        } catch (ExpressionException ex) {
//        	log.error("매퍼 : {}, 메소드 : {}", proxyMapper.getClass().getName(), proxyMethod);
//
//        }
    }

    @Data
    public static class TargetBeanInfo {
    	private Object bean;
    	private String beanName;
    	private Class<?> mapperInterface;
    }

    public static HashMap<String, String> beanCache;


    public static class CustomProxyHandler implements InvocationHandler {

    	private TargetBeanInfo beanInfo;

    	public CustomProxyHandler(TargetBeanInfo beanInfo) {
    		this.beanInfo = beanInfo;
    	}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// proxy object의 annotation 확인

			// TODO: DualMapper annotation 이 없는 경우 원본 실행
			// 원본 실행
			Proxy.getInvocationHandler(Proxy.getInvocationHandler(this.beanInfo.getBean())).invoke(proxy, method, args);

			// TODO: DualMapper annotation 이 있는 경우 DualMapper 실행
			// ApplicationContextProvider.getBean(annotaiton.class).

			return null;
		}

    }
}
