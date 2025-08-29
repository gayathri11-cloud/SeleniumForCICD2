package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends ReusableMethods {
    WebDriver driver;
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public String verifyConfirmationMsg(){
        return driver.findElement(By.cssSelector(".hero-primary")).getText();
    }
}
