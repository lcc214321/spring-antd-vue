package com.eaton.common.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 简单的Excel文档输出
 * 
 * @version 1.0
 * @author binghong.guo
 *
 */
public class ExcelWriter {
	// 每列的列名
	private List<String> colList;
	// 列名对应的对象属性名称
	private Map<String, String> colAndValueMap;
	// 需要打印的数据对象集合
	private List<?> dataList;
	// 数据对象的Class对象
	private Class<?> dataType;
	// 设置文件输出路径
	// private File file;
	// 分类用map集合
	private Map<String, Sheet> sortMap;
	// WorkBook
	private Workbook workbook;
	// 是否按照特定属性分类
	private String sortKey;

	public ExcelWriter() {
	}

	public ExcelWriter(List<String> colList, Map<String, String> colAndValueMap, List<?> dataList) {
		this.colList = colList;
		this.colAndValueMap = colAndValueMap;
		this.dataList = dataList;
		setDataType(dataList.get(0).getClass());
	}

	// 普通导入
	private void importExcelData(Workbook workbook) {
		Sheet sheet = workbook.createSheet();

		setTitle(sheet);

		// 导入数据对象生成数据行
		for (int i = 0; i < dataList.size(); i++) {
			Object object = dataList.get(i);
			Row dataRow = sheet.createRow(i + 1);

			// Class<?> clazz = excelBean.getDataType();

			writeRowData(dataRow, object);

		}
	}

	// 设置标题栏
	private void setTitle(Sheet sheet) {
		Row titleRow = sheet.createRow(0);
		for (int i = 0; i < colList.size(); i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(colList.get(i));
		}
	}

	// 根据指定属性进行sheet表分类写入
	private void importExcelDataSortBy(String key) {
		for (int i = 0; i < dataList.size(); i++) {
			Object object = dataList.get(i);

			try {
				Method sortMethod = dataType
						.getMethod("get" + key.substring(0, 1).toUpperCase().concat(key.substring(1)));
				String sortname = (String) sortMethod.invoke(object, null);
				Sheet sheet = getSheetBySort(sortname);

				//System.out.println(sheet.getLastRowNum());
				Row dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

				writeRowData(dataRow, object);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 写每个row行的数据
	private void writeRowData(Row dataRow, Object object) {
		try {
			for (int col = 0; col < colList.size(); col++) {

				String colName = colList.get(col);
				String str = colAndValueMap.get(colName);
				Method method;
				try {
					method = dataType.getMethod("get" + str.substring(0, 1).toUpperCase().concat(str.substring(1)));
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					continue;
				} catch (NullPointerException e) {
					e.printStackTrace();
					continue;
				}
				String value = "";

				Object invoke = method.invoke(object, null);
				// getXxx为List集合，则遍历集合，用符号','连接
				if (invoke instanceof List) {
					List list = (List) invoke;
					for (Object obj : list) {
						value = value + "," + obj;
					}
					if (value.length() > 1) {
						value = value.substring(1);
					}
				} else {
					value = (String) method.invoke(object, null);
				}

				Cell cell = dataRow.createCell(col);
				cell.setCellValue(value);

				System.out.println(colName + ":" + value);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 处理get字符串
	private void transformKey(Object object, String key) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String[] keys = key.split(".");
		String str = keys[0];
		
		Method method = dataType.getMethod("get" + str.substring(0, 1).toUpperCase().concat(str.substring(1)));
		Object invoke = method.invoke(object, null);
		if (keys.length > 1) {
			transformKey(invoke, key.substring(str.length()));
		} else {
			
		}
	}
	
	// 存储sort表
	private Sheet getSheetBySort(String sortname) {
		Sheet sheet = workbook.getSheet(sortname);
		if (sheet == null) {
			sheet = workbook.createSheet(sortname);
			setTitle(sheet);
		}
		return sheet;
	}

	// 生成Workbook对象，并导入数据
	public Workbook createWorkbook() {
		this.workbook = new HSSFWorkbook();
		// this.workbook = workbook;
		if (sortKey != null && sortKey != "") {
			importExcelDataSortBy(sortKey);
		} else {
			importExcelData(workbook);
		}
		return workbook;
	}

	// 根据文件对象，输出到指定文件地址
	public void createFileToPath(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);

			createWorkbook();
			workbook.write(fos);

			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 根据文件地址，输出到指定文件地址
	public void createFileToPathName(String PathName) {
		File file = new File(PathName);
		createFileToPath(file);
	}

	public void setColList(List<String> colList) {
		this.colList = colList;
	}

	public void setColAndValueMap(Map<String, String> colAndValueMap) {
		this.colAndValueMap = colAndValueMap;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}

	public void setSortKey(String key) {
		this.sortKey = key;
	}

	/*
	 * public void setFileLocation(File file) { this.file = file; }
	 */

}