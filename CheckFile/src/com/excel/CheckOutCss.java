package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CheckOutCss {


	public static final String FILE_NAME = "modules instance03";
    public static final int CHECK_CONTENT_IN_CELL = 6;
    public static final int INPUT_CONTENT_IN_CELL_1 = 15;
    public static final int INPUT_CONTENT_IN_CELL_2 = 16;
    public static final int START_ROW = 2;
    public static final String SHEET_NAME = "Export Worksheet";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	XSSFWorkbook wb = readColumns("D:/aaa/" + FILE_NAME + ".xlsx", SHEET_NAME);
        long currentTimeMillis = System.currentTimeMillis();
        writeExcel("D:/aaa/" + FILE_NAME + "_" + currentTimeMillis + ".xlsx", wb);
    }

    public static XSSFWorkbook readColumns(String filePath, String sheetName) {
//        File rootFile = new File(filePath);
        FileInputStream inputStream = null;
        File file = new File(filePath);
        XSSFWorkbook wb = null;
        Sheet sheet = null;
        Cell cellCss = null;
        Cell cellCssAll = null;
        Row row  = null;
//        for (File file : files) {
             try {
                String fileName = file.getName();
                if (fileName.endsWith(".xlsx")) {
                    inputStream = new FileInputStream(file);
                    wb = new XSSFWorkbook(inputStream);
                    sheet = wb.getSheet(sheetName);
                    if (sheet != null) {
                        for (int i = START_ROW - 1;i <= sheet.getLastRowNum(); i++) {
                            row = sheet.getRow(i);
                            System.out.println(i + 1);
                            cellCss = row.createCell(INPUT_CONTENT_IN_CELL_1 - 1, Cell.CELL_TYPE_STRING);
                            String cellCssValue = getCellValue(row, true);
//                            System.out.println("cellValue: " + cellValue);
                            cellCss.setCellValue(cellCssValue);
                            
                            CellStyle style = wb.createCellStyle();
                            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                            style.setFillForegroundColor(HSSFColor.GREEN.index);
                            if(!cellCssValue.isEmpty()) {
                            	cellCss.setCellStyle(style);
                            }
                            
                            cellCssAll = row.createCell(INPUT_CONTENT_IN_CELL_2 - 1, Cell.CELL_TYPE_STRING);
                            String cellValue = getCellValue(row, false);
                            cellCssAll.setCellValue(cellValue);
                            if(!cellValue.isEmpty()) {
                            	cellCssAll.setCellStyle(style);
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

//        }
        return wb;
    }

    public static String getCellValue(Row row, boolean isJustCss) {
        String str = row.getCell(CHECK_CONTENT_IN_CELL - 1).getRichStringCellValue().toString();
        StringBuffer buf = new StringBuffer();
        Matcher matcher = Pattern.compile("[\">'](http://.+?)[\"<']").matcher(str);
        while(matcher.find()) {
        	String url = matcher.group(1);
//            System.out.println(url);
            if(isJustCss) {
	            int index = url.lastIndexOf(".");
	    		if (index != -1) {
	    			String endWith = url.substring(index);
	    			
	    			if(endWith.toLowerCase().contains(".css")){
	    				buf.append(url + "\n");
	    			}
	    		}
            } else {
            	if(!url.endsWith(".jpg") && !url.endsWith(".JPEG") && !url.endsWith(".gif") && !url.endsWith(".jpeg") && !url.endsWith(".png") && !url.contains(".js") && !url.contains(".html") && !url.contains(".pdf") && !url.contains(".json") && !url.contains(".js") && !url.contains(".php") && !url.contains(".htm") && !url.contains(".css")) {
                	buf.append(url + "\n");
                	System.out.println(url);
                }
            }
        }
        return buf.toString();
    }

    public static void writeExcel(String path, XSSFWorkbook wb) {
        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
