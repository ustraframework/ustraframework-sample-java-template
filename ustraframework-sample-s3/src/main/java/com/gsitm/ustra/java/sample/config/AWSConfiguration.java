//package com.gsitm.ustra.java.sample.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.gsitm.ustra.java.sample.properties.AWSProperties;
//
//@Configuration
//@EnableConfigurationProperties({ AWSProperties.class })
//public class AWSConfiguration {
//
//	@Autowired private AWSProperties aWSProperties;
//
//	@Bean
//    public AmazonS3 AmazonS3() {
//        AWSCredentials credentials = new BasicAWSCredentials(
//        		aWSProperties.getCredentials().get("access-key")
//        		, aWSProperties.getCredentials().get("secret-key")
//        		);
//
//        AmazonS3 amazonS3 = AmazonS3ClientBuilder
//        		.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(aWSProperties.getRegionName())
//                .build();
//        return amazonS3;
//
//    }
//}
