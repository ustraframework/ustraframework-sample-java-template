package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.apache.ibatis.binding.MapperProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDualMapperBeanPostProcessor implements BeanPostProcessor {


	@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    	return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
    	log.info("===== postProcessAfterInitialization : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
    	bean = this.process(bean,  beanName);
        return bean;
    }



	private Object process(Object bean, String beanName) {
//        log.info("===== beanName : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
//
//        if ("sampleTemplateMapper".equals(beanName)) {
////        	log.info("isJdkDynamicProxy : {}", AopUtils.isJdkDynamicProxy(bean));
////        	log.info("isCglibProxy : {}", AopUtils.isCglibProxy(bean));
//        	log.info("isProxyClass : {}", Proxy.isProxyClass(bean.getClass()));
//        }

//        if (bean instanceof MapperFactoryBean) {
//
//        	log.info("===== MapperFactoryBean first : bean : {}", bean.getClass());
//
//        	MapperFactoryBean mfb = (MapperFactoryBean) bean;
//
//        	log.info("===== MapperFactoryBean start : bean : {}, mfb : {}", bean.getClass(), mfb.getClass());
//
//        	DualMapper dualMapper = (DualMapper) mfb.getMapperInterface().getAnnotation(DualMapper.class);
////        	mfb.setMapperInterface(dualMapper.clazz());
//
//        	log.info("===== MapperFactoryBean e n d : bean : {}, mfb : {}", bean.getClass(), mfb.getClass());
//
////        	mfb.getMapperInterface().getAnnotationsByType(DualMapper.class)[0].annotationType().getDeclaredAnnotationsByType(annotationClass)
//        	mfb.setMapperInterface(mapperInterface);
//        }

        if (Proxy.isProxyClass(bean.getClass()) ) {
        	InvocationHandler handler = Proxy.getInvocationHandler(bean);
        	if (handler instanceof MapperProxy) {
        		MapperProxy mapperProxy = (MapperProxy)handler;
        		try {
					Field field = mapperProxy.getClass().getDeclaredField("mapperInterface");
					field.setAccessible(true);


					Class<?> mapperInterfaceClass = (Class<?>)field.get(mapperProxy);
					log.info("mapperInterfaceClass : {}", mapperInterfaceClass);

					CustomDualMapperVo targetBeanInfo = new CustomDualMapperVo();
					targetBeanInfo.setBean(bean);
					targetBeanInfo.setBeanName(beanName);
					targetBeanInfo.setMapperInterface(mapperInterfaceClass);

					log.info("===== proxy before : {}, {}", bean.getClass(), handler.getClass());

					bean = Proxy.newProxyInstance(
						mapperInterfaceClass.getClassLoader() ,
						new Class[] { mapperInterfaceClass },
						new CustomDualMapperProxyHandler(targetBeanInfo)
						);

					log.info("===== proxy after : {}, {}", bean.getClass(), handler.getClass());

        		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        }
        return bean;

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


}
