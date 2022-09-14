package com.gsitm.ustra.java.sample.msi.api2.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * 샘플 Mybatis Mapper
 * @author RoyLee
 *
 */
@Mapper
public interface SampleTemplateMapper {

	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);

}
