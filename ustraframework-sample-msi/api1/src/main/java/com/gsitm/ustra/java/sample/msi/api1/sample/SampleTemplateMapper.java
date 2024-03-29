package com.gsitm.ustra.java.sample.msi.api1.sample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gsitm.ustra.java.sample.msi.main.interfaces.SampleTemplateModel;

/**
 * 샘플 Mybatis Mapper
 * @author RoyLee
 *
 */
@Mapper
public interface SampleTemplateMapper {

	List<SampleTemplateModel> selectAll(SampleTemplateModel.Criteria criteria);

}
