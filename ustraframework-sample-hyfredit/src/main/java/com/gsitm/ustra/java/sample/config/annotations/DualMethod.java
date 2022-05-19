package com.gsitm.ustra.java.sample.config.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DualMethod {
	String methodName() default "";
}
