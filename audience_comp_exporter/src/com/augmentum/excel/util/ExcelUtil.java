package com.augmentum.excel.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 */
public class ExcelUtil
{
   /**
    * Create a Workbook object from a file in the file system.
    */
   @SuppressWarnings("unused")
   public static Workbook getWorkbook (String templateXls) throws ExcelException {
      try {
	    java.io.InputStream inputStream = new java.io.FileInputStream(templateXls);

		if (inputStream==null) {
		   // file not found; attempt to retrieve via from class loader
      	   inputStream = ExcelUtil.class.getResourceAsStream(templateXls);
        }

		Workbook workBook = WorkbookFactory.create(inputStream);
		inputStream.close();

		return workBook;
	  }
	  catch (Exception ex) {
	     throw new ExcelException(ex);
	  }
   }


   /**
    * Create a Workbook object from a file through the class loader (e.g. from jar).
    */
   public static Workbook getWorkbookResource (String templateXls) throws ExcelException {
      try {
		InputStream inputStream = ExcelUtil.class.getResourceAsStream(templateXls);
		Workbook workBook = WorkbookFactory.create(inputStream);
		inputStream.close();

		return workBook;
	  }
	  catch (Exception ex) {
	     throw new ExcelException(ex);
	  }
   }


   /**
    * Create a Workbook object from an URL
    */
   public static Workbook getWorkbook(URL url) throws ExcelException {
      try {
		URLConnection urlCon = url.openConnection();
		InputStream inputStream = urlCon.getInputStream();
		Workbook workBook = WorkbookFactory.create(inputStream);
		inputStream.close();

		return workBook;
	  }
	  catch (Exception ex) {
	     throw new ExcelException(ex);
	  }
   }


   /**
    * Create a Workbook object from an InputStream
    */
   public static Workbook getWorkbook(InputStream inputStream) throws ExcelException {
      try {
  		Workbook workBook = WorkbookFactory.create(inputStream);

		return workBook;
	  }
	  catch (Exception ex) {
	     throw new ExcelException(ex);
	  }
      finally {
		try {
			inputStream.close();
		}
		catch (Exception e) {
			// ignore
		}
      }
   }


   /**
    * Get Cell object at the x,y coordinate.
    */
   public static Cell getCell (Sheet sheet, int colNum, int rowNum) {
      // rows are zero based
      Row row = sheet.getRow(rowNum);

	  // create row if it doesn't exist
	  if (row == null) {
	    return null;
	  }

      Cell cell = row.getCell(colNum);
	  return cell;
   }


   /**
    * Return cell at the location.  If the cell doesn't exist, then create it
    */
   public static Cell getCellOrCreate (Sheet sheet, int colNum, int rowNum) {
	  // get the row
	  Row row = sheet.getRow(rowNum);

	  if (row==null) {
	     // create a new row
		 row = sheet.createRow(rowNum);
	  }

      Cell cell = row.getCell(colNum);

	  // create a new cell
	  return (cell==null) ? row.createCell(colNum) : cell;
   }



   /**
    * Get  column from Excel column (e.g. "AZ")
    */
   public static int getSSColumn (String column) {
      int colNum = -1;

      switch (column.length()) {

         case 1 :
            colNum = column.charAt(0) - 'A';
            break;

         case 2 :
           colNum = (column.charAt(0) - 'A' + 1) * 26 + (column.charAt(1) - 'A');
            break;

         case 0 :
            System.err.println("column reference missing: " + column);
            break;

         default :
            System.err.println("column reference too large: " + column);
            break;
      }

	  return colNum;
   }


   /**
    * Get  row from Excel row
    */
   public static int getSSRow (int rowNum) {
	  return rowNum-1;
   }


   /**
    * Get Excel column (e.g. "AZ") from  column
    */
   public static String getExcelColumn (int colNum) {
      String column = "";

      char msb = (char) (colNum/26);

	  if (msb != 0) {
	    msb--;
	    msb += 'A';
	    column += msb;
	  }

	  char lsb = (char) (colNum%26);
	  lsb += 'A';
      column += lsb;

	  return column;
   }


