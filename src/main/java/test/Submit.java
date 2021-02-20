package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
	WebElement signUpLink= null;
	WebElement selUserType= null;
	WebElement selLocation= null;
	WebElement txtName= null;
	WebElement txtEmpNo= null;
	WebElement radioGenderMale= null;
	WebElement radioGenderFemale= null;
	WebElement txtEmail= null;
	WebElement btnCreateUser= null;
	WebElement lableSuccess= null;
	

	WebElement loginLink= null;
	WebElement txtUsername= null;
	WebElement txtPassword= null;

	WebElement btnAddChem= null;
	WebElement btnViewChem= null;
	WebElement txtAddName= null;
	WebElement txtAddSrNo= null;
	WebElement txtAddType= null;
	WebElement txtAddQuantity= null;
			WebDriver driver =null;

	
	@BeforeMethod
	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/dillipnayak/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		logger.info("Title of the page <<>"+driver.getTitle()+">");
		logger.info("No of Hyperlinks <<"+driver.findElements(By.tagName("//a")).size()+">>");

	}
	
	public void createLocators() {
		 signUpLink= ce("");
		 selUserType= ce("");
		 selLocation= ce("");
		 txtName= ce("");
		 txtEmpNo= ce("");
		 radioGenderMale= ce("");
		 radioGenderFemale= ce("");
		 txtEmail= ce("");
		 btnCreateUser= ce("");
		 lableSuccess= ce("");
		

		 loginLink= ce("");
		 txtUsername= ce("");
		 txtPassword= ce("");

		 btnAddChem= ce("");
		 btnViewChem= ce("");
		 txtAddName= ce("");
		 txtAddSrNo= ce("");
		 txtAddType= ce("");
		 txtAddQuantity= ce("");

	}
	
	//copy xpath from UI into ""
		
	@Test (priority = 0)
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
		signUpLink.click();

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


	
	



	

}
