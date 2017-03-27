package com.augmentum.excel.util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportUtil {

	public static String getProperty(Properties properties, String name, String ... alternateNames) {
		String value = properties.getProperty(name);
		
		if (value != null) {
			return value;
		}
		else {
			// search alternative names
			for (int i=0; i < alternateNames.length; i++) {
				value = properties.getProperty(alternateNames[i]);
				
				if (value != null) {
					break;
				}
			}
		}
		
		return value;
	}

	
	public static List<Properties> getProperties(Workbook wb, String sheetName, int headerRow, int startRow, DateFormat dateFormat, String curRowProperty, String ... alternateNames) throws Exception {
		List<Properties> propertiesList = new ArrayList<Properties>();
		
		Sheet sheet = wb.getSheet(sheetName);
		
		if (sheet==null) {
			// search for alternative sheet names
			for (int i=0; i < alternateNames.length; i++) {
				sheet = wb.getSheet(alternateNames[i]);
				
				if (sheet != null) {
					break;
				}
			}
		}
			
		if (sheet==null) {
			// sheet not found
			return propertiesList;
		}
		
		 int blankLineCount = 0;
		
		int row = startRow;
		for (;; row++) {
			int col = 0;
			
			if (ExcelUtil.getCellValue(sheet, col, row) == null && ExcelUtil.getCellValue(sheet, col+1, row) == null) {
				blankLineCount++;
				
				if (blankLineCount >= 5) {
					break;
				}
			}
			else {
				Properties properties = new Properties();
				
				properties.put(curRowProperty, sheetName+"!*"+ExcelUtil.getExcelRow(row));

				// scan all the column headers
				for (col=0; ; col++) {
					if (ExcelUtil.getCell(sheet, col, headerRow) == null) {
						break;
					}
					
					String header = ExcelUtil.getCellStringValue(sheet, col, headerRow);
					if (header == null || header.trim().length() == 0) {
						break;
					}
					
					
					String value = null;
					
					Cell cell = ExcelUtil.getCell(sheet, col, row);
					
					// check if it is a date
					if (cell != null && cell.getCellType()==Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
						Date date = DateUtil.getJavaDate(ExcelUtil.getCellDoubleValue(sheet, col, row));
						
						// format as date
						value = dateFormat.format(date);
					}
					else {
						value = ExcelUtil.getCellStringValue(sheet, col, row);
					}
					
					// add to properties
					if (value != null) {
						properties.put(header, value);
					}
				}
				
				propertiesList.add(properties);
				
				blankLineCount = 0;
			}
		}
		
		return propertiesList;
	}
	
	public static Map<Locale,String> toMap(String value) {
		if (value == null) {
			return null;
		}
		
		Map<Locale,String> map = new HashMap<Locale,String>();
		if (value != null && !value.isEmpty()) {
			map.put(Locale.getDefault(), value);
		}
		
		return map;
	}
}
