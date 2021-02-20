package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Submit {
	
	Logger logger = Logger.getLogger(Submit.class.getName()); 

	WebDriver driver =null;
	//copy xpath from UI into ""
	WebElement signUpLink= ce("");
	WebElement selUserType= ce("");
	WebElement selLocation= ce("");
	WebElement txtName= ce("");
	WebElement txtEmpNo= ce("");
	WebElement radioGenderMale= ce("");
	WebElement radioGenderFemale= ce("");
	WebElement txtEmail= ce("");
	WebElement btnCreateUser= ce("");
	WebElement lableSuccess= ce("");
	

	WebElement loginLink= ce("");
	WebElement txtUsername= ce("");
	WebElement txtPassword= ce("");

	WebElement btnAddChem= ce("");
	WebElement btnViewChem= ce("");
	WebElement txtAddName= ce("");
	WebElement txtAddSrNo= ce("");
	WebElement txtAddType= ce("");
	WebElement txtAddQuantity= ce("");
	
	@Test 
	public void test01() throws IOException {
		System.out.println("Running test 01");
		String userType="";
		String name="";
		String empID="";
		String gender="";
		String location="";
		String emialID="";
		FileInputStream fis=new FileInputStream(new File("/SelAdv/data.xls"));  
		HSSFWorkbook wb=new HSSFWorkbook(fis);   
		HSSFSheet sheet=wb.getSheet("SignUp"); 
		for (Row row:sheet) {
			signUpLink.click();
			 userType=row.getCell(0).getCellStyle().getDataFormatString();
			 name=row.getCell(1).getCellStyle().getDataFormatString();
			 empID=row.getCell(2).getCellStyle().getDataFormatString();
			 gender=row.getCell(3).getCellStyle().getDataFormatString();
			 location=row.getCell(4).getCellStyle().getDataFormatString();
			 emialID=row.getCell(5).getCellStyle().getDataFormatString();
			 if(userType.equalsIgnoreCase("Staff")) {
				 Select selUserType= new Select(driver.findElement(By.id("ID of the user type dropdown")));
				 selUserType.selectByVisibleText(userType);
				 
			 }
			 txtName.sendKeys(name);
			 txtEmpNo.sendKeys(empID);
			 if(gender.equalsIgnoreCase("Male")) {
				 radioGenderMale.click();
				 
			 }
			 else {
				 radioGenderFemale.click();
			 }
			 if(selLocation.getAttribute("disabled").equalsIgnoreCase("disabled")) {
				 
			 }
			 else {
				 Select selUserLocation= new Select(driver.findElement(By.id("ID of the user type dropdown")));
				 selUserLocation.selectByVisibleText(location);
			 }
			 txtEmail.sendKeys(emialID);
			 btnCreateUser.click();
			 logger.info(lableSuccess.getText());
			 
			 
		}
		

	}
	
	@Test 
	@Parameters({ "Type", "Name", "ServiceNo", "Quantity" })
	public void test02(String type, String name, String srNo, String quantity) {
		System.out.println(type+" "+name+" "+srNo+" "+quantity);
		loginLink.click();
		btnAddChem.click();
		txtAddType.sendKeys(type);
		txtAddName.sendKeys(name);
		txtAddSrNo.sendKeys(srNo);
		txtAddQuantity.sendKeys(quantity);
		btnViewChem.click();
	}

	public WebElement ce(String xpath) {
		WebElement ele=null;
		ele=driver.findElement(By.xpath(xpath));
		return ele;
	}

	@AfterMethod
	public void release() {
		driver.close();
		driver.quit();
	}


	@BeforeMethod
	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/dillipnayak/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		logger.info("Title of the page <<>"+driver.getTitle()+">");
		logger.info("No of Hyperlinks <<"+driver.findElements(By.tagName("//a")).size()+">>");

	}
	



	

}
