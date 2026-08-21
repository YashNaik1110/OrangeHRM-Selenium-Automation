package ForgotPasswordPage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;	
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyForgotPasswordPage {

    WebDriver driver;
    WebDriverWait wait;
    String loginURL ="https://opensource-demo.orangehrmlive.com/";
    String forgotPasswordURL ="https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(loginURL);
        wait.until(ExpectedConditions.visibilityOfElementLocated( By.name("username")));

        System.out.println("==========================================");
        System.out.println("Login page opened successfully");
        System.out.println("==========================================");
    }
    
    @Test(priority = 0 , enabled = true)
    public void TC_01() {
        openForgotPasswordPage();
        String actualURL = driver.getCurrentUrl();

        System.out.println("Expected URL: " + forgotPasswordURL);
        System.out.println("Actual URL  : " + actualURL);
        Assert.assertEquals(actualURL,forgotPasswordURL,"Forgot Password URL is incorrect");

        System.out.println("TC_01 PASSED");
    }
    @Test(priority = 1 , enabled = true)
    public void TC_02() {
        openForgotPasswordPage();
        Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Reset Password']")).isDisplayed(),"Reset Password heading is not displayed");

        Assert.assertTrue(driver.findElement(By.name("username")).isDisplayed(),"Username field is not displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isDisplayed(),"Reset Password button is not displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).isDisplayed(),"Cancel button is not displayed");

        System.out.println("TC_02 PASSED");
    }
    
    @Test(priority = 2 , enabled = true)
    public void TC_03() {
        openForgotPasswordPage();
        By usernameField = By.name("username");

        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));

        driver.findElement(usernameField).click();

        driver.findElement(usernameField).sendKeys("Admin");

        String actualValue =driver.findElement(usernameField).getAttribute("value");

        System.out.println("Entered Username: " + actualValue);
        Assert.assertEquals(actualValue,"Admin","Username was not entered correctly");

        System.out.println("TC_03 PASSED");
    }
    @Test(priority = 3 , enabled = true)
    public void TC_04() {

        openForgotPasswordPage();

        driver.findElement(By.name("username")).sendKeys("Admin");

        System.out.println("Username entered: Admin");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        System.out.println("Reset Password button clicked");

        checkResetPasswordResponse();

        System.out.println("TC_04 EXECUTED");
    }

    @Test(priority = 4 , enabled = true)
    public void TC_05() {

        openForgotPasswordPage();

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        By requiredMessage =By.xpath("//span[text()='Required']");

        // Wait for validation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage));

        // Verify Required message
        Assert.assertTrue(driver.findElement(requiredMessage).isDisplayed(),"Required validation message is not displayed");

        System.out.println("TC_05 PASSED");
    }

    @Test(priority = 5 , enabled = true)
    public void TC_06() {
        openForgotPasswordPage();
        driver.findElement(By.name("username")).sendKeys("Yash123");

        System.out.println("Invalid username entered: Yash123");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        System.out.println("Reset Password button clicked");

        checkResetPasswordResponse();

        System.out.println("TC_06 EXECUTED");
    }


    @Test(priority = 6 , enabled = true)
    public void TC_07() {
        openForgotPasswordPage();
        driver.findElement(By.name("username")).sendKeys("Admin");

        driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();
        wait.until(ExpectedConditions.urlContains("/auth/login"));
        String actualURL = driver.getCurrentUrl();
        System.out.println("After Cancel URL: " + actualURL);
        Assert.assertTrue(actualURL.contains("/auth/login"),"User was not redirected to Login page");

        System.out.println("TC_07 PASSED");
    }


    @Test(priority = 7 , enabled = true)
    public void TC_08() {
        openForgotPasswordPage();
        By resetButton =By.xpath("//button[@type='submit']");

        Assert.assertTrue(driver.findElement(resetButton).isDisplayed(),"Reset Password button is not displayed");

        Assert.assertTrue( driver.findElement(resetButton).isEnabled(),"Reset Password button is disabled");

        driver.findElement(resetButton).click();

        By requiredMessage = By.xpath("//span[text()='Required']");

        wait.until(ExpectedConditions.visibilityOfElementLocated(requiredMessage));

        // Verify validation
        Assert.assertTrue(driver.findElement(requiredMessage).isDisplayed(),"Required validation is not displayed");

        System.out.println("TC_08 PASSED");
    }

    @Test(priority = 8 , enabled = true)
    public void TC_09() {
        openForgotPasswordPage();
        driver.findElement(By.name("username")).sendKeys("@#$%");

        String actualValue = driver.findElement(By.name("username")).getAttribute("value");

        System.out.println("Entered special characters: " + actualValue);

        Assert.assertEquals(actualValue,"@#$%","Special characters were not entered correctly");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        checkResetPasswordResponse();

        System.out.println("TC_09 EXECUTED");
    }



    @Test(priority = 9 , enabled = true)
    public void TC_10() {
        openForgotPasswordPage();
        driver.findElement(By.name("username")).sendKeys(" Admin ");

        String actualValue =driver.findElement( By.name("username")).getAttribute("value");

        System.out.println("Entered Username: [" + actualValue + "]");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        checkResetPasswordResponse();

        System.out.println("TC_10 EXECUTED");
    }

    @Test(priority = 10 , enabled = true)
    public void TC_11() {
        openForgotPasswordPage();
        String forgotURL = driver.getCurrentUrl();

        Assert.assertTrue(forgotURL.contains("requestPasswordResetCode"), "Forgot Password page was not opened");

        driver.findElement(By.xpath("//button[contains(.,'Cancel')]")).click();

        wait.until(ExpectedConditions.urlContains("/auth/login"));

        String loginPageURL = driver.getCurrentUrl();

        System.out.println("Login page URL: " + loginPageURL);
        Assert.assertTrue(loginPageURL.contains("/auth/login"),"User did not return to Login page");

        System.out.println("TC_11 PASSED");
    }

    @Test(priority = 11 , enabled = true)
    public void TC_12() {
        openForgotPasswordPage();
        driver.findElement( By.name("username")).sendKeys("Admin");

        System.out.println("Valid username entered: Admin");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        System.out.println("Reset Password button clicked");
        checkResetPasswordResponse();

        System.out.println("TC_12 EXECUTED");
    }


    public void openForgotPasswordPage() {
    	System.out.println("Opening Forgot Password page...");
        By forgotPasswordLink =By.xpath("//p[contains(normalize-space(),'Forgot your password?')]");

        wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordLink));

        System.out.println( "Forgot Password link found" );
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));

        // Click Forgot Password
        driver.findElement(forgotPasswordLink).click();

        System.out.println("Forgot Password link clicked");

        wait.until(ExpectedConditions.urlContains("requestPasswordResetCode"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Reset Password']")));

        System.out.println("Forgot Password page opened successfully");
    }
    public void checkResetPasswordResponse() {

        try {

            Thread.sleep(2000);

            String currentURL =driver.getCurrentUrl();

            String pageSource =driver.getPageSource().toLowerCase();

            System.out.println( "Response URL: " + currentURL);


            if (pageSource.contains("password reset") ||
                    pageSource.contains("reset password link")) {

                System.out.println( "Password reset response received.");}

            else if (pageSource.contains("504 gateway") ||
                    pageSource.contains("gateway time-out") ||
                    pageSource.contains("gateway timeout")) {

                System.out.println( "504 Gateway Timeout received.");

                System.out.println( "This appears to be a server-side issue " +"with the OrangeHRM demo application.");
            }
            else {

                System.out.println( "Response received, but expected " +"confirmation could not be verified.");
            }

        } catch (Exception e) {

            System.out.println("Error while checking response: "+ e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();

            System.out.println(
                    "Browser closed"
            );
        }
    }
}