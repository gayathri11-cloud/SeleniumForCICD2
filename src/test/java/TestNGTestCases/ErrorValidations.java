package TestNGTestCases;

import Utilities.BrowserDriver;
import Utilities.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BrowserDriver {
    @Test(groups = {"ErrorHandlingTests"}, retryAnalyzer = Retry.class)
    public void ValidateErrorAtLogin() throws InterruptedException {
        landingPage.LoginApplication("sri@test.com","Sre@123");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

}
