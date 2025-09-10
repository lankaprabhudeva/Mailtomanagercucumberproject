package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.SendMail;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        driver = DriverFactory.initDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        // Create Extent Test
        ExtentManager.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // Capture screenshot if scenario failed
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                ExtentManager.test.fail("Scenario Failed")
                           .addScreenCaptureFromBase64String(
                                java.util.Base64.getEncoder().encodeToString(screenshot));
            } else {
                ExtentManager.test.pass("Scenario Passed");
            }

            // Flush Extent Report
            ExtentManager.flushReports();

            // Get latest report path
            File reportFolder = new File("target/ExtentReports");
            File[] files = reportFolder.listFiles((dir, name) -> name.endsWith(".html"));
            File latestReport = Arrays.stream(files)
                                      .max(Comparator.comparingLong(File::lastModified))
                                      .get();

            // Send report via email
            SendMail.sendReport(latestReport.getAbsolutePath(), "devalanka1997@gmail.com");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.quitDriver();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
