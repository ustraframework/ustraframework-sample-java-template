package com.gsitm.ustra.java.sample.msi.api2.config;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "ustra.msa")
public class BaseUrlProperties {
	private HashMap<String, String> proxyUri;

}