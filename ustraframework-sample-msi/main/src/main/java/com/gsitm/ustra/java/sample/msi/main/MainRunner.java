package com.gsitm.ustra.java.sample.msi.main;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gsitm.ustra.java.mvc.app.ServletApplicationRunner;

@SpringBootApplication
public class MainRunner extends ServletApplicationRunner {
	public static void main(String[] args) throws IOException {
		ServletApplicationRunner.run(MainRunner.class, args);
	}
}
