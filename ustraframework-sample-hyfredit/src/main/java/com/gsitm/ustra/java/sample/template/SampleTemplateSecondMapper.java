package com.gsitm.ustra.java.sample.template;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.EnableCaching;

import com.gsitm.ustra.java.sample.config.annotations.Rds2Mapper;

// @Mapper
@EnableCaching(proxyTargetClass=true)
@Rds2Mapper
public interface SampleTemplateSecondMapper {
	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);
}
