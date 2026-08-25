package Dashboard;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyDashboardPage {
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
		   
	 }
   
   @Test(priority = 0 , enabled = true)
   public void TC_01() {
	   String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   driver.findElement(By.name("username")).sendKeys("Admin");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   driver.findElement(By.name("password")).sendKeys("admin123");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   driver.findElement(By.xpath("//button[text() = ' Login ']")).click();
	   
	   wait.until(ExpectedConditions.urlContains("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));
	   
	   String  actual_url = driver.getCurrentUrl();
	   
	   soft.assertEquals(actual_url,expected_url,"Dasboard url is incorrect");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   WebElement heading = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
	   
	   String actual_heading = heading.getText();
	   String expected_heading = "Dashboard";
	   soft.assertEquals(actual_heading, expected_heading,"Heading is not displayed");
	   
	   System.out.println("TC_01 : PASSED");
	   soft.assertAll();
	   
   }
   @Test(priority = 1 , enabled = true)
   public void TC_02() {
	   login();
	   String expected_tilte = "OrangeHRM";
	   String actual_title = driver.getTitle();
	   soft.assertEquals(actual_title, expected_tilte , "Title is incorrect");
	   System.out.println("TC_02 : PASSED");
	   soft.assertAll();
   }
    @Test(priority=2, enabled = true)
  public void TC_03() {
    	login();
	 WebElement search =  driver.findElement(By.xpath("//input[@class=\"oxd-input oxd-input--active\"]"));
	 search.click();
	 boolean search_visible = search.isDisplayed();
	 soft.assertTrue(search_visible, "Search functionality is not Displayed");
	 System.out.println("TC_03 : PASSED");
	 soft.assertAll();
  }
    @Test(priority = 3 , enabled = true)
    public void TC_04() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement admin = driver.findElement(By.xpath("//a[@class=\"oxd-main-menu-item\"]"));
    	wait.until(ExpectedConditions.elementToBeClickable(admin));
    	admin.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//span[@class=\"oxd-topbar-header-breadcrumb\"]"));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       System.out.println("TC_04 : PASSED");
       
       soft.assertAll();
    	
    }
    @Test(priority = 4 , enabled = true)
    public void TC_05() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement pim = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[2]"));
    	wait.until(ExpectedConditions.elementToBeClickable(pim));
    	pim.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='PIM']"));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       System.out.println("TC_05 : PASSED");
       
       soft.assertAll();
    	
    }
    @Test(priority = 5 , enabled = true)
    public void TC_06() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement leave = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[3]"));
    	wait.until(ExpectedConditions.elementToBeClickable(leave));
    	leave.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Leave']"));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       System.out.println("TC_06 : PASSED");
       
       soft.assertAll();
    	
    }
    @Test(priority = 6 , enabled = true)
    public void TC_07() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement time = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[4]"));
    	wait.until(ExpectedConditions.elementToBeClickable(time));
    	time.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_07 : PASSED");
       
      
    }
    @Test(priority = 7 , enabled = true)
    public void TC_08() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement requirement = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[5]"));
    	wait.until(ExpectedConditions.elementToBeClickable(requirement));
    	requirement.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Recruitment']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_08 : PASSED");
       
      
    }
    @Test(priority = 8 , enabled = true)
    public void TC_09() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement myInfo = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[6]"));
    	wait.until(ExpectedConditions.elementToBeClickable(myInfo));
    	myInfo.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='PIM']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_09 : PASSED");
       
      
    }
    @Test(priority = 9 , enabled = true)
    public void TC_10() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement performance = driver.findElement(By.xpath("(//a[@class=\"oxd-main-menu-item\"])[7]"));
    	wait.until(ExpectedConditions.elementToBeClickable(performance));
    	performance.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_10 : PASSED");
       
      
    }
    @Test(priority = 10 , enabled = true)
    public void TC_11() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement Dashboard = driver.findElement(By.xpath("//a[@class='oxd-main-menu-item active']"));
    	wait.until(ExpectedConditions.elementToBeClickable(Dashboard));
    	Dashboard.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_11 : PASSED");
       
      
    }
    @Test(priority = 11 , enabled = true)
    public void TC_12() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement Directory = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[9]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Directory));
    	Directory.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/directory/viewDirectory";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Directory']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_12 : PASSED");
       
      
    }
    @Test(priority = 12 , enabled = true)
    public void TC_13() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement Maintenance = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[10]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Maintenance));
    	Maintenance.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	driver.findElement(By.name("password")).sendKeys("admin123");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	driver.findElement(By.xpath("//button[text() = ' Confirm ']")).click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/maintenance/purgeEmployee";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_13 : PASSED");
       
      
    }
    @Test(priority = 13 , enabled = true)
    public void TC_14() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement  Claim = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[11]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Claim));
    	Claim.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Claim']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_14 : PASSED");
       
      
    }
    @Test(priority = 14 , enabled = true)
    public void TC_15() {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	login();
    	WebElement Buzz = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[12]"));
    	wait.until(ExpectedConditions.elementToBeClickable(Buzz));
    	Buzz.click();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/buzz/viewBuzz";
       wait.until(ExpectedConditions.urlContains(expected_url));
       String actual_url = driver.getCurrentUrl();
       soft.assertEquals(actual_url, expected_url,"URL is incorrect");
       
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       WebElement heading = driver.findElement(By.xpath("//h6[text()='Buzz']"));
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       boolean verify_heading = heading.isDisplayed();
       soft.assertTrue(verify_heading, "Heading is not dispalyed");
       soft.assertAll();
   
       System.out.println("TC_15 : PASSED");
       
      
    }
    
    @Test(priority = 15 , enabled =true)
    public void TC_016() {
    	login();
    	WebElement profile_info = driver.findElement(By.xpath("//div[contains(@class,'orangehrm-dashboard-widget-name')]"));
        soft.assertTrue(profile_info.isDisplayed(),"Time and Widget is not displayed is not dispalyed");
        soft.assertAll();
        System.out.println("TC_16 : PASSED");
    }
    @Test(priority = 16 , enabled =true)
    public void TC_017() {
    	login();
    	WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[2]"));
        soft.assertTrue(widget.isDisplayed(),"My Actions widget is not displayed");
        soft.assertAll();
        System.out.println("TC_17 : PASSED");
    }
    
    @Test(priority = 17 , enabled =true)
    public void TC_18() {
    	login();
    	WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[3]"));
        soft.assertTrue(widget.isDisplayed(),"Quick Launch widget is not displayed");
        soft.assertAll();
        System.out.println("TC_18 : PASSED");
    }
    


    @Test(priority = 18 , enabled = true)
    public void TC_19() {
        login();

        WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[4]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widget);

        soft.assertTrue(widget.isDisplayed(),"Buzz Latest Posts widget is not displayed");
        soft.assertAll();
        System.out.println("TC_19 : PASSED");
    }

    @Test(priority = 19 , enabled = true)
    public void TC_20() {
        login();

        WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[5]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widget);

        soft.assertTrue(widget.isDisplayed(),"Employees on Leave Today widget is not displayed");
        soft.assertAll();
        System.out.println("TC_20 : PASSED");
    }

    @Test(priority = 20 , enabled = true)
    public void TC_21() {
        login();

        WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[6]"));

        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", widget);

        soft.assertTrue(widget.isDisplayed(),"Employee Distribution by Sub Unit widget is not displayed");
        soft.assertAll();
        System.out.println("TC_21 : PASSED");
    }

    @Test(priority =  21 , enabled = true)
    public void TC_22() {
        login();

        WebElement widget = driver.findElement(By.xpath("(//div[contains(@class,'orangehrm-dashboard-widget-name')])[7]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widget);

        soft.assertTrue(widget.isDisplayed(),"Employee Distribution by Location widget is not displayed");
        soft.assertAll();
        System.out.println("TC_22 : PASSED");
    }
    @Test(priority = 22 , enabled = true)
    public void TC_23() {
        login();
        WebElement collapseButton = driver.findElement(By.xpath("//button[contains(@class,'oxd-main-menu-button')]"));
        collapseButton.click();
        WebElement sidebar = driver.findElement(By.xpath("//aside[contains(@class,'oxd-sidepanel')]"));

        soft.assertTrue(sidebar.getAttribute("class").contains("toggled"),"Sidebar is not collapsed");
        soft.assertAll();
        System.out.println("TC_23 : PASSED");
    }

    @Test(priority = 23 , enabled = true)
    public void TC_24() {
        login();
        WebElement collapseButton = driver.findElement(By.xpath("//button[contains(@class,'oxd-main-menu-button')]"));
        collapseButton.click();
        WebElement expandButton = driver.findElement(By.xpath("//button[contains(@class,'oxd-main-menu-button')]"));
        expandButton.click();
        WebElement sidebar = driver.findElement(By.xpath("//aside[contains(@class,'oxd-sidepanel')]"));

        soft.assertFalse(sidebar.getAttribute("class").contains("toggled"),"Sidebar is not expanded");
        soft.assertAll();
        System.out.println("TC_24 : PASSED");
    }

    @Test(priority = 24 , enabled = true )
    public void TC_25() {
        login();
        WebElement profileDropdown = driver.findElement(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]"));
        profileDropdown.click();
        WebElement dropdownMenu = driver.findElement(By.xpath("//ul[@class='oxd-dropdown-menu']"));

        soft.assertTrue(dropdownMenu.isDisplayed(),"Profile dropdown is not displayed");
        soft.assertAll();
        System.out.println("TC_25 : PASSED");
    }

    @Test(priority = 25 , enabled = true )
    public void TC_26() {
        login();
        WebElement profileDropdown = driver.findElement(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]"));
        profileDropdown.click();
        WebElement logoutButton = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
        logoutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/auth/login"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and contains(.,'Login')]"));

        soft.assertTrue(loginButton.isDisplayed(),"Login page is not displayed after logout");
        System.out.println("TC_26 : PASSED");
        
    }
    
    
   
   
   @AfterMethod
   public void tear() {
	   driver.quit();
   }
}
