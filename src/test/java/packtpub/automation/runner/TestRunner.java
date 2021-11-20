package packtpub.automation.runner;



import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

// Cucumber options to set report structure.
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "packtpub.automation.stepdef",
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        monochrome = true

)
// Cucumber test runner for default chrome browser has initialized
public class TestRunner extends AbstractTestNGCucumberTests {
    public static String BrowserName="CHROME";

    @BeforeTest
    @Parameters({ "BrowserName"})
    public void setUpClass(String BrowserName) throws Exception {
        this.BrowserName = BrowserName;

    }

}