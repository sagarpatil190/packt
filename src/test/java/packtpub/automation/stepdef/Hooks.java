package packtpub.automation.stepdef;


import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import packtpub.automation.runner.TestRunner;
import packtpub.automation.util.TestContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

//this class contains cucumber hooks which will be required across different stepdef's
public class Hooks extends TestRunner {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
                File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
                Files.copy(sourcePath, destinationPath);
                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (Exception e) {
            }
        }
        testContext.getWebDriverManager().quitDriver();
    }
}