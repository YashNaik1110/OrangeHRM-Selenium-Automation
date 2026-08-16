package LoginPage;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;


public class VerifyLoginPage {
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert soft = new SoftAssert();
	@BeforeMethod
	public void setup() {
		
		   driver = new ChromeDriver();
		   driver.manage().window().maximize();
		   driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		   
	}
	@Test(priority = 0 , enabled = true)
	public void TC_01() {
		
		
		String actual_Title = driver.getTitle();
		String expected_Title  =  "OrangeHRM";
		
		Assert.assertEquals(actual_Title, expected_Title , "Page title is not correct");
		String expected_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		String actual_URL = driver.getCurrentUrl();
		soft.assertEquals(actual_URL, expected_URL,"Page url is not correct");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		boolean logo = driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
		soft.assertEquals(logo,"Logo is not displayed");
		boolean username = driver.findElement(By.name("username")).isDisplayed();
		soft.assertEquals(username, "Username text field is not displayed");
		boolean password = driver.findElement(By.name("password")).isDisplayed();
		soft.assertEquals(password, "Password text field is not displayed ");
	    boolean login_Button = driver.findElement(By.tagName("button")).isDisplayed();
	    soft.assertEquals(login_Button, "Login button is not displayed");
	    
	    System.out.println("TC_01 PASS: OrangeHRM Login Page loaded successfully.");
		
	}
	
	@Test(priority = 1,enabled = true)
	public void TC_02() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		By usernameLocator = By.name("username");
        boolean isDisplayed = driver.findElement(usernameLocator).isDisplayed();
        soft.assertTrue(isDisplayed,"Username field is not displayed");
        driver.findElement(usernameLocator).click();
        String username = "Admin";
        driver.findElement(usernameLocator).sendKeys(username);
        String enteredUsername = driver.findElement(usernameLocator).getAttribute("value");
        soft.assertEquals(enteredUsername, username,"Username was not entered successfully");
        System.out.println("TC_02 PASS: Username field accepted the username successfully.");
    }
	@Test(priority = 2,enabled = true)
	public void TC_03() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement passwordLocator = driver.findElement(By.name("password"));
        boolean isDisplayed = passwordLocator.isDisplayed();
        soft.assertTrue(isDisplayed,"Password field is not displayed");
        passwordLocator.click();
        String password = "admin123";
        passwordLocator.sendKeys(password);
        String enteredPassword = passwordLocator.getAttribute("value");
        soft.assertEquals(enteredPassword, password,"Password was not entered successfully");
        System.out.println("TC_03 PASS: Password field accepted the password successfully.");
    }
	@Test(priority = 3,enabled = true)
	public void TC_04(){
		String expected_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement loginButton = driver.findElement(By.tagName("button"));
		boolean visibility = loginButton.isDisplayed();
		soft.assertEquals(visibility, "Login Button is not visible");
		boolean enabled = loginButton.isEnabled();
		soft.assertEquals(enabled, "Login button is not enabled");
		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("Admin");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("admin123");
		loginButton.click();
	
		String actual_URL = driver.getCurrentUrl();
		if(expected_URL.equals(actual_URL)) {

			System.out.println("TC_04: Login Button verification passed successfully");
		}
		else {
			System.out.println("User is not Logged in ");
		}
	}
  @Test(priority = 4,enabled = true)
  public void TC_05() {
	  wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement username = driver.findElement(By.name("username"));
	  username.sendKeys("Admin");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement password = driver.findElement(By.name("password"));
	  password.sendKeys("admin123");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement loginButton = driver.findElement(By.tagName("button"));
	  loginButton.click();
	  WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
	boolean visibilty = dashboard.isDisplayed();
	soft.assertEquals(visibilty, "Dashboard is not visible ");
	System.out.println("TC_05: Login with valid credentials passed successfully");	  
  }
  @Test(priority = 5 , enabled = true)
  public void TC_06() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement username = driver.findElement(By.name("username"));
	  username.sendKeys("Yash");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement password = driver.findElement(By.name("password"));
      password.sendKeys("admin123");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
      loginButton.click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement errorMessage = driver.findElement(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
      boolean errorVisible = errorMessage.isDisplayed();
      soft.assertTrue(errorVisible,"Error message is not displayed");
      String actualMessage = errorMessage.getText();
      String expectedMessage = "Invalid credentials";
      soft.assertEquals(actualMessage,expectedMessage,"Incorrect error message displayed");

      System.out.println("TC_06: Invalid Username test passed successfully");

  }
  @Test(priority = 6 , enabled = true)
  public void TC_07() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement username = driver.findElement(By.name("username"));
	  username.sendKeys("Admin");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  WebElement password = driver.findElement(By.name("password"));
      password.sendKeys("yash123");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
      loginButton.click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement errorMessage = driver.findElement(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
      boolean errorVisible = errorMessage.isDisplayed();
      soft.assertTrue(errorVisible,"Error message is not displayed");
      String actualMessage = errorMessage.getText();
      String expectedMessage = "Invalid credentials";
      soft.assertEquals(actualMessage,expectedMessage,"Incorrect error message displayed");

      System.out.println("TC_07: Invalid Password test passed successfully");

  }
  @Test(priority = 7 , enabled = true)
  public void TC_08() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
      loginButton.click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
      WebElement requiredMessage = driver.findElement(By.xpath("//span[text()='Required']"));
      boolean errorVisible = requiredMessage.isDisplayed();
      soft.assertTrue(errorVisible,"Error message is not displayed");
      String actualMessage = requiredMessage.getText();
      String expectedMessage = "Required";
      soft.assertEquals(actualMessage,expectedMessage,"Requied message displayed");

      System.out.println("TC_08: Blank Input test passed successfully");

  }
	@AfterMethod
	public void tear() {
		driver.quit();
	}
	
}
