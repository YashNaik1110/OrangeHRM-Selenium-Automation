package AdminPage;

import java.time.Duration;
import java.util.List;

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
   @Test(priority = 0 , enabled = false)
   public void TC_01() {
	   login();
	   WebElement heading = driver.findElement(By.className("oxd-topbar-header-breadcrumb"));
	   wait.until(ExpectedConditions.visibilityOf(heading));
	   soft.assertTrue(heading.isDisplayed(), "Heading is not Displayed");
	   soft.assertAll();
	   System.out.println("TC_01 : PASSED");
   }
   @Test(priority = 1, enabled = false)
   public void TC_02() {
	   login();
	   String expected_url = "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers";
	   wait.until(ExpectedConditions.urlMatches(expected_url));
	   String  actual_url = driver.getCurrentUrl();
	   soft.assertEquals(actual_url, expected_url,"Admin page url is incorrect");
	   soft.assertAll();
	   System.out.println("TC_02 : PASSED");
	   
   }
   @Test(priority = 2 , enabled = false)
   public void TC_03() {
	   login();
	   boolean user_management = driver.findElement(By.xpath("//h5[text()='System Users']")).isDisplayed();

	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	   soft.assertTrue(user_management,"User Management section is not Displayed");
	   soft.assertAll();
	    System.out.println("TC_03 : PASSED");
   }
   @Test(priority = 3 , enabled = false)
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
   @Test(priority = 4 , enabled = false)
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
   @Test(priority = 5, enabled = false)
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
   @Test(priority = 6  , enabled = false)
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
   @Test(priority = 7,enabled = false)
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
   
   @Test(priority = 8 , enabled = false)
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
   @Test(priority = 9 ,enabled = false)
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


   
   @Test(priority = 10 , enabled =  false)
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
 @Test(priority =  12 , enabled = true)
 public void TC_13() throws InterruptedException {
	 login();
	 WebElement username = driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
	 username.sendKeys("Admin");
	 driver.findElement(By.xpath("(//i[contains(@class,'oxd-icon bi-caret-down-fill')])[2]")).click();
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='Admin']"))).click();
	 WebElement status = driver.findElement(By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]"));
	 status.click();
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='option']//span[text()='Enabled']"))).click();
	 WebElement reset =driver.findElement(By.xpath("//button[text()=' Reset ']"));
	 reset.click();
	 soft.assertEquals(username.getAttribute("value"), "", "Username is not cleared"); 
	 WebElement userRoleValue = driver.findElement(By.xpath("//div[contains(@class,'oxd-select-text-input')]"));
     soft.assertEquals(userRoleValue.getText(),"-- Select --","User Role was not reset");
     WebElement statusValue = driver.findElement(By.xpath("//div[contains(@class,'oxd-select-text-input')]"));
     soft.assertEquals(statusValue.getText(),"-- Select --","Status was not reset");
	 soft.assertAll();
	 System.out.println("TC_13 : PASSED");

 }
 @Test(priority = 13, enabled = true)
 public void TC_14() {
	 login();
   WebElement user_table = driver.findElement(By.xpath("(//div[contains(@class,'oxd-table')])[6]"));
   soft.assertTrue(user_table.isDisplayed(),"User Table is not Displayed");
   soft.assertAll();
   System.out.println("TC_14 : PASSED");
 }
 
 @Test (priority = 14 , enabled = true)
 public void TC_15() {
	    login();
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='']")).isDisplayed(),"Checkbox column is not displayed");
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='Username']")).isDisplayed(),"Username column is not displayed");
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='User Role']")).isDisplayed(),"User Role column is not displayed");
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='Employee Name']")).isDisplayed(),"Employee Name column is not displayed");
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='Status']")).isDisplayed(),"Status column is not displayed");
	    soft.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'oxd-table-header')]//div[text()='Actions']")).isDisplayed(),"Actions column is not displayed");
	    soft.assertAll();
	    System.out.println("TC_15: PASSED");
	}
 @Test(priority = 15 , enabled =true)
 public void TC_16() {
     login();
     WebElement tableBody = driver.findElement(By.xpath("//div[contains(@class,'oxd-table-body')]"));
     List<WebElement> records = tableBody.findElements(By.xpath(".//div[contains(@class,'oxd-table-card')]"));
     soft.assertTrue(records.size() > 0,"No user records are displayed");
     System.out.println("Total user records displayed: " + records.size());
     soft.assertAll();
     System.out.println("TC_16: PASSED");
 }
