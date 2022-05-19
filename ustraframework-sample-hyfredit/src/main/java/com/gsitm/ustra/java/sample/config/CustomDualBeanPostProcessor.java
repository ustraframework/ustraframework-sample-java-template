package com.gsitm.ustra.java.sample.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.binding.MapperProxy;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;

import com.gsitm.ustra.java.sample.config.annotations.DualMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomDualBeanPostProcessor implements BeanPostProcessor {

//	@Override
//	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//		// TODO Auto-generated method stub
//
//	}

	@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    	log.info("===== postProcessBeforeInitialization : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
    	return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
      throws BeansException {
    	log.info("===== postProcessAfterInitialization : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
    	bean = this.process(bean,  beanName);
//    	if (Proxy.isProxyClass(bean.getClass()) ) {
//    		log.info("===== after return : {}, {}", bean.getClass(), Proxy.getInvocationHandler(bean).getClass().getName());
//    	}
        return bean;
    }

//	@Override
//	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
//		// TODO Auto-generated method stub
//
//        if ("sampleTemplateMapper".equals(beanName)) {
//        	log.info("isProxyClass : {}", Proxy.isProxyClass(beanDefinition.getBeanClass()));
//        }
//
//		if (MapperProxy.class.isAssignableFrom(beanType)) {
//			try {
//				this.registerDynamicMapperFactoryBean(beanDefinition, beanType, beanName);
//			} catch (Exception e) {
//				log.error(e.getMessage(), e);
//			}
//		}
//
//	}


//	private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();
//
//	private void registerDynamicMapperFactoryBean(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) throws Exception {
//
////		MultiDataSourceProperties multiDataSourceProperties = UstraEnvironmentUtils.bindProperties(new MultiDataSourceProperties(), MultiDataSourceProperties.PREFIX, environment);
////		if (!multiDataSourceProperties.isSeparatedMybatisScan()) {
////			DataSource dataSource = this.beanFactory.getBean(DataSource.class);
////			this.registerSqlSessionFactoryBean(dataSource, null, true, null);
////		} else {
////
////			for(Entry<String, SourceProperties> e : multiDataSourceProperties.getDatasources().entrySet()) {
////				SourceProperties.Mybatis mybatisProp = e.getValue().getMybatis();
////				DataSource dataSource = this.beanFactory.getBean(e.getKey(), DataSource.class);
////
////				Assert.notNull(dataSource, "dataSource name" + e.getKey() + " have to not be null");
////				this.registerSqlSessionFactoryBean(dataSource, e.getKey(), multiDataSourceProperties.getDefaultDatasourceName().equals(e.getKey()), mybatisProp);
////			}
////
////		}
//
//
//	    if (Proxy.isProxyClass(beanType.getClass()) ) {
//	    	@SuppressWarnings("unchecked")
//			final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(beanType);
//	    	if (mapperProxyFactory == null) {
//	  	      throw new BindingException("Type " + beanType + " is not known to the MapperRegistry.");
//	  	    }
//	  	    try {
//	  	      // return mapperProxyFactory.newInstance(sqlSession);
//	  	    } catch (Exception e) {
//	  	      throw new BindingException("Error getting mapper instance. Cause: " + e, e);
//	  	    }
//	    }
//
//	}



	private Object process(Object bean, String beanName) {
        // Object proxy = this.getTargetObject(bean);
        log.info("===== beanName : {}, {}, {}\n", beanName, bean.getClass(), ClassUtils.getUserClass(bean.getClass()));
        // MapperFactoryBean.getObjectType

        if ("sampleTemplateMapper".equals(beanName)) {
//        	log.info("isJdkDynamicProxy : {}", AopUtils.isJdkDynamicProxy(bean));
//        	log.info("isCglibProxy : {}", AopUtils.isCglibProxy(bean));
        	log.info("isProxyClass : {}", Proxy.isProxyClass(bean.getClass()));


        }

        if (bean instanceof MapperFactoryBean) {

        	log.info("===== MapperFactoryBean first : bean : {}", bean.getClass());

        	MapperFactoryBean mfb = (MapperFactoryBean) bean;

        	log.info("===== MapperFactoryBean start : bean : {}, mfb : {}", bean.getClass(), mfb.getClass());

        	DualMapper dualMapper = (DualMapper) mfb.getMapperInterface().getAnnotation(DualMapper.class);
        	mfb.setMapperInterface(dualMapper.clazz());

        	log.info("===== MapperFactoryBean e n d : bean : {}, mfb : {}", bean.getClass(), mfb.getClass());

        	return mfb;

//        	mfb.getMapperInterface().getAnnotationsByType(DualMapper.class)[0].annotationType().getDeclaredAnnotationsByType(annotationClass)
        	//mfb.setMapperInterface(mapperInterface);
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

					TargetBeanInfo targetBeanInfo = new TargetBeanInfo();
					targetBeanInfo.setBean(bean);
					targetBeanInfo.setBeanName(beanName);
					targetBeanInfo.setMapperInterface(mapperInterfaceClass);

					log.info("===== proxy before : {}, {}", bean.getClass(), handler.getClass());
//					Proxy.newProxyInstance(
//							mapperInterfaceClass.getClassLoader() ,
//							new Class[] { mapperInterfaceClass },
//							new CustomProxyHandler(targetBeanInfo));


					log.info("===== proxy after : {}, {}", bean.getClass(), handler.getClass());

        		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}

        }


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
        return bean;
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
    		log.info("===== CustomProxyHandler : {}", beanInfo.getBeanName());
    		this.beanInfo = beanInfo;
    	}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// proxy object의 annotation 확인
			log.info("===== invoke : {}", this.beanInfo.getBeanName());
			// TODO: DualMapper annotation 이 없는 경우 원본 실행
			// 원본 실행
			Proxy.getInvocationHandler(Proxy.getInvocationHandler(this.beanInfo.getBean())).invoke(proxy, method, args);

			// TODO: DualMapper annotation 이 있는 경우 DualMapper 실행
			// ApplicationContextProvider.getBean(annotaiton.class).

			return null;
		}

    }

    public static class CustomMethidInterceptor implements MethodInterceptor, Advice {

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			// TODO Auto-generated method stub
			log.info("===== CustomMethidInterceptor : {}", invocation.getMethod());
			return null;
		}



    }






}
