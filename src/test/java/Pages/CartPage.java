package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends ReusableMethods {
WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public static By ItemsInCart = By.cssSelector(".cartSection h3");
    public static By CheckoutBtn = By.cssSelector(".totalRow button");

    public boolean verifyCartPage(String AddedItem){
        Boolean presentOrNot = driver.findElements(ItemsInCart).stream()
                        .anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(AddedItem));
        return presentOrNot;

    }

    public CheckoutPage goToCheckout(){
        ClickElement(CheckoutBtn);
        return new CheckoutPage(driver);
    }


}
