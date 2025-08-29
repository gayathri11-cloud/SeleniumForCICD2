package StepDefinitions;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ConfirmationPage;
import Pages.ProductCatPage;
import Utilities.BrowserDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDef extends BrowserDriver {
    public ProductCatPage pc;
    public ConfirmationPage confirm;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_ecommerce_page() throws IOException {
        LaunchApplication();
    }

    @Given("^a user logged with (.+) and (.+)$")
    public void user_logged_with_creds(String Username, String Password) throws InterruptedException {
        pc = landingPage.LoginApplication(Username, Password);
    }

    @When("^I add (.+) to cart$")
    public void I_add_Product_to_cart(String Product) throws InterruptedException {
        pc.addItemToCart(Product);
    }

    @And("^I checkout (.+) and submit the order$")
    public void Checkout_product(String product) throws InterruptedException {
        //go to cart page - in reusable class as product cat class inherits reusable
        CartPage CP = pc.goToCartPage();
        //verify cart Items
        Boolean presentOrNot = CP.verifyCartPage(product);
        Assert.assertTrue(presentOrNot);
        //click on checkout
        CheckoutPage checkP = CP.goToCheckout();
        //select country
        checkP.selectCountry("India");
        //submit
        confirm = checkP.goToConfirmationPage();
    }

    @Then("{string} message is displayed on confirmation page")
    public void verify_confirmation_message(String str) {
        String message = confirm.verifyConfirmationMsg();
        Assert.assertTrue(message.equalsIgnoreCase(str));
    }

    @Then("{string} message is displayed on landing page")
    public void verify_error_message(String str) {
        Assert.assertEquals(landingPage.getErrorMessage(),str);
    }

    @Then("CLose the browser")
    public void close_browser(){
        driver.quit();
    }


}
