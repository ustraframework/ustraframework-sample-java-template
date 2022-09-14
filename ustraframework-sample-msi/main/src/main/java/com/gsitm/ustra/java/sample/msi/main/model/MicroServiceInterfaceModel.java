package com.gsitm.ustra.java.sample.msi.main.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MicroServiceInterfaceModel {
    private String className;
    private String methodName;
    private List<String> parameterNameList;
}
