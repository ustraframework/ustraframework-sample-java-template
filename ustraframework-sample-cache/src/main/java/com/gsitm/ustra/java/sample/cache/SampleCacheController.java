package com.gsitm.ustra.java.sample.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsitm.ustra.java.core.utils.UstraJsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * 샘플 캐시 컨트롤러
 * @author d.Naf1y
 *
 */
@Api("Sample Cache API")
@RestController
@RequestMapping("/api/sample/cache")
public class SampleCacheController {

	@Autowired private SampleCacheService sampleCacheService;

	@GetMapping
	@ApiOperation(value = "캐시 샘플 API 목록 조회", notes = "<strong>캐시 API 전체 목록</string>을 반환")
	public Map<String, String> tcSampleList() {
		return sampleCacheService.getSampleList();
	}

	@PutMapping("tc01")
	@ApiOperation(value = "캐시 등록 샘플 - 캐시 저장소 직접 지정 방식", notes = "캐시 저장소 <strong>직접 지정</string> 방식")
	public Map<String, String> tcCache01() {
		//return UstraJsonUtils.toJsonNode(sampleCacheService.getCacheTestCase01()).toString();
		//return sampleCacheService.getCacheTestCase01().toString();
		return sampleCacheService.getCacheTestCase01();
	}

	@PutMapping("tc02")
	@ApiOperation(value = "캐시 등록 샘플 - 캐시 저장소 변수 지정 방식", notes = "캐시 저장소 <strong>변수 지정</string> 방식")
	public Map<String, String> tcCache02() {
		return sampleCacheService.getCacheTestCase02();
	}

	@PutMapping("tc03")
	@ApiOperation(value = "캐시 등록 샘플 - U.Stra Framework 임시 데이터 캐시 저장소 지정", notes = "U.Stra Framework 임시 데이터 캐시 저장소 지정")
	public Map<String, String> tcCache03() {
		return sampleCacheService.getCacheTestCase03();
	}

	@DeleteMapping("clear")
	@ApiOperation(value = "캐시 데이터 클리어", notes = "등록된 <strong>캐시 데이터</string> 삭제")
	public Map<String, String> tcCacheClear() {
		return sampleCacheService.cacheClear();
	}

}
