package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gsitm.ustra.java.sample.config.annotations.DualMapper;

/**
 * 샘플 Mybatis Mapper
 * @author RoyLee
 *
 */
@Mapper
public interface SampleTemplateMapper {

	@DualMapper(clazz = SampleTemplateSecondMapper.class, mapperMethod = "selectAll")
	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);

}
