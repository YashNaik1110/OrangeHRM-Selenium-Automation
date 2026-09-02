package AdminPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyAdminPage {
	 WebDriver driver;
	 WebDriverWait wait ;
	 SoftAssert soft = new SoftAssert();
	 String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	  
	 
   @BeforeMethod
   public void setup() {
	   driver = new ChromeDriver();
	   driver.manage().window().maximize();
	   wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	   driver.get(url);
	   
   }
   public void login() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  driver.findElement(By.name("username")).sendKeys("Admin");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  driver.findElement(By.name("password")).sendKeys("admin123");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  driver.findElement(By.xpath("//button[text() = ' Login ']")).click();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  driver.findElement(By.xpath("//span[text()='Admin']")).click();
		   
	 }
   @Test(priority = 0 , enabled = true)
   public void TC_01() {
	   login();
	   WebElement heading = driver.findElement(By.className("oxd-topbar-header-breadcrumb"));
	   wait.until(ExpectedConditions.visibilityOf(heading));
	   soft.assertTrue(heading.isDisplayed(), "Heading is not Displayed");
	   soft.assertAll();
	   System.out.println("TC_01 : PASSED");
   }
   @Test(priority = 1, enabled = true)
   public void TC_02() {
	   login();
	   String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
	   wait.until(ExpectedConditions.urlMatches(expected_url));
	   String  actual_url = driver.getCurrentUrl();
	   soft.assertEquals(actual_url, expected_url,"Admin page url is incorrect");
	   soft.assertAll();
	   System.out.println("TC_02 : PASSED");
	   
   }
   @Test(priority = 2 , enabled = true)
   public void TC_03() {
	   login();
	   boolean user_management = driver.findElement(By.xpath("//h5[text()='System Users']")).isDisplayed();

	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   soft.assertTrue(user_management,"User Management section is not Displayed");
	   soft.assertAll();
	    System.out.println("TC_03 : PASSED");
   }
   @Test(priority = 3 , enabled = true)
   public void TC_04() {
	   login();
	  WebElement username_field =  driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
	  wait.until(ExpectedConditions.visibilityOf(username_field));
	  username_field.sendKeys("Admin");
	   String  username_search = username_field.getAttribute("value");
	   WebElement search = driver.findElement(By.xpath("//button[@type='submit']"));
	   search.click();
	   wait.until(ExpectedConditions.elementToBeClickable(search));
	  WebElement username =driver.findElement(By.xpath("//div[text()='Admin']"));
	  wait.until(ExpectedConditions.visibilityOf(username));
	  String actual_username = username.getText();
	  soft.assertEquals(username_search, actual_username , "Username is not matched");
	  
	  soft.assertAll();
	  System.out.println("TC_04 : PASSED");
	   
   }
   @Test(priority = 4 , enabled = true)
   public void TC_05() throws InterruptedException {
	   login();
	   	  WebElement username_field =  driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		  wait.until(ExpectedConditions.visibilityOf(username_field));
		  username_field.sendKeys("Yash");
		  WebElement search = driver.findElement(By.xpath("//button[@type='submit']"));
		  search.click();
		   wait.until(ExpectedConditions.elementToBeClickable(search));  
		   WebElement no_record_message = driver.findElement(By.xpath("//span[text()='No Records Found']"));
		   soft.assertTrue(no_record_message.isDisplayed(),"No Records Found message is not displayed");
		   soft.assertAll();
		   System.out.println("TC_05 : PASSED");
   }
   @Test(priority = 5, enabled = true)
   public void TC_06() {
	login();
	WebElement user_role = driver.findElement(By.xpath("//div[@class='oxd-select-text--after']"));
	user_role.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	WebElement admin = driver.findElement(By.xpath("//div[text()='Admin']"));
	user_role.click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	WebElement ess = driver.findElement(By.xpath("(//div[text()='ESS'])"));
	soft.assertTrue(admin.isDisplayed(),"Admin user role is not available");
	soft.assertTrue(ess.isDisplayed(),"ESS user role is not available");
	soft.assertAll();
   System.out.println("TC_06 : PASSED ");
   }
   @Test(priority = 6  , enabled = true)
   public void TC_07(){
	   login();

       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]"))).click();
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='Admin']"))).click();
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-table-body')]")));
       WebElement adminResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'oxd-table-cell')])[3]//div[text()='Admin']")));

       soft.assertTrue(adminResult.isDisplayed(),"User with Admin role is not displayed");
       soft.assertAll();
       System.out.println("TC_07 : PASSED");
   }
   @Test(priority = 7,enabled = true)
   public void TC_08(){
	   login();
       WebElement employeeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Name']/following::input[1]")));
       employeeName.sendKeys("a");
       WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option'][1]"))); 
       soft.assertTrue(suggestion.isDisplayed(),"Employee suggestion is not displayed");
       suggestion.click();
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']"))).click();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-table-body')]")));
       soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-body')]")).isDisplayed(),"Employee/User record is not displayed");
       soft.assertAll();
       System.out.println("TC_08: PASSED");
   }
   
   @Test(priority = 8 , enabled = true)
   public void TC_09(){
	   login();
       WebElement employeeName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Employee Name']/following::input[1]")));
       employeeName.sendKeys("a");
       WebElement suggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option'][1]")));
       suggestion.click();
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']"))).click();
       WebElement resultTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-table-body')]")));
       soft.assertTrue(resultTable.isDisplayed(),"Corresponding user record is not displayed");
      soft.assertAll();
      System.out.println("TC_09 : PASSED");
   }
   @Test(priority = 9 ,enabled = true)
   public void TC_10() {
	   login();
       WebElement statusDropdown = driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"));
       statusDropdown.click();
       WebElement enabled = driver.findElement(By.xpath("//div[@role='option']//span[text()='Enabled']"));
       WebElement disabled = driver.findElement(By.xpath("//div[@role='option']//span[text()='Disabled']"));
       soft.assertTrue(enabled.isDisplayed(),"Enabled option is not displayed");
       soft.assertTrue(disabled.isDisplayed(),"Disabled option is not displayed");
       soft.assertAll();
       System.out.println("TC_10 : PASSED");
   }


   
   @Test(priority = 10 , enabled =  true)
   public void TC_11() {
	   login();
       WebElement statusDropdown = driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"));
       statusDropdown.click();
       driver.findElement(By.xpath("//div[@role='option']//span[text()='Enabled']")).click();
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       WebElement results = driver.findElement(By.xpath("//div[contains(@class,'oxd-table-body')]"));
       soft.assertTrue(results.isDisplayed(),"Enabled users are not displayed");
       soft.assertAll();
       System.out.println("TC_11 : PASSED");
   }

   @Test(priority = 11 ,enabled = true)
   public void TC_12() {
	   login();
       WebElement statusDropdown = driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"));
       statusDropdown.click();
       driver.findElement(By.xpath("//div[@role='option']//span[text()='Disabled']")).click();
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       WebElement results = driver.findElement(By.xpath("//div[contains(@class,'oxd-table-body')]"));
       soft.assertTrue(results.isDisplayed(),"Disabled users are not displayed");
       soft.assertAll();
       System.out.println("TC_12 : PASSED");
   }



   
  @AfterMethod
  public void tear() {
	  driver.quit();
  }
}