   /**
    * Get Excel row from  column
    */
   public static int getExcelRow (int row) {
	  return row+1;
   }


   /**
    * Set a cell to the given value
    */
   public static void setCellValue(Sheet sheet, int colNum, int rowNum, String value) throws ExcelException {
     try {
        Cell cell = getCellOrCreate(sheet, colNum, rowNum);

		 cell.setCellValue(value);
      }
	  catch (Exception ex) {
	    throw new ExcelException(ex);
	  }
   }


   /**
    * Set a cell to the given value
    */
   public static void setCellValue(Sheet sheet, int colNum, int rowNum, double value) throws ExcelException {
     try {
        Cell cell = getCellOrCreate(sheet, colNum, rowNum);

		 cell.setCellValue(value);
      }
	  catch (Exception ex) {
	    throw new ExcelException(ex);
	  }
   }


   /**
    * Set a cell to the given value
    */
   public static void setCellValue(Sheet sheet, int colNum, int rowNum, Date value) throws ExcelException {
     try {
        Cell cell = getCellOrCreate(sheet, colNum, rowNum);

		if (value != null) {
		   cell.setCellValue(value);
		}
		else {
		   cell.setCellType(Cell.CELL_TYPE_BLANK);
		}
      }
	  catch (Exception ex) {
	    throw new ExcelException(ex);
	  }
   }


   /**
    * Set a cell to the given value
    */
   public static void setCellValue(Sheet sheet, int colNum, int rowNum, boolean value) throws ExcelException {
     try {
        Cell cell = getCellOrCreate(sheet, colNum, rowNum);

                 cell.setCellValue(value);
      }
          catch (Exception ex) {
            throw new ExcelException(ex);
          }
   }


   /**
    * Set a cell to the given value
    */
   public static void setCellBlank(Sheet sheet, int colNum, int rowNum) throws ExcelException {
     try {
        Cell cell = getCell(sheet, colNum, rowNum);

        if (cell != null) {
          cell.setCellType(Cell.CELL_TYPE_BLANK);
        }
      }
      catch (Exception ex) {
         throw new ExcelException(ex);
      }
   }

   /**
    * Delete rows
    */
   public static void deleteRows(Sheet sheet, int topRow, int bottomRow) {
        // remove template rows.
		for (int i=topRow; i<=bottomRow; i++) {
		   Row row = sheet.getRow(i);

		   if (row != null) {
 		      sheet.removeRow(row);
		   }
		}
   }


   /**
    * Copy cell formatting & value to another cell
    */
   public static void copyCell(Cell source, Cell dest) throws ExcelException {
     try {
	     int type = source.getCellType();

         // copy cell type
		 dest.setCellType(type);

         // copy cell value
		 switch (type) {
		     case Cell.CELL_TYPE_BLANK:
			    break;

		     case Cell.CELL_TYPE_NUMERIC:
			    dest.setCellValue(source.getNumericCellValue());
				break;

		     case Cell.CELL_TYPE_FORMULA:
			    // need to translate the cell formula!
				String formula = source.getCellFormula();
			    dest.setCellFormula(formula);
				break;

		     case Cell.CELL_TYPE_STRING:
			    dest.setCellValue(source.getStringCellValue());
			    break;

			 default:
			   throw new ExcelException("Copying of cell with type: "+type+" not supported");
		 }

		 // copy style
		 dest.setCellStyle(source.getCellStyle());
      }
	  catch (Exception ex) {
	    throw new ExcelException(ex);
	  }
   }


   /**
    * Delete merged regions
    */
   public static void deleteMergedRegions(Sheet sheet) throws ExcelException {
      int numRegions = sheet.getNumMergedRegions();

	  // remove regions in descending order
	  for (int i=numRegions-1; i>=0; i--) {
		 sheet.removeMergedRegion(i);
	  }
   }


