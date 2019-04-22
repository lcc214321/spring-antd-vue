package com.eaton.common.poi;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * Excel文档输出工具类
 * 
 * 将传入对象批量输出到excel文件对象中 传入参数为List<T t>,T为需要输出的
 * 
 * 
 * @author binghong.guo
 *
 */
public class ExcelWriterUtil {

	// private ExcelBean excelBean;

	private ExcelWriterUtil() {
	}

	// private static String[] paramfields = {"name","age"};

	public static void objectListTransform(List sourceList, Class clazz,
			List<String> colList, Map<String, String> map) {

		try {
			for (Object object : sourceList) {
				for (int col = 0; col < colList.size(); col++) {
					String colName = colList.get(col);
					String str = map.get(colName);
					Method method = clazz.getMethod("get"
							+ str.substring(0, 1).toUpperCase()
									.concat(str.substring(1)));
					if (method != null) {
						String value = (String) method.invoke(object, null);
						System.out.println(colName + ":" + value);
					}

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void importExcelData(Workbook workbook, ExcelBean excelBean) {
		Sheet sheet = workbook.createSheet();
		// 设置标题栏数据
		Row titleRow = sheet.createRow(0);
		List<String> colList = excelBean.getColList();
		for (int i = 0; i < colList.size(); i++) {
			Cell cell = titleRow.createCell(i);
			cell.setCellValue(colList.get(i));
		}

		// 导入数据对象生成数据行
		for (int i = 0; i < excelBean.getDataList().size(); i++) {
			Object object = excelBean.getDataList().get(i);
			Row dataRow = sheet.createRow(i + 1);
			

			Map<String, String> colAndValueMap = excelBean.getColAndValueMap();
			Class<?> clazz = excelBean.getDataType();
			for (int col = 0; col < colList.size(); col++) {
				String colName = colList.get(col);
				String str = colAndValueMap.get(colName);
				try {
					Method method = clazz.getMethod("get"
							+ str.substring(0, 1).toUpperCase()
									.concat(str.substring(1)));
					//if (method != null) {
						String value = (String) method.invoke(object, null);
						
						Cell cell = dataRow.createCell(col);
						cell.setCellValue(value);
						
						System.out.println(colName + ":" + value);
					//}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (Object object : excelBean.getDataList()) {

			// List<String> colList = excelBean.getColList();
		}
	}

	// 传入参数为ExcelBean，
	public static Workbook getExcel(ExcelBean excelBean) {
		Workbook workbook = new HSSFWorkbook();
		importExcelData(workbook, excelBean);
		return workbook;
	}

}