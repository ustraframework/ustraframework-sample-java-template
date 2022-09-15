package com.gsitm.ustra.java.sample.msi.api1.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsitm.ustra.java.sample.msi.main.interfaces.GetTestParam;
import com.gsitm.ustra.java.sample.msi.main.interfaces.SampleMSI;
import com.gsitm.ustra.java.sample.msi.main.interfaces.SampleTemplateModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 샘플 템플릿 컨트롤러
 * @author RoyLee
 *
 */
@Api("샘플 템플릿 API")
@RestController
@RequestMapping("/api/sample")
public class SampleTemplateController implements SampleMSI {

	@Autowired private SampleTemplateService sampleTemplateService;

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


	@Override
	public List<String> getTest(GetTestParam param) {

		List<String> temp = new ArrayList<>();
		temp.add("test1");
		temp.add("test2");
		return temp;
	}

}
