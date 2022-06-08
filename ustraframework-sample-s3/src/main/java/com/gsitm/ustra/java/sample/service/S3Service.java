package com.gsitm.ustra.java.sample.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gsitm.ustra.java.sample.model.S3FileInfo;
import com.gsitm.ustra.java.sample.properties.AWSProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class S3Service {
	@Autowired private AWSProperties awsProperties;


    public S3FileInfo upload(MultipartFile multipartFile, String bucketType, String subPath) throws IOException {

        String bucketName = awsProperties.getS3().getBucketType().get(bucketType);
        String key = UUID.randomUUID().toString();

        // File 객체가 아닌 stream을 통한 업로드를 위하여 파일 메타 정보 생성
    	ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        // AWS 인증 정보 생성
        AWSCredentials credentials = new BasicAWSCredentials(
        		awsProperties.getCredentials().get("access-key")
        		, awsProperties.getCredentials().get("secret-key")
        		);
        // AWS 인증 정보를 포함하여 AmazonS3 객체 생성
        AmazonS3 amazonS3 = AmazonS3ClientBuilder
        		.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(awsProperties.getRegionName())
                .build();

        // AmazonS3 객체를 통한 S3 파일 연계
        amazonS3.putObject(new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata));

        // 업로드 이후 url 정보 및 key 값 리턴
        return S3FileInfo.builder()
        		.key(key)
        		.uploadUrl(amazonS3.getUrl(bucketName, key).toString())
        		.build();

    }



//    public String delete(String key, String bucketType) throws IOException {
//
//        String bucketName = aWSProperties.getS3().getBucketType().get(bucketType);
//
//        return awsS3.delete(key, bucketName);
//    }
//
//
//    public S3Object get(String key, String bucketType) throws Exception {
//
//	    if (key == null) {
//	        return null;
//	    }
//	    S3Object fullObject = null;
//	    String bucketName = aWSProperties.getS3().getBucketType().get(bucketType);
//	    try {
//	        fullObject = awsS3.get(key, bucketName);
//	        if (fullObject == null) {
//	            return null;
//	        }
//	    } catch (AmazonS3Exception e) {
//	        throw new Exception("다운로드 파일이 존재하지 않습니다.");
//	    }
//
//	    return fullObject;
//
//    }
}
