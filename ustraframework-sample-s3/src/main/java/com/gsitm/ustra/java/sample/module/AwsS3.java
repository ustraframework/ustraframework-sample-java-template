//package com.gsitm.ustra.java.sample.module;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.SdkClientException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.gsitm.ustra.java.core.properties.UstraProperties;
//import com.gsitm.ustra.java.core.utils.ApplicationContextProvider;
//import com.gsitm.ustra.java.sample.model.S3FileInfo;
//import com.gsitm.ustra.java.sample.properties.AWSProperties;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class AwsS3 {
//
//	@Autowired private AWSProperties awsProperties;
//	@Autowired private UstraProperties ustraProperties;
//
//    //Amazon-s3-sdk
//    private AmazonS3 s3Client;
//
//    private AwsS3() {
//        createS3Client();
//    }
//
//    //singleton pattern
//    static private AwsS3 instance = null;
//
//    public static AwsS3 getInstance() {
//        if (instance == null) {
//            return new AwsS3();
//        } else {
//            return instance;
//        }
//    }
//
//    //aws S3 client 생성
//    private void createS3Client() {
//
//    	String key1 = "AKIAYTDNIEDBM7W2XUH4";
//    	String key2 = "5pE7Y8fqIa4a2txnfLWrVeLI86BlGZmpNAFQDSju";
//    	String region = "ap-northeast-2";
//
//        AWSCredentials credentials = new BasicAWSCredentials(key1, key2);
//
////        AWSCredentials credentials = new BasicAWSCredentials(
////        		awsProperties.getCredentials().get("access-key")
////        		, awsProperties.getCredentials().get("secret-key")
////        		);
//        this.s3Client = AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .withRegion(region)
//                //.withRegion(awsProperties.getRegionName())
//                .build();
//    }
//
//    public S3FileInfo upload(MultipartFile multipartFile, String bucketName, String subPath) throws IOException {
//    	String key = UUID.randomUUID().toString();
//    	ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(multipartFile.getContentType());
//
//        this.s3Client.putObject(new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata));
//
//        return S3FileInfo.builder()
//        		.key(key)
//        		.uploadUrl(s3Client.getUrl(bucketName, key).toString())
//        		.build();
//
//    }
//
//    //PutObjectRequest는 Aws S3 버킷에 업로드할 객체 메타 데이터와 파일 데이터로 이루어져있다.
//    private void uploadToS3(PutObjectRequest putObjectRequest) {
//
//        try {
//            this.s3Client.putObject(putObjectRequest);
//            System.out.println(String.format("[%s] upload complete", putObjectRequest.getKey()));
//
//        } catch (AmazonServiceException e) {
//            e.printStackTrace();
//        } catch (SdkClientException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
////    public void copy(String orgKey, String copyKey) {
////        try {
////            //Copy 객체 생성
////            CopyObjectRequest copyObjRequest = new CopyObjectRequest(
////                    this.bucket,
////                    orgKey,
////                    this.bucket,
////                    copyKey
////            );
////            //Copy
////            this.s3Client.copyObject(copyObjRequest);
////
////            System.out.println(String.format("Finish copying [%s] to [%s]", orgKey, copyKey));
////
////        } catch (AmazonServiceException e) {
////            e.printStackTrace();
////        } catch (SdkClientException e) {
////            e.printStackTrace();
////        }
////    }
////
////    public void delete(String key) {
////        try {
////            //Delete 객체 생성
////            DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(this.bucket, key);
////            //Delete
////            this.s3Client.deleteObject(deleteObjectRequest);
////            System.out.println(String.format("[%s] deletion complete", key));
////
////        } catch (AmazonServiceException e) {
////            e.printStackTrace();
////        } catch (SdkClientException e) {
////            e.printStackTrace();
////        }
////    }
//
//}
