package com.gsitm.ustra.java.sample.msi.api2.sample;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/***
 * 샘플 템플릿 모델
 * @author RoyLee
 *
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SampleTemplateModel{

	/**
	 * 그룹 코드
	 */
	@ApiModelProperty("그룹 코드")
	private String grpCd;

	/**
	 * 상세 코드
	 */
	@ApiModelProperty("상세 코드")
	private String dtlCd;

	/**
	 * 코드 명
	 */
	@ApiModelProperty("코드 명")
	private String cdNm;


	@Data
	public static class Criteria {

		/**
		 * 사용 여부
		 */
		@ApiModelProperty("사용여부")
		private String useYn;
	}

}