   /**
    * Set cell style
    */
  public static void setCellStyle(Sheet sheet, int column, int rowNum, CellStyle style) {
     Cell cell = getCellOrCreate(sheet, column, rowNum);
     cell.setCellStyle(style);
  }


   /**
    * Set cell formula
    */
  public static void setCellFormula(Sheet sheet, int column, int rowNum, String formula) {
     Cell cell = getCellOrCreate(sheet, column, rowNum);

     cell.setCellFormula(formula);
  }

   /**
    * Get Cell range reference
    */
    public static String getCellRangeReference(int leftCol, int topRow, int rightCol, int bottomRow) {
	   return getExcelColumn(leftCol)+getExcelRow(topRow)+":"+getExcelColumn(rightCol)+getExcelRow(bottomRow);
    }

    /**
     * Get Cell reference
     */
 	public static String getCellReference(Sheet sheet, int col, int row) {
 	  return sheet.getSheetName()+"!"+ExcelUtil.getCellReference(col, row);
 	}

   /**
    * Get Cell reference
    */
	public static String getCellReference(int col, int row) {
	  return ExcelUtil.getExcelColumn(col)+ExcelUtil.getExcelRow(row);
	}

   /**
    * Merge cells together
    */
  public static void mergeCells(Sheet sheet, int left, int top, int right, int bottom) {
     sheet.addMergedRegion(new CellRangeAddress(top, left, bottom, right));
  }

   /**
    * Format cells
    */
  public static void formatCellRange(Sheet sheet, int left, int top, int right, int bottom, CellStyle style) {
     for (int i=left; i<=right; i++) {
	   for (int j=top; j<=bottom; j++) {
	      Cell cell=ExcelUtil.getCell(sheet, i, j);
		  cell.setCellStyle(style);
	   }
	 }
  }

   /**
    * Set sheet name
    */
  public static void setSheetName(Workbook workBook, int sheetIndex, String sheetName) {
      // POI 1.7 bug-if sheet name is duplicated, Excel will crash upon close
      Sheet existingSheet = workBook.getSheet(sheetName);

      if (existingSheet != null) {
        // to ensure uniqueness, pad with spaces
         do {
           sheetName += " ";

           existingSheet = workBook.getSheet(sheetName);
         } while (existingSheet != null);

         workBook.setSheetName(sheetIndex, sheetName);
      }

      workBook.setSheetName(sheetIndex, sheetName);
  }

