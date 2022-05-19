package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gsitm.ustra.java.sample.config.annotations.DualMapper;
import com.gsitm.ustra.java.sample.config.annotations.DualMethod;

/**
 * 샘플 Mybatis Mapper
 * @author RoyLee
 *
 */
@Mapper
@DualMapper(clazz = SampleTemplateSecondMapper.class, mapperMethod = "selectAll")
public interface SampleTemplateMapper {
	@DualMethod(methodName = "selectAll")
	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);

}
