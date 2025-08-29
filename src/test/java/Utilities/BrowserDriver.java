package Utilities;

import Pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BrowserDriver {
    public LandingPage landingPage;
    public WebDriver driver;

    public WebDriver InitializeDriver() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//Global.properties");
        prop.load(fis);

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//        String browserName = prop.getProperty("browser");

//        WebDriverManager.chromedriver().setup();
        if(browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")){
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            driver=new ChromeDriver(options);
//            driver.manage().window().setSize(new Dimension(1920,1080));
        }
        else if(browserName.equalsIgnoreCase("firefox")) driver=new FirefoxDriver();
        else if(browserName.equalsIgnoreCase("edge")) driver=new EdgeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        //read json to string - in built
//        String jsonContent = FileUtils.readFileToString(new File(filePath));
        //string to hashmap - add dependancy jackson data bind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(new File(filePath), new TypeReference<List<HashMap<String,String>>>(){});
        return data;
    }

    @BeforeMethod(alwaysRun = true)
    public void LaunchApplication() throws IOException {
        driver = InitializeDriver();
        landingPage= new LandingPage(driver);
        landingPage.goTo();
    }

    @AfterMethod(alwaysRun = true)
    public void CloseBrowser(){
        driver.quit();
    }

}
