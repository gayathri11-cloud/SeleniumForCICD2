package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCatPage extends ReusableMethods {

    WebDriver driver;

    public ProductCatPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    public static By productsList = By.xpath("//div[contains(@class,'mb-3')]");
    public static By loadAnimator = By.cssSelector(".ng-animating");
    public static By toastMessage = By.cssSelector("#toast-container");


    public WebElement getItem(String AddedItem){
        waitUntilElementIsVisible((productsList));
        WebElement item=driver.findElements(productsList).stream().filter(product->product.findElement
                        (By.xpath(".//b"))
                .getText().equalsIgnoreCase(AddedItem)).findFirst().orElse(null);
        return item;
    }

    public void addItemToCart(String itemToAdd) throws InterruptedException {
        getItem(itemToAdd).findElement(By.xpath(".//button[last()]")).click();
        waitUntilElementIsInVisible(loadAnimator);
        waitUntilElementIsVisible(toastMessage);

    }
}
