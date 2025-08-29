package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;

public class ExtentReportsDemo {
    //ExtentReports and //ExtentSparkReporter
    @BeforeTest
    public static ExtentReports configExtentReport(){
        String filePath = System.getProperty("user.dir")+"\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Purchase Items from Ecommerce");
        reporter.config().setDocumentTitle("Ecommerce Extent report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","abc");
        return extent;
    }

//    @Test
//    public void DemoTest(){
//        extent.createTest("demo");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
//        extent.flush();
//    }


}
