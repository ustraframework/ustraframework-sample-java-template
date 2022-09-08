package com.gsitm.ustra.java.sample.microserviceinterface.main.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MicroServiceInterfaceAnnotation {
	String value();
}
