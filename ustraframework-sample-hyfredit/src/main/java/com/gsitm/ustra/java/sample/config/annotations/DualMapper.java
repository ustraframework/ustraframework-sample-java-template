package com.gsitm.ustra.java.sample.config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DualMapper {
	String mapperName() default "";
	String mapperMethod() default "";
	Class<?> clazz();
}
