package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
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
	public static WebDriver driver =null;
	//initialize driver
	@BeforeMethod
	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver","/Users/dillipnayak/Downloads/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test 
	public void test01() throws IOException {
		System.out.println("Running test 01");
		driver.get("https://www.google.ca/");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		logger.info("Title of the page <<>"+driver.getTitle()+">");
		logger.info("No of Hyperlinks <<"+driver.findElements(By.tagName("//a")).size()+">>");

		WebElement signUpLink= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement selLocation= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement txtName= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement txtEmpNo= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement radioGenderMale= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement radioGenderFemale= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement txtEmail= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement btnCreateUser= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");
		WebElement lableSuccess= ce("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input");

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
				Select uType= new Select(driver.findElement(By.id("ID of the user type dropdown")));
				uType.selectByVisibleText(userType);

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

		WebElement loginLink=ce("");
		WebElement btnAddChem=ce("");
		WebElement txtAddType=ce("");
		WebElement txtAddName=ce("");
		WebElement txtAddSrNo=ce("");
		WebElement txtAddQuantity=ce("");
		WebElement btnViewChem=ce("");
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

	public void switchWindow(String NewWinTitle) {
		//String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			driver.switchTo().window(child_window);
			if(child_window.equalsIgnoreCase(NewWinTitle)) {
				driver.switchTo().window(child_window);
			}
		}
	}

	public void closeWindows() {
		Set<String>s=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			driver.switchTo().window(child_window);
			//System.out.println(driver.switchTo().window(child_window).getTitle());
			driver.close();
			driver.quit();
		}
	}
	//release resources after test
	@AfterMethod
	public void release() {
		closeWindows();

	}

}
