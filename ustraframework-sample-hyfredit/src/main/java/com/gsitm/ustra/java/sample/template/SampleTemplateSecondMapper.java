package com.gsitm.ustra.java.sample.template;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleTemplateSecondMapper {
	SampleTemplateModel selectAll(SampleTemplateModel.Criteria criteria);
}
