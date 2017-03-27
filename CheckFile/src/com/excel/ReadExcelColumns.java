package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelColumns {
    public static final int START_ROW = 3;

    public static List<HashMap<String, String>> readColumns(String filePath, String sheetName) {
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        File rootFile = new File(filePath);
        FileInputStream inputStream = null;
        File[] files = rootFile.listFiles();
        Workbook wb = null;
        Sheet sheet =null;
        for (File file : files) {
             try {
                String fileName = file.getName();
                if (fileName.endsWith(".xls")) {
                    inputStream = new FileInputStream(file);
                    wb = WorkbookFactory.create(inputStream);
                    sheet = wb.getSheet(sheetName);
                    if (sheet != null) {
                        Row row = sheet.getRow(START_ROW - 1);
                        List<Integer> colNums = getScriptColNum(row);
                        for (int i = START_ROW; i <= sheet.getLastRowNum(); i++) {
                            HashMap<String, String> template = new HashMap<String, String>();
                            Row targetRow = sheet.getRow(i);
                            if (targetRow == null || targetRow.getCell(2) == null) {
                                continue;
                            }
                            //String newFileName = fileName.substring(0, fileName.indexOf(".")) + "_" + targetRow.getCell(2).getRichStringCellValue().toString() + ".vm";
                            String newFileName = targetRow.getCell(2).getRichStringCellValue().toString() + "_now.vm";
                            StringBuffer buf = new StringBuffer();
                            for (Integer colNum : colNums) {
                                Cell targetCell = targetRow.getCell(colNum);
                                if (targetCell != null) {
                                    buf.append(targetCell.getRichStringCellValue().toString()+ "\n");
                                }
                            }
                            String script = buf.toString();
                            if ( script.contains("<img") || script.contains("backg")) {
                                template.put(newFileName, buf.toString());
                                result.add(template);
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return result;
    }

    public static void writeFile(String targetFilePath, List<HashMap<String, String>> result) {
        for (HashMap<String, String> targetFilesMap : result) {
            Iterator<Entry<String, String>> iter = targetFilesMap.entrySet().iterator();
            while (iter.hasNext()) {
                Entry<String, String> targetFileMap = iter.next();
                File targetFile = new File(targetFilePath + "/" + targetFileMap.getKey());
                try {
                    FileWriter writer = new FileWriter(targetFile);
                    writer.write(targetFileMap.getValue());
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<Integer> getScriptColNum(Row row) {
        List<Integer> colNums = new ArrayList<Integer>();
        Iterator<Cell> iterator= row.cellIterator();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            String colName = cell.getRichStringCellValue().toString();
            if(Pattern.compile("(?i)script").matcher(colName).find()) {
                colNums.add(cell.getColumnIndex());
            }
        }
        return colNums;
    }

    public static void main(String[] args) {
        List<HashMap<String, String>> result = ReadExcelColumns.readColumns("D:/ss", "Template");
        writeFile("D:/IMGPROCESS_20_review", result);
    }
}
