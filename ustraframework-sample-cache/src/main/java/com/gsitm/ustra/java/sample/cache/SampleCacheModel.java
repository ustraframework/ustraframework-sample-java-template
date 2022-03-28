package com.gsitm.ustra.java.sample.cache;

import com.gsitm.ustra.java.management.models.base.UstraManagementBaseModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/***
 * Sample Cache Model
 * @author d.Naf1y
 *
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SampleCacheModel extends UstraManagementBaseModel {

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
