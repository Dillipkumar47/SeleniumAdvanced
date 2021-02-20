package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Submit {

	@Test (priority = 0)
	public void test01() throws IOException {
		System.out.println("Running test 01");
		  
	}

	@Test (priority = 1)
	@Parameters({ "Type", "Name", "ServiceNo", "Quantity" })
	public void test02(String type, String name, String srNo, String quantity) {
		System.out.println(type+" "+name+" "+srNo+" "+quantity);


	}
	
	
	
//	@AfterMethod
//	public void release() {
//		driver.close();
//		driver.quit();
//	}

	//WebDriver driver = null;
	
	/*FileInputStream fis=new FileInputStream(new File("/SelAdv/data.xls"));  
	 
	HSSFWorkbook wb=new HSSFWorkbook(fis);   
 
	HSSFSheet sheet=wb.getSheet("SignUp"); 
	
	for (Row row:sheet) {
		
		String userType=row.getCell(0).getCellStyle().getDataFormatString();
		String name=row.getCell(1).getCellStyle().getDataFormatString();
		String empID=row.getCell(2).getCellStyle().getDataFormatString();
		String gender=row.getCell(3).getCellStyle().getDataFormatString();
		String location=row.getCell(4).getCellStyle().getDataFormatString();
		String emialID=row.getCell(5).getCellStyle().getDataFormatString();
		
	}*/

//	@BeforeMethod
//	public void initializeDriver() {
//		System.setProperty("webdriver.chrome.driver","/Users/dillipnayak/Downloads/chromedriver");
//		driver = new ChromeDriver();
//		driver.get("https://google.com");
//
//	}

}
