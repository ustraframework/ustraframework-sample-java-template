package com.gsitm.ustra.java.sample.msi.main.interfaces;

import java.util.List;

import com.gsitm.ustra.java.sample.msi.main.annotations.MicroServiceInterfaceAnnotation;

import io.swagger.annotations.ApiParam;

@MicroServiceInterfaceAnnotation("api1")
public interface SampleMSI {
	List<SampleTemplateModel> getAll(@ApiParam("사용 여부") String useYn);

	List<SampleTemplateModel> getAllPost(@ApiParam("사용 여부") String useYn);

	List<String> getTest(GetTestParam param);
}
