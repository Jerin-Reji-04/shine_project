package utilitiespkg;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutilis {

	public static int getRowCount(String xl, String Sheet)
	{
			try
			{
				FileInputStream fi=new FileInputStream(xl);
				try (XSSFWorkbook wb = new XSSFWorkbook(fi)) {
					return wb.getSheet(Sheet).getLastRowNum();
				}
			
			}
			catch(Exception e)
			{
				return 0;
			}

		}
		

	public static String getCellValString(String xl, String Sheet, int r, int c) 
	{
		try
		{
			FileInputStream fi=new FileInputStream(xl);//to open excel
			try (XSSFWorkbook wb = new XSSFWorkbook(fi)) {
				XSSFCell cell=wb.getSheet(Sheet).getRow(r).getCell(c);
				
				if(cell.getCellType()==CellType.STRING)//getcelltype--to get type of the value in the cell
				{
					return cell.getStringCellValue();//to fetch the string value
				}
				else
				{
					return cell.getRawValue();//to fetch the integer value
				}
			}
		}
			catch(Exception e)
			{
				return "";
			}
		}
	}


