package com.gsitm.ustra.java.sample.msi.api1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ApiController {
	@GetMapping("/health")
	@ApiOperation(value = "동작 확인", notes = "<strong>OK</string>를 반환")
	public String getHealth() {
		return "OK";
	}
}
