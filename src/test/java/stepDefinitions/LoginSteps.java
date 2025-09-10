package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.LoginPage;
import pages.ProfilePage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentManager;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;
    ProfilePage profilePage;

    @Given("I launch the browser and open the Naukri site")
    public void i_launch_the_browser_and_open_the_naukri_site() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.getProperty("url"));
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);

        ExtentManager.test.info("Browser launched and Naukri site opened");
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        try {
            loginPage.clickLogin();
            loginPage.enterEmail(ConfigReader.getProperty("username"));
            loginPage.enterPassword(ConfigReader.getProperty("password"));
            loginPage.clickSubmit();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlContains("naukri.com"));

            ExtentManager.test.pass("Logged in successfully");
        } catch (Exception e) {
            ExtentManager.test.fail("Login failed: " + e.getMessage());
            takeScreenshot("LoginFail");
            throw e;
        }
    }

    @When("I navigate to the update profile page")
    public void i_navigate_to_the_update_profile_page() {
        try {
            profilePage.clickProfilePicture();
            profilePage.clickViewUpdateProfile();
            ExtentManager.test.pass("Navigated to Update Profile page");
        } catch (Exception e) {
            ExtentManager.test.fail("Navigation to profile page failed: " + e.getMessage());
            takeScreenshot("ProfileNavFail");
            throw e;
        }
    }

    @When("I scroll down to the resume section")
    public void i_scroll_down_to_the_resume_section() {
        try {
            profilePage.scrollToResumeSection();
            ExtentManager.test.info("Scrolled to resume section");
        } catch (Exception e) {
            ExtentManager.test.fail("Scrolling failed: " + e.getMessage());
            takeScreenshot("ScrollFail");
            throw e;
        }
    }

    @When("I upload my latest resume")
    public void i_upload_my_latest_resume() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement uploadInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@value='Update resume']")
            ));

            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", uploadInput);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", uploadInput);
            uploadInput.sendKeys(ConfigReader.getProperty("resumePath"));

            ExtentManager.test.pass("Resume uploaded successfully");
        } catch (Exception e) {
            ExtentManager.test.fail("Resume upload failed: " + e.getMessage());
            takeScreenshot("ResumeUploadFail");
            throw e;
        }
    }

    @Then("I close the browser")
    public void i_close_the_browser() {
        DriverFactory.quitDriver();
        ExtentManager.test.info("Browser closed");
    }

    // Utility method to take screenshot and attach to Extent Report
    public void takeScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            ExtentManager.test.addScreenCaptureFromBase64String(
                    java.util.Base64.getEncoder().encodeToString(screenshot), name);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
