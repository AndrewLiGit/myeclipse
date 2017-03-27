package com.excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Check out fox components instance
 */
public class CheckOutAllHttp {

    public static final String FILE_NAME = "test";
//    public static final String FILE_NAME = "fox-component-instance";
//    public static final String FILE_NAME = "fox-component-instance1";
    public static final int CHECK_CONTENT_IN_CELL = 4;
    public static final int INPUT_CONTENT_IN_CELL_1 = 15;
    public static final int INPUT_CONTENT_IN_CELL_2 = 16;
    public static final int START_ROW = 2;
    public static final String SHEET_NAME = "Export Worksheet";
    public static Logger log_ = Logger.getLogger(CheckOutAllHttp.class.getName());
    public static final int CHECK_GROUPID_IN_CELL = 1;
    public static final int CHECK_ARTICLEID_IN_CELL = 2;
    public static final int CHECK_TITLE_IN_CELL = 3;
    
    /**
     * @param args
     */
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	XSSFWorkbook wb = readColumns("D:/aaa/" + FILE_NAME + ".xlsx", SHEET_NAME);
        long currentTimeMillis = System.currentTimeMillis();
        writeExcel("D:/aaa/" + FILE_NAME + "_" + currentTimeMillis + ".xlsx", wb);
    }

    public static  XSSFWorkbook readColumns(String filePath, String sheetName) {
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

    public static  String getCellValue(Row row) {
    	Cell cell = row.getCell(CHECK_CONTENT_IN_CELL - 1);
    	if (cell != null){
    		String str = cell.getRichStringCellValue().toString();
    		if(str.startsWith("\"")){
    			str = str.substring(1);
    		}
    		if(str.endsWith("\"")) {
    			str = str.substring(0, str.length() - 1);
    		}
    		
    		InputStream is = new ByteArrayInputStream(str.getBytes());
    		SAXReader sax = new SAXReader();
    		StringBuffer buf = new StringBuffer();
    		try {
				Document document = sax.read(is);
				Element root=document.getRootElement();//获取根节点
				getNodes(root, buf);//从根节点开始遍历所有节点
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				log_.error("Group: " + row.getCell(CHECK_GROUPID_IN_CELL - 1).getRichStringCellValue().toString() + " , ArticleId: " + row.getCell(CHECK_ARTICLEID_IN_CELL - 1) + " , Title: " + row.getCell(CHECK_TITLE_IN_CELL - 1).getRichStringCellValue().toString() + " , Row Number: " + (row.getRowNum() + START_ROW - 1) + " , Content: " + str, e);
				e.printStackTrace();
			}
    		
//    		Matcher matcher = Pattern.compile("[\">'quot;](http://.+?)(\"|<|'|&amp)").matcher(str);
//    		while(matcher.find()) {
//    			String url = matcher.group(1);
//    			System.out.println(url);
//    			if(!url.endsWith(".jpg") && !url.endsWith(".png") && !url.endsWith(".JPEG") && !url.endsWith(".gif") && !url.endsWith(".jpeg") && !url.endsWith(".JPG")) {//.jpeg
//    				buf.append(url + "\n");
//    			}
//    		}
    		return buf.toString();
    	}
    	return null;
    }
    
    private static void getNodes(Element node, StringBuffer buf) {
		// TODO Auto-generated method stub
//    	System.out.println("--------------------");
		
		//当前节点的名称、文本内容和属性
//		System.out.println("当前节点名称："+node.getName());//当前节点名称
//		System.out.println("当前节点的内容："+node.getTextTrim());//当前节点名称
		@SuppressWarnings("unchecked")
		List<Attribute> listAttr=node.attributes();//当前节点的所有属性的list
		Map<String, String> attrMap = new HashMap<String, String>();
		for(Attribute attr:listAttr){//遍历当前节点的所有属性
			String name=attr.getName();//属性名称
			String value=attr.getValue();//属性的值
			attrMap.put(name, value);
//			System.out.println("属性名称："+name+"属性值："+value);
		}
		String attrNameValue = attrMap.get("name");
		String attrTypeValue = attrMap.get("type");
		if(attrNameValue != null && attrNameValue.contentEquals("html") && attrTypeValue != null && attrTypeValue.equalsIgnoreCase("text_area")) {
			List<Element> listElement=node.elements();//所有一级子节点的list
			for(Element e:listElement){//遍历所有一级子节点
//				getNodes(e);//递归
				String conetntHtml = e.getTextTrim();
				if(conetntHtml != null) {
					Matcher matcher = Pattern.compile("[\">'quot;](http://.+?)(\"|<|'|&quot)").matcher(conetntHtml);
					int i = 1;
		    		while(matcher.find()) {
		    			String url = matcher.group(1);
//		    			System.out.println(url);
		    			if(!url.endsWith(".jpg") && !url.endsWith(".png") && !url.endsWith(".JPEG") && !url.endsWith(".gif") && !url.endsWith(".jpeg") && !url.endsWith(".JPG")) {//.jpeg
		    				if(i == 1) {
		    					buf.append("From HTML: " + "\n");
		    				}
		    				buf.append(url + "\n");
		    				i ++;
		    			}
		    		}
				}
			}
		}
		
		if(attrNameValue != null && attrNameValue.contentEquals("iframe_url") && attrTypeValue != null && attrTypeValue.equalsIgnoreCase("text")) {
			List<Element> listElement=node.elements();//所有一级子节点的list
			for(Element e:listElement){//遍历所有一级子节点
				String conetntIframe = e.getTextTrim();
				if(conetntIframe != null && !conetntIframe.isEmpty()) {
					buf.append("From IFrame: " + "\n");
					buf.append(conetntIframe + "\n");
				}
			}
		}
		
		if(attrNameValue != null && attrNameValue.contentEquals("link_url") && attrTypeValue != null && attrTypeValue.equalsIgnoreCase("text")) {
			List<Element> listElement=node.elements();//所有一级子节点的list
			for(Element e:listElement){//遍历所有一级子节点
				String conetntLink = e.getTextTrim();
				if(conetntLink != null && !conetntLink.isEmpty()) {
					buf.append("From Link: " + "\n");
					buf.append(conetntLink + "\n");
				}
			}
		}
		
		if(attrNameValue != null && attrNameValue.contentEquals("data") && attrTypeValue != null && attrTypeValue.equalsIgnoreCase("text_box")) {
			List<Element> listElement=node.elements();//所有一级子节点的list
			for(Element e:listElement){//遍历所有一级子节点
//				getNodes(e);//递归
//				buf.append("From THIRD_PARTY_DATA:" + "\n");
				String conetntLink = e.getTextTrim();
				if(conetntLink != null) {
					Matcher matcher = Pattern.compile("[\">'quot;](http://.+?)(\"|<|'|&quot)").matcher(conetntLink);
					int i = 1;
		    		while(matcher.find()) {
		    			
		    			String url = matcher.group(1);
//		    			System.out.println(url);
		    			if(!url.endsWith(".jpg") && !url.endsWith(".png") && !url.endsWith(".JPEG") && !url.endsWith(".gif") && !url.endsWith(".jpeg") && !url.endsWith(".JPG")) {//.jpeg
		    				if(i == 1) {
		    					buf.append("From THIRD_PARTY_DATA: " + "\n");
		    				}
		    				buf.append(url + "\n");
		    				i++;
		    			}
		    		}
				}
			}
		}
		
		//递归遍历当前节点所有的子节点
		@SuppressWarnings("unchecked")
		List<Element> listElement=node.elements();//所有一级子节点的list
		for(Element e:listElement){//遍历所有一级子节点
			getNodes(e, buf);//递归
		}
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
