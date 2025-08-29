package Utilities;

import Pages.CartPage;
import Pages.OrdersPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ReusableMethods {
    WebDriver driver;
    public static By cartIcon = By.cssSelector("button[routerlink*='/cart']");
    public static By OrdersIcon = By.cssSelector("button[routerlink*='/myorders']");

    public ReusableMethods(){

    }
    public ReusableMethods(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void waitUntilElementIsInVisible(By by) throws InterruptedException {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        Thread.sleep(2000);
//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(by)); // u can use above one or this

    }

    public void waitUntilElementIsVisible(By by){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public void waitUntilWebElementIsVisible(WebElement element){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public CartPage goToCartPage(){
        Actions a = new Actions(driver);
        try{
            a.moveToElement(driver.findElement(cartIcon)).click().perform();
        }catch (Exception e){
            driver.findElement(cartIcon).click();
        }
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        ClickElement(OrdersIcon);
        return new OrdersPage(driver);
    }

    public String takeScreenshot(String testcaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dtn = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
        FileUtils.copyFile(src,dtn);
        return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
        //returning path
    }

    public void ClickElement(By by){
        Actions a = new Actions(driver);
        a.click(driver.findElement(by)).perform();
    }
}