@Test (priority =  16 , enabled = true)
public void TC_17() {
	login();
    driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
    WebElement addUserHeading = driver.findElement(By.xpath("//h6[normalize-space()='Add User']"));
    soft.assertTrue(addUserHeading.isDisplayed(),"Add User page is not displayed");
    soft.assertAll();
    System.out.println("TC_17 : PASSED");
}
@Test (priority = 17 , enabled =true)
public void TC_18() {
		login();
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
	    soft.assertTrue(driver.findElement(By.xpath("//h6[text()='Add User']")).isDisplayed(),"Add User page is not displayed");
	    driver.findElement(By.xpath("//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")).click();
	    driver.findElement(By.xpath("//div[@role='option']//span[text()='ESS']")).click();
	    WebElement employeeName = driver.findElement(By.xpath("//label[text()='Employee Name']/../following-sibling::div//input"));
	    employeeName.sendKeys("Yash Naik");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='option']")));
	    driver.findElement(By.xpath("//div[@role='option']//span[contains(text(),'Linda Anderson')]")).click();
	    driver.findElement(By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")).click();
	    driver.findElement(By.xpath("//div[@role='option']//span[text()='Enabled']")).click();
	    driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div//input")).sendKeys("TestUser" + System.currentTimeMillis());
	    List<WebElement> passwordFields = driver.findElements(By.xpath("//input[@type='password']"));
	    passwordFields.get(0).sendKeys("Yash123");
	    passwordFields.get(1).sendKeys("Yash123");
	    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'oxd-toast')]")));

	    WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class,'oxd-toast')]"));
	    soft.assertTrue(successMessage.isDisplayed(),"User creation success message is not displayed");
	    soft.assertTrue(successMessage.getText().contains("Successfully Saved"),"User was not created successfully");
	    soft.assertAll();
	    System.out.println("TC_18 : PASSED");
}
   @Test(priority = 18 , enabled = false)
public void TC_19() throws InterruptedException {
	login();
    driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
    Thread.sleep(2000);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    Thread.sleep(2000);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")));
    Thread.sleep(2000);
    List<WebElement> errorMessages = driver.findElements(By.xpath("//span[contains(@class,'oxd-input-field-error-message')]"));
    Thread.sleep(2000);
    soft.assertTrue(errorMessages.size() > 0,"Mandatory field validation messages are not displayed");
    Thread.sleep(2000);
    soft.assertAll();
    System.out.println("Validation messages displayed: " + errorMessages.size());
    System.out.println("TC_19 : PASSED");
}
@Test(priority = 19 , enabled = false)
public void TC_20() {
	login();
    driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
    List<WebElement> passwordFields = driver.findElements(By.xpath("//input[@type='password']"));
    passwordFields.get(0).sendKeys("Yash@12345");
    passwordFields.get(1).sendKeys("Yash@54321");
    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Passwords do not match']")));
    soft.assertTrue(errorMessage.isDisplayed(),"Password mismatch validation is not displayed");
    System.out.println("Password mismatch message: "+ errorMessage.getText());
    System.out.println("TC_20: PASSED");
}



@Test(priority =20, enabled = false)
public void TC_21() {
 login();
 driver.findElement(By.xpath("//span[text()='Admin']")).click();
 driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
 soft.assertTrue(driver.getCurrentUrl().contains("/admin/saveSystemUser"),"Add User page was not opened");
 driver.findElement(By.xpath("//label[text()='Username']/following::input[1]")).sendKeys("TestUser123");
 driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
 soft.assertTrue( driver.getCurrentUrl().contains("/admin/viewSystemUsers"),"User was not returned to Admin page");
 soft.assertAll();
}

@Test(priority = 21, enabled = false)
public void TC_22() {
    login();
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//label[text()='Username']/following::input[1]")));
    username.sendKeys("Admin");
    WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Search']")));
    searchButton.click();
    WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'bi-pencil-fill')]")));
    edit.click();
    soft.assertTrue(driver.getCurrentUrl().contains("admin/saveSystemUser/1"),"Edit User page was not opened");
    WebElement editUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/following::input[1]")));
    editUsername.click();
    editUsername.sendKeys(Keys.CONTROL, "a");
    editUsername.sendKeys(Keys.BACK_SPACE);
    editUsername.sendKeys("YashNaik");
    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
    saveButton.click();
    wait.until(ExpectedConditions.urlContains("/admin/viewSystemUsers"));
    soft.assertTrue(driver.getCurrentUrl().contains("/admin/viewSystemUsers"),"User was not returned to Admin User Management page");
    soft.assertAll();
}

