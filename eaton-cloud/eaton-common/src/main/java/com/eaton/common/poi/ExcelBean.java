package com.eaton.common.poi;

import java.util.List;
import java.util.Map;

/**
 * Excel的包装bean类
 * 
 * @author binghong.guo
 *
 */
public class ExcelBean {
	// 每列的列名
	private List<String> colList;
	// 列名对应的对象属性名称
	private Map<String, String> colAndValueMap;
	// 需要打印的数据对象集合
	private List<?> dataList;
	// 数据对象的Class对象
	private Class<?> dataType;
	//private E e;
	
	public ExcelBean(){
	}
	
	public void test(){
		System.out.println();
	}

	public void setColAndValueMap(Map<String, String> colAndValueMap){
		this.colAndValueMap = colAndValueMap;
	}

	public List<String> getColList() {
		return colList;
	}

	public void setColList(List<String> colList) {
		this.colList = colList;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public Class getDataType() {
		return dataType;
	}

	public void setDataType(Class dataType) {
		this.dataType = dataType;
	}

	public Map<String, String> getColAndValueMap() {
		return colAndValueMap;
	}
}