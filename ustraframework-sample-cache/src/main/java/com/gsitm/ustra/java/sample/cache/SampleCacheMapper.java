package com.gsitm.ustra.java.sample.cache;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * Sample Cache Mapper
 * @author d.Naf1y
 *
 */
@Mapper
public interface SampleCacheMapper {

	List<SampleCacheModel> selectAll(SampleCacheModel.Criteria criteria);

}
