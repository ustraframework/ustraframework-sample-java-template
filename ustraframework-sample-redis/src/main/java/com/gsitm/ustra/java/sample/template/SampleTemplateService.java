package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Sample Template 서비스
 * @author RoyLee
 *
 */
@Service
@Transactional
public class SampleTemplateService {

	@Autowired private SampleTemplateMapper sampleTemplateMapper;

	/**
	 * 전체 코드 목록 조회
	 * @return 코드 목록
	 */
	public List<SampleTemplateModel> getAll(SampleTemplateModel.Criteria criteria) {
		return this.sampleTemplateMapper.selectAll(criteria);
	}


}
