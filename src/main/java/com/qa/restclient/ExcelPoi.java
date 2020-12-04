package com.qa.restclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPoi {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String Path;

	public static OutputStream fileOut = null;
	public static FileInputStream ExcelFile = null;
	
	public static void main(String [] args) throws IOException{
		writeLetter("D:\\JenkinsNode\\apidata\\dataEngine.xlsx", 2, 7, "hahahha");
	}
	
	
	public static void writeLetter(String path,int RowNum, int ColNum,String result) throws IOException{
		ExcelPoi.Path = path;
		ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);

		try {
			ExcelWSheet = ExcelWBook.getSheet("post");
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellType(Cell.CELL_TYPE_BLANK);
				Cell.setCellValue(result);
			} else {
				Cell.setCellType(Cell.CELL_TYPE_BLANK);
				Cell.setCellValue(result);
			}
			fileOut = new FileOutputStream(Path);
			ExcelWBook.write(fileOut);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ExcelFile.close();
				if (fileOut != null) {
					fileOut.flush();
					fileOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