@Test(priority = 22, enabled = true)
public void TC_23() {

    login();

    // --------------------------------------------------------
    // Search user
    // --------------------------------------------------------

    WebElement username = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//label[text()='Username']/following::input[1]"
                    )
            )
    );

    username.sendKeys("Mandas");

    // --------------------------------------------------------
    // Click Search
    // --------------------------------------------------------

    WebElement searchButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Search']")
            )
    );

    searchButton.click();

    // --------------------------------------------------------
    // Wait for first row
    // --------------------------------------------------------

    WebElement firstRow = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                    )
            )
    );

    soft.assertTrue(
            firstRow.isDisplayed(),
            "User record was not displayed"
    );

    // --------------------------------------------------------
    // Select user checkbox
    // --------------------------------------------------------

    WebElement checkbox = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                                    + "//i[contains(@class,'oxd-checkbox-input-icon')]"
                    )
            )
    );

    checkbox.click();

    // --------------------------------------------------------
    // Click Delete Selected
    // --------------------------------------------------------

    WebElement deleteButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//button[normalize-space()='Delete Selected']"
                    )
            )
    );

    deleteButton.click();

    // --------------------------------------------------------
    // Verify confirmation popup
    // --------------------------------------------------------

    WebElement popup = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'oxd-dialog-container')]"
                    )
            )
    );

    soft.assertTrue(
            popup.isDisplayed(),
            "Delete confirmation popup was not displayed"
    );

    // --------------------------------------------------------
    // Click Yes, Delete
    // --------------------------------------------------------

    WebElement yesDeleteButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//button[normalize-space()='Yes, Delete']"
                    )
            )
    );

    yesDeleteButton.click();

    // --------------------------------------------------------
    // Verify success message
    // --------------------------------------------------------

    WebElement message = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//*[contains(normalize-space(),'Successfully Deleted')]"
                    )
            )
    );

    soft.assertTrue(
            message.isDisplayed(),
            "User was not deleted successfully"
    );

    soft.assertAll();
}

// ============================================================
// TC_ADMIN_026
// Verify Cancel Delete
// ============================================================

@Test(priority = 23, enabled = true)
public void TC_24() {

    login();

    // --------------------------------------------------------
    // Search user
    // --------------------------------------------------------

    WebElement username = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//label[text()='Username']/following::input[1]"
                    )
            )
    );

    username.sendKeys("Admin");

    // --------------------------------------------------------
    // Click Search
    // --------------------------------------------------------

    WebElement searchButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Search']")
            )
    );

    searchButton.click();

    // --------------------------------------------------------
    // Select first user
    // --------------------------------------------------------

    WebElement checkbox = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                                    + "//i[contains(@class,'oxd-checkbox-input-icon')]"
                    )
            )
    );

    checkbox.click();

    // --------------------------------------------------------
    // Click Delete Selected
    // --------------------------------------------------------

    WebElement deleteButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//button[normalize-space()='Delete Selected']"
                    )
            )
    );

    deleteButton.click();

    // --------------------------------------------------------
    // Verify popup
    // --------------------------------------------------------

    WebElement popup = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'oxd-dialog-container')]"
                    )
            )
    );

    soft.assertTrue(
            popup.isDisplayed(),
            "Delete confirmation popup was not displayed"
    );

    // --------------------------------------------------------
    // Click Cancel
    // --------------------------------------------------------

    WebElement cancelButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Cancel']")
            )
    );

    cancelButton.click();

    // --------------------------------------------------------
    // Verify popup closed
    // --------------------------------------------------------

    wait.until(
            ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'oxd-dialog-container')]"
                    )
            )
    );

    boolean popupClosed = driver.findElements(
            By.xpath(
                    "//div[contains(@class,'oxd-dialog-container')]"
            )
    ).isEmpty();

    soft.assertTrue(
            popupClosed,
            "Delete popup was not closed"
    );

    soft.assertAll();
}

// ============================================================
// TC_ADMIN_027
// Verify User Checkbox
// ============================================================

@Test(priority = 24, enabled = true)
public void TC_25() {

    login();


    // --------------------------------------------------------
    // Wait for first row
    // --------------------------------------------------------

    wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                    )
            )
    );

    // --------------------------------------------------------
    // Click checkbox
    // --------------------------------------------------------

    WebElement checkbox = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                                    + "//i[contains(@class,'oxd-checkbox-input-icon')]"
                    )
            )
    );

    checkbox.click();

    // --------------------------------------------------------
    // Verify checkbox
    // --------------------------------------------------------

    WebElement checkboxInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                                    + "//input[@type='checkbox']"
                    )
            )
    );

    soft.assertTrue(
            checkboxInput.isSelected(),
            "User checkbox was not selected"
    );

    soft.assertAll();
}

// ============================================================
// TC_ADMIN_028
// Verify Select All Checkbox
// ============================================================

