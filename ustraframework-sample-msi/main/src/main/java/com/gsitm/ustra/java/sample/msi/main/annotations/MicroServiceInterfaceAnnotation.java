package com.gsitm.ustra.java.sample.msi.main.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MicroServiceInterfaceAnnotation {
	String value();
}
