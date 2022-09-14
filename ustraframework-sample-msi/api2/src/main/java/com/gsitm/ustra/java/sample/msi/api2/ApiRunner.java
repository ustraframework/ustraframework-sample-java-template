package com.gsitm.ustra.java.sample.msi.api2;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gsitm.ustra.java.mvc.app.ServletApplicationRunner;

@SpringBootApplication
public class ApiRunner extends ServletApplicationRunner {
	public static void main(String[] args) throws IOException {
		ServletApplicationRunner.run(ApiRunner.class, args);
	}
}