  /**
   * Get Cell value as Object at the x,y coordinate.
   */
  public static Object getCellValue (Sheet sheet, int colNum, int rowNum) throws ExcelException {
     Cell cell = getCell(sheet, colNum, rowNum);

     if (cell == null) {
        return null;
     }
     else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
        return cell.getRichStringCellValue().getString().trim();
     }
     else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
        return null;
     }
     else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
   	  	return cell.getBooleanCellValue();
      }
     else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
    	 if (DateUtil.isCellDateFormatted(cell)) {
    		 return cell.getDateCellValue();
    	 }
    	 else {
    		 return cell.getNumericCellValue();
    	 }
     }
     else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
         return cell.getCellFormula();
     }
     else {
         throw new ExcelException("Unsupported cell type "+cell.getCellType()+" @ "+sheet.getSheetName()+"!"+ExcelUtil.getCellReference(colNum, rowNum));
     }
  }

   /**
    * Get Cell value as String at the x,y coordinate.
    */
   public static String getCellStringValue (Sheet sheet, int colNum, int rowNum) throws ExcelException {
      Cell cell = getCell(sheet, colNum, rowNum);

      if (cell == null) {
         return null;
      }
      else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
         return cell.getStringCellValue().trim();
      }
      else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
         return null;
      }
      else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
    	  boolean value = cell.getBooleanCellValue();
          return Boolean.toString(value);
       }
      else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
         String stringValue = Double.toString(cell.getNumericCellValue());

         // trim .0 if present
         return stringValue.endsWith(".0") ? stringValue.substring(0, stringValue.length()-2) : stringValue;
      }
      else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
          return cell.getCellFormula();
      }
      else {
          throw new ExcelException("Get string from cell type "+cell.getCellType()+" not supported @ "+sheet.getSheetName()+"!"+ExcelUtil.getCellReference(colNum, rowNum));
      }
   }

   /**
    * Get Cell value as double at the x,y coordinate.
    */
   public static double getCellDoubleValue (Sheet sheet, int colNum, int rowNum) throws ExcelException {
      Cell cell = getCell(sheet, colNum, rowNum);

      if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
         return 0.0;
      }
      else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
         return cell.getNumericCellValue();
      }
      else {
          throw new ExcelException("Get number from cell type "+cell.getCellType()+" not supported @ "+sheet.getSheetName()+"!"+ExcelUtil.getCellReference(colNum, rowNum));
      }
   }

   /**
    * Get Cell value as a Date at the x,y coordinate.
    */
   public static Date getCellDateValue (Sheet sheet, int colNum, int rowNum) {
      Cell cell = getCell(sheet, colNum, rowNum);

      if (cell == null) {
         return null;
      }
      else {
         return cell.getDateCellValue();
      }
   }

   /**
    * Get Cell value as a boolean at the x,y coordinate.
    */
   public static boolean getCellBooleanValue (Sheet sheet, int colNum, int rowNum) {
      Cell cell = getCell(sheet, colNum, rowNum);

      if (cell == null) {
         return false;
      }
      else {
         return cell.getBooleanCellValue();
      }
   }

   /**
    * Get CellStyle at the x,y coordinate.
    */
   public static CellStyle getCellStyle (Sheet sheet, int colNum, int rowNum) {
      Cell cell = getCell(sheet, colNum, rowNum);

      if (cell == null) {
         return null;
      }
      else {
         return cell.getCellStyle();
      }
   }

   /**
    * Get CellStyles.  For legends.
    */
   public static List<CellStyle> getCellStyles(Sheet sheet, int colNum, int topRow, int bottomRow) {
     int size = bottomRow-topRow+1;
     List<CellStyle> styles = new ArrayList<CellStyle>(size);

     for (int i=topRow; i<=bottomRow; i++) {
       styles.add(getCellStyle(sheet, colNum, i));
     }

     return styles;
   }


   /**
    * Clone cell style
    */
    public static CellStyle cloneCellStyle(Workbook workBook, CellStyle source) {
      CellStyle cellStyle = workBook.createCellStyle();
      cellStyle.setAlignment(source.getAlignment());
      cellStyle.setBorderBottom(source.getBorderBottom());
      cellStyle.setBorderLeft(source.getBorderLeft());
      cellStyle.setBorderRight(source.getBorderRight());
      cellStyle.setBorderTop(source.getBorderTop());
      cellStyle.setBottomBorderColor(source.getBottomBorderColor());
      cellStyle.setDataFormat(source.getDataFormat());
      cellStyle.setFillBackgroundColor(source.getFillBackgroundColor());
      cellStyle.setFillForegroundColor(source.getFillForegroundColor());
      cellStyle.setFillPattern(source.getFillPattern());
      cellStyle.setFont(workBook.getFontAt(source.getFontIndex()));
      cellStyle.setHidden(source.getHidden());
      cellStyle.setIndention(source.getIndention());
      cellStyle.setLeftBorderColor(source.getLeftBorderColor());
      cellStyle.setLocked(source.getLocked());
      cellStyle.setRightBorderColor(source.getRightBorderColor());
      cellStyle.setRotation(source.getRotation());
      cellStyle.setTopBorderColor(source.getTopBorderColor());
      cellStyle.setVerticalAlignment(source.getVerticalAlignment());
      cellStyle.setWrapText(source.getWrapText());

      return cellStyle;
    }


    /**
     * Set Cell as the active cell
     */
    public static void setActiveCell (Sheet sheet, int colNum, int rowNum) {
       Cell cell = getCell(sheet, colNum, rowNum);

       cell.setAsActiveCell();
    }
}
