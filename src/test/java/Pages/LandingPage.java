package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends ReusableMethods {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // this will initialise all elements in the current page
    }


    //WebElement Email = driver.findElement(By.id("userEmail")).sendKeys("sri@test.com"); // or
    @FindBy(id = "userEmail")
    WebElement Email;
    @FindBy(id = "userPassword")
    WebElement password;
    @FindBy(css = "[class*='flyInOut']")
    WebElement ErrorMsg;
    public static By Submit = By.id("login");

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    public ProductCatPage LoginApplication(String email, String pwd) throws InterruptedException {
        Email.sendKeys(email);
        password.sendKeys(pwd);
        ClickElement(Submit);
        ProductCatPage PC = new ProductCatPage(driver);
        return PC;
    }

    public String getErrorMessage() {
        waitUntilWebElementIsVisible(ErrorMsg);
        return ErrorMsg.getText();

    }
}
