package Pages;

import Utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersPage extends ReusableMethods {
    WebDriver driver;
    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public static By ordersInOrderPage = By.cssSelector(".table-bordered tbody tr td:nth-child(3)");

    public boolean verifyOrder(String item){
        Boolean presentOrNot = driver.findElements(ordersInOrderPage).stream()
                .anyMatch(order->order.getText().equalsIgnoreCase(item));
        return presentOrNot;
    }
}
