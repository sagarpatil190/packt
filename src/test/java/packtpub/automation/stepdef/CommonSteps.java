package packtpub.automation.stepdef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import packtpub.automation.managers.FileReaderManager;
import packtpub.automation.util.TestContext;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

//This class is created for writing code for common steps.
public class CommonSteps {

    TestContext testContext;
    WebDriver driver;

    public CommonSteps(TestContext context) {
        testContext = context;
        try {
            this.driver = testContext.getWebDriverManager().getDriver();
        } catch (Exception e) {
            Assert.fail("Failed to initialize test:", e.fillInStackTrace());
        }
    }




}
