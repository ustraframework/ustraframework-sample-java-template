package com.gsitm.ustra.java.sample.msi.api2.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsitm.ustra.java.sample.msi.main.interfaces.SampleMSI;

/**
 * Sample Template 서비스
 * @author RoyLee
 *
 */
@Service
@Transactional
public class SampleTemplateService {

	@Autowired private SampleTemplateMapper sampleTemplateMapper;

	@Autowired private SampleMSI api2;

	/**
	 * 전체 코드 목록 조회
	 * @return 코드 목록
	 */
	public List<SampleTemplateModel> getAll(SampleTemplateModel.Criteria criteria) {
		final List<com.gsitm.ustra.java.sample.msi.main.interfaces.SampleTemplateModel> itemList = api2.getAll("Y");
		return this.sampleTemplateMapper.selectAll(criteria);
	}


}
