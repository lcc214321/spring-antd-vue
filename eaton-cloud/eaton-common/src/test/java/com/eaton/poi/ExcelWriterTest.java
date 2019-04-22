package com.eaton.poi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eaton.common.poi.ExcelBean;
import com.eaton.common.poi.ExcelWriter;
import org.junit.Test;

public class ExcelWriterTest {

	public static void main(String[] args){
		
		List<Boy> list = new ArrayList<Boy>();
		List<String> colString = new ArrayList<String>();
		Map<String, String> map = new HashMap<String, String>();
		List<String> friend = new ArrayList<String>();
		
		friend.add("小柳");
		friend.add("小陈");
		
		colString.add("姓名");
		colString.add("年龄");
		colString.add("小伙伴");
		
		map.put("年龄", "age");
		map.put("姓名", "name");
		map.put("小伙伴", "friends");
		
		Boy boy = new Boy();
		boy.setName("小明");
		boy.setAge("12");
		boy.setFriends(friend);
		Boy boy1 = new Boy();
		boy1.setName("小玲");
		boy1.setAge("13");
		
		list.add(boy);
		list.add(boy);
		list.add(boy);
		list.add(boy1);
		
		//ExcelWriterUtil.objectListTransform(list, Boy.class, colString, map);
		
		ExcelBean excelBean = new ExcelBean();
		excelBean.setDataType(Boy.class);
		excelBean.setColList(colString);
		excelBean.setColAndValueMap(map);
		excelBean.setDataList(list);
		
		String filePath = "D:/demo.xls";
		
		File file = new File(filePath);
		
		try {
			//FileOutputStream fos = new FileOutputStream(file);
			//Workbook excel = ExcelWriterUtil.getExcel(excelBean);
			//excel.write(fos);
			//fos.close();
			ExcelWriter excelWriter = new ExcelWriter(colString, map, list);
			//excelWriter.setDataType(Boy.class);
			excelWriter.setSortKey("name");
			excelWriter.createFileToPath(file);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	@Test
	public void test(){
		Object obj = new ArrayList();
		System.out.println(obj instanceof String);
	}
	
}