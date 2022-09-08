package com.gsitm.ustra.java.sample.microserviceinterface.main.config;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.gsitm.ustra.java.sample.microserviceinterface.main.client.MicroServiceInterfaceClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MicroServiceInterfaceBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private final List<String> basePackages;

    private final Class<? extends Annotation> annotationClass;

    private final Class<?> factoryBeanClass;

    private final MicroServiceInterfaceClient microserviceInterfaceClient;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        final List<Class<?>> classList = scanServiceInterfaceList(registry);
        for (Class<?> each : classList) {
            final BeanDefinition bd = createBeanDefinition(each);

            registry.registerBeanDefinition(bd.getBeanClassName(), bd);
        }
    }

    private BeanDefinition createBeanDefinition(Class<?> serviceInterfaceClass) {
        final ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, serviceInterfaceClass);
        constructorArgumentValues.addIndexedArgumentValue(1, new MicroServiceInterfaceInvocationHandler(microserviceInterfaceClient));

        final GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(factoryBeanClass);
        beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

        return beanDefinition;
    }

    private List<Class<?>> scanServiceInterfaceList(BeanDefinitionRegistry registry) {
        final DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;

        final List<Class<?>> l = basePackages.stream()
                                             .map(each -> new Reflections(each).getTypesAnnotatedWith(annotationClass))
                                             .flatMap(each -> each.stream())
                                             .filter(each -> each.isInterface())
                                             .distinct().collect(Collectors.toList());

        return l.stream()
                .filter(each -> beanFactory.getBeanNamesForType(each).length <= 0)
                .collect(Collectors.toList());
    }
}