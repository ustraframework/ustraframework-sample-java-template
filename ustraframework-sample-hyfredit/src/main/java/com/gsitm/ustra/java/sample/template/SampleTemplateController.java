package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsitm.ustra.java.sample.config.CustomProxyCachingProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 샘플 템플릿 컨트롤러
 * @author RoyLee
 *
 */
@Api("샘플 템플릿 API")
@RestController
@RequestMapping("/api/sample/template")
@Slf4j
public class SampleTemplateController {

	@Autowired private SampleTemplateService sampleTemplateService;
	@Autowired private CustomProxyCachingProperties customProxyCachingProperties;

	@GetMapping
	@ApiOperation(value = "전체 코드 목록 조회", notes = "<strong>코드 전체 목록</string>을 반환")
	public List<SampleTemplateModel> getAll(@ApiParam("사용 여부") String useYn) {

		SampleTemplateModel.Criteria criteria = new SampleTemplateModel.Criteria();
		criteria.setUseYn(useYn);

		return this.sampleTemplateService.getAll(criteria);
	}


	@PostMapping
	@ApiOperation(value = "전체 코드 목록 조회", notes = "<strong>코드 전체 목록</string>을 반환")
	public List<SampleTemplateModel> getAllPost(@ApiParam("사용 여부") String useYn) {

		SampleTemplateModel.Criteria criteria = new SampleTemplateModel.Criteria();
		criteria.setUseYn(useYn);

		return this.sampleTemplateService.getAll(criteria);
	}

	@PostMapping("/cache")
	@ApiOperation(value = "전체 코드 목록 조회 - 캐싱", notes = "<strong>코드 전체 목록</string>을 반환")
	public List<SampleTemplateModel> getAllPostByCache(@ApiParam("사용 여부") String useYn) {

		SampleTemplateModel.Criteria criteria = new SampleTemplateModel.Criteria();
		criteria.setUseYn(useYn);
		log.info(Boolean.toString(customProxyCachingProperties.getBypass()));
		return this.sampleTemplateService.getAllByCache(criteria);
	}

	@PostMapping("/dual")
	@ApiOperation(value = "전체 코드 목록 조회 - dual", notes = "<strong>코드 전체 목록</string>을 반환")
	public List<SampleTemplateModel> getAllPostByDualMapper(@ApiParam("사용 여부") String useYn) {

		SampleTemplateModel.Criteria criteria = new SampleTemplateModel.Criteria();
		criteria.setUseYn(useYn);
		return this.sampleTemplateService.getAllByDualMapper(criteria);
	}

}
