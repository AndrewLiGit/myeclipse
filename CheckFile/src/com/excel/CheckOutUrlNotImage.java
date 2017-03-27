package com.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CheckOutUrlNotImage {

    public static final String FILE_NAME = "test";
    public static final int CHECK_CONTENT_IN_CELL = 4;
    public static final int INPUT_CONTENT_IN_CELL_1 = 14;
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
//        File[] files = rootFile.listFiles();
        File file = new File(filePath);
        XSSFWorkbook wb = null;
        Sheet sheet = null;
        Cell cell = null;
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
                            cell = row.createCell(INPUT_CONTENT_IN_CELL_1 - 1, Cell.CELL_TYPE_STRING);
                            String cellValue = getCellValue(row);
//                            System.out.println("cellValue: " + cellValue);
                            cell.setCellValue(cellValue);
                            if(cellValue != null) {
	                            
	                            CellStyle style = wb.createCellStyle();
	                            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	                            style.setFillForegroundColor(HSSFColor.GREEN.index);
	                            if(!cellValue.isEmpty()) {
	                            	cell.setCellStyle(style);
	                            }
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

    public static String getCellValue(Row row) {
    	Cell cell = row.getCell(CHECK_CONTENT_IN_CELL - 1);
    	if (cell != null){
    		String str = cell.getRichStringCellValue().toString();
//    		if(str.startsWith("\"")){
//    			str = str.substring(1);
//    		}
//    		if(str.endsWith("\"")) {
//    			str = str.substring(0, str.length() - 1);
//    		}
//    		Document dom = null;
//			try {
//				dom = DocumentHelper.parseText(str);
//			} catch (DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		Element root = dom.getRootElement();
//    		root.element("dynamic-element").getText();
//    		for(Iterator i = root.elementIterator(); i.hasNext();){   
//    			Element employee = (Element) i.next();   
//    			for(Iterator j = employee.elementIterator(); j.hasNext();){   
//	    			Element node=(Element) j.next();  employee.attribute("name").getValue() ;
//	    			System.out.println(node.getName()+" : "+node.getText());   
//    			} 
//    		}
//    		InputStream is = new ByteArrayInputStream(str.getBytes());
//    		
//    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = null;
//			try {
//				db = dbf.newDocumentBuilder();
//			} catch (ParserConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				Document dt = db.parse(is);
//				NodeList nl= dt.getElementsByTagName("dynamic-content");
//				nl.item(0).getAttributes();
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    		
    		StringBuffer buf = new StringBuffer();
    		Matcher matcher = Pattern.compile("[\">'quot;](http://.+?)[\"<'&amp;quot;]").matcher(str);
    		while(matcher.find()) {
    			String url = matcher.group(1);
    			System.out.println(url);
    			if(!url.endsWith(".jpg") && !url.endsWith(".png") && !url.endsWith(".JPEG") && !url.endsWith(".gif") && !url.endsWith(".jpeg") && !url.endsWith(".JPG")) {//.jpeg
    				buf.append(url + "\n");
    			}
    		}
    		return buf.toString();
    	}
    	return null;
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
