package com.gsitm.ustra.java.sample.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gsitm.ustra.java.sample.model.S3FileInfo;
import com.gsitm.ustra.java.sample.service.S3Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/common/s3")
public class S3Controller {
	@Autowired private S3Service s3Service;

	@PutMapping
    public S3FileInfo upload(MultipartFile multipartFile, String bucketType, String subPath) throws IOException {
        return s3Service.upload(multipartFile, bucketType, subPath);
    }

//	@DeleteMapping
//    public String delete(@RequestBody S3FileInVo in) throws IOException {
//        return s3Service.delete(in.getKey(), in.getBucketType());
//    }
//
//	@GetMapping
//    public Boolean get(String key, String bucketType, String downloadFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        OutputStream os = null;
//        FileInputStream fis = null;
//        boolean success = false;
//
//        S3Object fullObject = s3Service.get(key, bucketType);
//
//        try {
//            S3ObjectInputStream objectInputStream = fullObject.getObjectContent();
//            byte[] bytes = IOUtils.toByteArray(objectInputStream);
//
//            String fileName = null;
//            if(downloadFileName != null) {
//                //fileName= URLEncoder.encode(downloadFileName, "UTF-8").replaceAll("\\+", "%20");
//                fileName=  getEncodedFilename(request, downloadFileName);
//            } else {
//                fileName=  getEncodedFilename(request, key); // URLEncoder.encode(fileKey, "UTF-8").replaceAll("\\+", "%20");
//            }
//
//            response.setContentType("application/octet-stream;charset=UTF-8");
//            response.setHeader("Content-Transfer-Encoding", "binary");
//            response.setHeader( "Content-Disposition", "attachment; filename=\"" + fileName + "\";" );
//            response.setHeader("Content-Length", String.valueOf(fullObject.getObjectMetadata().getContentLength()));
//            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
//            FileCopyUtils.copy(bytes, response.getOutputStream());
//            success = true;
//        } catch (IOException e) {
//            log.debug(e.getMessage(), e);
//        } finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (IOException e) {
//                log.debug(e.getMessage(), e);
//            }
//            try {
//                if (os != null) {
//                    os.close();
//                }
//            } catch (IOException e) {
//                log.debug(e.getMessage(), e);
//            }
//        }
//        return success;
//
//    }
//
//    private String getEncodedFilename(HttpServletRequest request, String displayFileName) throws UnsupportedEncodingException {
//        String header = request.getHeader("User-Agent");
//
//        String encodedFilename = null;
//        if (header.indexOf("MSIE") > -1) {
//            encodedFilename = URLEncoder.encode(displayFileName, "UTF-8").replaceAll("\\+", "%20");
//        } else if (header.indexOf("Trident") > -1) {
//            encodedFilename = URLEncoder.encode(displayFileName, "UTF-8").replaceAll("\\+", "%20");
//        } else if (header.indexOf("Chrome") > -1) {
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < displayFileName.length(); i++) {
//                char c = displayFileName.charAt(i);
//                if (c > '~') {
//                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
//                } else {
//                    sb.append(c);
//                }
//            }
//            encodedFilename = sb.toString();
//        } else if (header.indexOf("Opera") > -1) {
//            encodedFilename = "\"" + new String(displayFileName.getBytes("UTF-8"), "8859_1") + "\"";
//        } else if (header.indexOf("Safari") > -1) {
//            encodedFilename = URLDecoder.decode("\"" + new String(displayFileName.getBytes("UTF-8"), "8859_1") + "\"", "UTF-8");
//        } else {
//            encodedFilename = URLDecoder.decode("\"" + new String(displayFileName.getBytes("UTF-8"), "8859_1") + "\"", "UTF-8");
//        }
//        return encodedFilename;
//
//    }
}
