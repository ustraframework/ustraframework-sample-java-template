package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.EnableCaching;

@Mapper
@EnableCaching(proxyTargetClass=true)
public interface SampleTemplateSecondMapper {
	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);
}
