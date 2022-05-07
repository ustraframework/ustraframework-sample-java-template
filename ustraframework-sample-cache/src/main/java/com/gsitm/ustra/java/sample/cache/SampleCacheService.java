package com.gsitm.ustra.java.sample.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.ustra.java.core.cache.UstraCacheNames;

/**
 * Sample Cache Service
 * @author d.Naf1y
 *
 */
@Service
@Transactional
public class SampleCacheService {

	/**
	 application.yml에 사용할 캐시명을 다음과 같은 경로에 등록
	 ustra.core.cache.map.additional-cache-names: USTRA-TEST-CACHE

	 GUIDE : http://guide.ustraframework.kro.kr/ref-doc/02/6ODFR12ZITaSOHIRGr1q
	 */
	public static final String TEST_CACHE_NAME = "USTRA-TEST-CACHE";

	@Autowired private SampleCacheMapper sampleCacheMapper;

	@Cacheable(value="USTRA-TEST-CACHE")
	public Map<String, String> getSampleList() {

		Map<String, String> map = new HashMap<>();
		map.put("API_LIST", "/api/sample/cache");
		map.put("API_TC01", "/api/sample/cache/tc01");
		map.put("API_TC02", "/api/sample/cache/tc02");
		map.put("API_TC03", "/api/sample/cache/tc03");
		map.put("API_CLEAR", "/api/sample/cache/clear");

		return map;
	}

	// 캐시 저장소 직접 지정 방식
	@Cacheable(value="USTRA-TEST-CACHE")
	public Map<String, String> getCacheTestCase01() {

		Map<String, String> map = new HashMap<>();
		map.put("type", "Cache TC-01");
		map.put("key01", "v-123");
		map.put("key02", "v-ABC");

		//log.warn("This data is now cached");
		return map;
	}

	// 캐시 저장소 변수 지정 방식
	@Cacheable(value=TEST_CACHE_NAME)
	public Map<String, String> getCacheTestCase02() {

		Map<String, String> map = new HashMap<>();
		map.put("type", "Cache TC-02");
		map.put("key01", "v-123");
		map.put("key02", "v-ABC");

		//log.warn("This data is now cached");
		return map;
	}

	// U.Stra Framework 임시 데이터 캐시 지정
	@Cacheable(value=UstraCacheNames.TEMP_DATA)
	public Map<String, String> getCacheTestCase03() {

		Map<String, String> map = new HashMap<>();
		map.put("type", "Cache TC-03");
		map.put("key01", "v-123");
		map.put("key02", "v-ABC");

		//log.warn("This data is now cached");
		return map;
	}

	@CacheEvict(value=TEST_CACHE_NAME)
	public Map<String, String> cacheClear() {
		Map<String, String> map = new HashMap<>();
		map.put("result", "Cache is empty");
		return map;
	}

	/* Sample : U.Stra Framework EhCache 사용
	@Cacheable(value=UstraEhCacheNames.USER_INFO, cacheManager=UstraCacheManagerSupport.FRAMEWORK_EH_CACHE_MANAGER_NAME)
	public Map<String, String> getCacheDataOnEhCache(String key) {

		Map<String, String> map = new HashMap<>();
		map.put("key01", "v-123");
		map.put("key02", "v-ABC");

		log.warn("This data is now cached");
		return map;
	}
	*/

	public List<SampleCacheModel> getAll(SampleCacheModel.Criteria criteria) {
		return sampleCacheMapper.selectAll(criteria);
	}
}
