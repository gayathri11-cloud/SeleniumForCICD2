package Runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "StepDefinitions",
        monochrome=true,
        tags="@ErrorValidation",
        plugin = {"html:target/cucumber.html"}
)
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
