package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CheckoutPage extends ReusableMethods {
WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public static By submitBtn = By.cssSelector(".action__submit");
    public static By country = By.cssSelector("input[placeholder='Select Country']");


    public void selectCountry(String countryName) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(country),countryName).build().perform();
        Thread.sleep(2000);
        driver.findElements(By.cssSelector("section .ta-item")).stream()
                .filter(s->s.getText().equalsIgnoreCase(countryName)).findFirst().orElse(null).click();

    }

    public ConfirmationPage goToConfirmationPage(){
//        driver.findElement(submitBtn).click();
        ClickElement(submitBtn);
        return new ConfirmationPage(driver);
    }
}