@Test(priority = 25, enabled = true)
public void TC_26() {

    login();

    // --------------------------------------------------------
    // Wait for table header
    // --------------------------------------------------------

    wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'oxd-table-header')]"
                    )
            )
    );

    // --------------------------------------------------------
    // Click Select All checkbox
    // --------------------------------------------------------

    WebElement selectAll = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "//div[contains(@class,'oxd-table-header')]"
                                    + "//i[contains(@class,'oxd-checkbox-input-icon')]"
                    )
            )
    );

    selectAll.click();

    // --------------------------------------------------------
    // Verify Select All checkbox
    // --------------------------------------------------------

    WebElement selectAllInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//div[contains(@class,'oxd-table-header')]"
                                    + "//input[@type='checkbox']"
                    )
            )
    );

    soft.assertTrue(
            selectAllInput.isSelected(),
            "Select All checkbox was not selected"
    );

    soft.assertAll();
}

// ============================================================
// TC_ADMIN_029
// Verify Pagination
// ============================================================

@Test(priority = 26, enabled = true)
public void TC_27() {

    login();

    // --------------------------------------------------------
    // Wait for first row
    // --------------------------------------------------------

    WebElement firstRow = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                    )
            )
    );

    String beforePage = firstRow.getText();

    System.out.println(
            "First page first row: " + beforePage
    );

    // --------------------------------------------------------
    // Find Next button
    // --------------------------------------------------------

    WebElement nextButton = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//button[@aria-label='Next']"
                    )
            )
    );

    // --------------------------------------------------------
    // Check whether Next is enabled
    // --------------------------------------------------------

    if (!nextButton.isEnabled()) {

        System.out.println(
                "Next page is not available."
        );

        return;
    }

    // --------------------------------------------------------
    // Click Next
    // --------------------------------------------------------

    nextButton.click();

    // --------------------------------------------------------
    // Wait for first row to change
    // --------------------------------------------------------

    wait.until(
            ExpectedConditions.not(
                    ExpectedConditions.textToBe(
                            By.xpath(
                                    "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
                            ),
                            beforePage
                    )
            )
    );

    // --------------------------------------------------------
    // Get first row after next page
    // --------------------------------------------------------

    String afterPage = driver.findElement(
            By.xpath(
                    "(//div[@class='oxd-table-body']//div[@role='row'])[1]"
            )
    ).getText();

    System.out.println(
            "Second page first row: " + afterPage
    );

    // --------------------------------------------------------
    // Verify different page
    // --------------------------------------------------------

    soft.assertNotEquals(
            beforePage,
            afterPage,
            "Next page was not displayed"
    );

    soft.assertAll();
}

// ============================================================
// TC_ADMIN_030
// Verify Records Per Page
// ============================================================

@Test(priority = 27, enabled = true)
public void TC_28() {

    login();


    // --------------------------------------------------------
    // Find dropdowns
    // --------------------------------------------------------

    List<WebElement> dropdowns = driver.findElements(
            By.xpath(
                    "//div[contains(@class,'oxd-select-text')]"
            )
    );

    // --------------------------------------------------------
    // Check dropdown availability
    // --------------------------------------------------------

    if (dropdowns.isEmpty()) {

        System.out.println(
                "Records per page option is not available "
                        + "in current OrangeHRM version."
        );

        return;
    }

    // --------------------------------------------------------
    // Click dropdown
    // --------------------------------------------------------

    WebElement recordsDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath(
                            "(//div[contains(@class,'oxd-select-text')])[1]"
                    )
            )
    );

    recordsDropdown.click();

    // --------------------------------------------------------
    // Wait for options
    // --------------------------------------------------------

    List<WebElement> options = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.xpath("//div[@role='option']")
            )
    );

    boolean option20Found = false;

    // --------------------------------------------------------
    // Find 20 option
    // --------------------------------------------------------

    for (WebElement option : options) {

        String optionText = option.getText().trim();

        System.out.println(
                "Available option: " + optionText
        );

        if (optionText.equals("20")) {

            option.click();

            option20Found = true;

            break;
        }
    }

    // --------------------------------------------------------
    // Verify 20 option
    // --------------------------------------------------------

    if (!option20Found) {

        System.out.println(
                "20 records option is not available "
                        + "in current OrangeHRM version."
        );

        return;
    }

    // --------------------------------------------------------
    // Wait for table
    // --------------------------------------------------------

    wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(
                            "//div[@class='oxd-table-body']//div[@role='row']"
                    )
            )
    );

    // --------------------------------------------------------
    // Count rows
    // --------------------------------------------------------

    int rowCount = driver.findElements(
            By.xpath(
                    "//div[@class='oxd-table-body']//div[@role='row']"
            )
    ).size();

    System.out.println(
            "Number of records displayed: " + rowCount
    );

    // --------------------------------------------------------
    // Verify records
    // --------------------------------------------------------

    soft.assertTrue(
            rowCount <= 20,
            "Records per page was not changed correctly"
    );

    soft.assertAll();
}

  @AfterMethod
  public void tear() {
	  driver.quit();
  }
}
