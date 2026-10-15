package AdminPage;


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

	public class VerifyJobPage {
	    WebDriver driver;
	    WebDriverWait wait;
	    String baseUrl ="https://opensource-demo.orangehrmlive.com";
	    String username = "Admin";
	    String password = "admin123";
	    String jobTitle = "QA Engineer 101";
	    String jobDescription = "Automation Testing Engineer";
	    String manualTitle = "Manual Tester 101";
	    String manualDescription ="Manual Testing and Test Case Execution";
	    String updatedDescription ="Updated Automation Testing Description";

	    @BeforeMethod
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        driver.get(baseUrl + "/web/index.php/auth/login");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
	        driver.findElement(By.name("password")).sendKeys(password);
	        driver.findElement(By.cssSelector("button[type='submit']")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
	    }
	    public void openJobTitles() {

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Job ']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Job Titles']"))).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Job Titles']")));
	    }

	    // Click Add button
	    public void clickAdd() {

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']"))).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add Job Title']")));
	    }

	    // Locate form fields
	    public WebElement titleField() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Job Title']/../following-sibling::div//input")));
	    }

	    public WebElement descriptionField() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Job Description']/../following-sibling::div//textarea")));
	    }

	    public void saveJobTitle(String title, String description) {

	        titleField().sendKeys(title);
	        descriptionField().sendKeys(description);

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']"))).click();
	    }

	    // Find a job title row by its title
	    public WebElement jobRow(String title) {

	        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row'][.//div[@role='cell' and normalize-space()='"+ title + "']]")));
	    }


	    @Test(priority = 0 , enabled = true)
	    public void TC_01() {
	        openJobTitles();
	        Assert.assertTrue( driver.getCurrentUrl().contains("/admin/viewJobTitleList"),"Job Titles page URL is incorrect");
	    }

	  
	    @Test(priority = 1 , enabled = true)
	    public void TC_02() {
	        openJobTitles();
	        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Job Titles']"))).isDisplayed(),"Job Titles heading is not visible");
	    }

	    @Test(priority = 2 , enabled = true)
	    public void TC_03() {
	        openJobTitles();
	        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add']")));
	        Assert.assertTrue(addButton.isDisplayed());
	        Assert.assertTrue(addButton.isEnabled());
	        addButton.click();
	        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add Job Title']"))).isDisplayed());
	    }


	@Test(priority = 3, enabled = true)
	public void TC_04() {

	    openJobTitles();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
	    System.out.println("Current URL: " + driver.getCurrentUrl());
	    System.out.println("Page Title: " + driver.getTitle());
	    System.out.println("Page Text:");
	    System.out.println(driver.findElement(By.tagName("body")).getText());
	    System.out.println("Table-related HTML:");
	    java.util.List<WebElement> elements =driver.findElements(By.xpath("//*[contains(@class,'table') or " +"contains(@class,'header') or " +"@role='columnheader']"));

	    for (WebElement element : elements) {String text = element.getText().trim();
	    if (!text.isEmpty()) {System.out.println("Tag: " + element.getTagName()+ " | Class: " + element.getAttribute("class")+ " | Text: " + text);
	        }
	    }

	    // Temporary assertion: verify the expected page is open
	    Assert.assertTrue(driver.getCurrentUrl().contains("viewJobTitleList"),"Job Titles page did not open correctly.");
	}

	    // TC_JOB_05


	@Test(priority = 4, enabled = true)
	public void TC_JOB_05_VerifyExistingRecords() {

	    openJobTitles();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".oxd-table")));

	    By rowsLocator = By.cssSelector(".oxd-table-body .oxd-table-row");

	    try {
	        wait.until(driver ->!driver.findElements(rowsLocator).isEmpty()|| driver.findElements(By.xpath("//*[contains(text(),'No Records Found')]")).size() > 0);
	    } 
	    catch (org.openqa.selenium.TimeoutException e) {
	        System.out.println("Table did not finish loading.");
	    }

	    int rows = driver.findElements(rowsLocator).size();
	    System.out.println("Number of job title rows: " + rows);
	    System.out.println("Table content: " +driver.findElement(By.cssSelector(".oxd-table")).getText());
	    Assert.assertTrue(rows > 0,"No existing job title records found. Check the table content printed above.");
	}
	    @Test(priority = 5 , enabled = true)
	    public void TC_06() {

	        openJobTitles();
	        clickAdd();
	        Assert.assertTrue(titleField().isDisplayed());
	        Assert.assertTrue(descriptionField().isDisplayed());
	        Assert.assertTrue( driver.findElement(By.xpath("//button[normalize-space()='Save']")).isDisplayed());

	        Assert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).isDisplayed());
	    }

	    // TC_JOB_07
	    @Test(priority = 6 , enabled = true)
	    public void TC_07() {

	        openJobTitles();
	        clickAdd();
	        String uniqueTitle ="QA Engineer " + System.currentTimeMillis();

	        saveJobTitle(uniqueTitle, jobDescription);

	        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='row'][.//div[@role='cell' and normalize-space()='"+ uniqueTitle + "']]"))).isDisplayed(),"New job title was not found");
	    }


	 // TC_08 - Verify mandatory field validation
	 @Test(priority = 7, enabled = true)
	 public void TC_08() {

	     // Open Job Titles page
	     openJobTitles();

	     // Click Add button
	     clickAdd();

	     // Enter only the Job Description
	     descriptionField().sendKeys("Test description");

	     // Click Save
	     By saveButton = By.xpath("//button[normalize-space()='Save']");

	     wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

	     // Wait for the required-field validation message
	     By errorMessage = By.xpath(
	         "//span[contains(@class,'oxd-input-field-error-message')]"
	     );

	     WebElement error = wait.until(
	         ExpectedConditions.visibilityOfElementLocated(errorMessage)
	     );

	     // Verify validation message is displayed
	     Assert.assertTrue(
	         error.isDisplayed(),
	         "Required-field validation message not displayed"
	     );

	     // Verify the form remains open
	     Assert.assertTrue(
	         driver.getCurrentUrl().contains("/admin/saveJobTitle"),
	         "Form unexpectedly closed after validation"
	     );

	     System.out.println(
	         "TC_08 Passed: Required-field validation displayed successfully."
	     );
	 }
	 
	    @Test(priority = 8 , enabled = true)
	    public void TC_09() {

	        openJobTitles();
	        clickAdd();
	        String uniqueTitle ="Manual Tester " + System.currentTimeMillis();

	        saveJobTitle(uniqueTitle, manualDescription);

	        WebElement row = jobRow(uniqueTitle);

	        Assert.assertTrue(row.getText().contains(uniqueTitle));
	        Assert.assertTrue(row.getText().contains(manualDescription),"Description was not saved correctly");
	    }
	    
	    @Test(priority = 9 , enabled = true)
	    public void TC_10() {
	        openJobTitles();
	        clickAdd();

	        String cancelledTitle ="Cancel Test " + System.currentTimeMillis();
	        titleField().sendKeys(cancelledTitle);
	        descriptionField().sendKeys("Temporary description");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Cancel']"))).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Job Titles']")));

	        Assert.assertTrue(driver.getCurrentUrl().contains("/admin/viewJobTitleList"));

	        Assert.assertEquals(driver.findElements(By.xpath("//div[@role='row'][.//div[@role='cell' and normalize-space()='"+ cancelledTitle + "']]")).size(),0,"Cancelled job title should not be saved");
	    }

	    @Test(priority = 10, enabled = true)
	    public void TC_11() {
	    openJobTitles();
	    clickAdd();
	    String uniqueTitle = "Edit QA " + System.currentTimeMillis();
	    saveJobTitle(uniqueTitle, jobDescription);
	    WebElement row = jobRow(uniqueTitle);
	    row.findElement(By.xpath(".//button[.//i[contains(@class,'pencil-fill')]]")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Edit Job Title']")));
	    WebElement description = descriptionField();
	    wait.until(ExpectedConditions.visibilityOf(description));
	    description.click();
	    description.sendKeys(org.openqa.selenium.Keys.CONTROL, "a");
	    description.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
	    description.sendKeys(updatedDescription);
	    String actualDescription = description.getAttribute("value");
	    Assert.assertEquals(actualDescription,updatedDescription,"Description field was not updated before saving");
	}



	    // TC_JOB_12
	    @Test(priority = 11 , enabled = true)
	    public void TC_JOB_12_VerifyUpdatedDetailsAfterRefresh() {
	        openJobTitles();
	        clickAdd();
	        String uniqueTitle ="Refresh QA " + System.currentTimeMillis();
	        saveJobTitle(uniqueTitle, jobDescription);
	        WebElement row = jobRow(uniqueTitle);
	        row.findElement(By.xpath(".//button[.//i[contains(@class,'pencil-fill')]]")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Edit Job Title']")));
	        WebElement description = descriptionField();
	        description.click();
	        description.sendKeys(org.openqa.selenium.Keys.CONTROL, "a");
	        description.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
	        description.sendKeys(updatedDescription);
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Save']"))).click();
	        Assert.assertTrue(jobRow(uniqueTitle).getText().contains(updatedDescription));
	        driver.navigate().refresh();
	        openJobTitles();
	        Assert.assertTrue(jobRow(uniqueTitle).getText().contains(updatedDescription),"Updated description did not persist after refresh");
	    }

	    // Close browser after each test
	    @AfterMethod(alwaysRun = true)
	    public void tearDown() {

	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


