package TestNGTestCases;

import Pages.*;
import Utilities.BrowserDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BrowserDriver {
    String AddedItem = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"PurchaseItems"})
    public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        //login in with creds

        ProductCatPage pc = landingPage.LoginApplication(input.get("email"), input.get("pwd"));
        //add Item to cart
        pc.addItemToCart(input.get("product"));
        //go to cart page - in reusable class as product cat class inherits reusable
        CartPage CP = pc.goToCartPage();
        //verify cart Items
        Boolean presentOrNot = CP.verifyCartPage(input.get("product"));
        Assert.assertTrue(presentOrNot);
        //click on checkout
        CheckoutPage checkP = CP.goToCheckout();
        //select country
        checkP.selectCountry("India");
        //submit
        ConfirmationPage confirm = checkP.goToConfirmationPage();
        //verify msg
        String message = confirm.verifyConfirmationMsg();
        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        Thread.sleep(2000);
    }

    @Test(dependsOnMethods = {"SubmitOrder"})
    public void VerifyOrdersPage() throws InterruptedException {
        ProductCatPage pc = landingPage.LoginApplication("sri@test.com", "Sree@123");
        OrdersPage OP = pc.goToOrdersPage();
        Assert.assertTrue(OP.verifyOrder(AddedItem));
    }

    //using object - 1, here 2 parameters are passed to test method
//    @DataProvider
//    public Object[][] getData(){
//        return new Object[][]{{"sri@test.com","Sree@123","ZARA COAT 3"},{"sri@test.com","Sree@123","ADIDAS ORIGINAL"}};
//    }

    //using haspMap - 2, for hashmap, 1 parameter is passed as param to test method
    //Object[] -> number of test executions
    //object 2nd brace[]->no of parameters send to test method
//    @DataProvider
//    public Object[][] getData(){
//        HashMap<String,String> data1 = new HashMap<>();
//        data1.put("email","sri@test.com");
//        data1.put("pwd","Sree@123");
//        data1.put("product","ZARA COAT 3");
//
//        HashMap<String,String> data2 = new HashMap<>();
//        data2.put("email","sri@test.com");
//        data2.put("pwd","Sree@123");
//        data2.put("product","ADIDAS ORIGINAL");
//
//        return new Object[][]{{data1},{data2}};
//    }

    //using JSON file - 3
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "//src//test//java//Data//SubmitOrderData.json");
        Object[][] data2 = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            data2[i][0] = data.get(i);
        }
        return data2;
    }
}
