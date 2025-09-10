package utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentTest test;

    // Get or create ExtentReports instance
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String reportFolder = "target/ExtentReports";
            new File(reportFolder).mkdirs(); // create folder if not exists
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = reportFolder + "/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Prabhu");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    // **Create a test** (this is the method your Hooks class calls)
    public static ExtentTest createTest(String testName) {
        test = getExtentReports().createTest(testName);
        return test;
    }

    // Flush reports
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
