package packtpub.automation.stepdef;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import packtpub.automation.util.TestContext;

import java.util.List;

public class AutomationTestingExercise {
    TestContext testContext;
    WebDriver driver;

    public AutomationTestingExercise(TestContext context) {
        testContext = context;
        try {
            this.driver = testContext.getWebDriverManager().getDriver();
        } catch (Exception e) {
            Assert.fail("Failed to initialize test:", e.fillInStackTrace());
        }
    }

    @When("^User clicks on \"([^\"]*)\" link in top nav bar menu$")
    public void userClicksOnLinkInTopNavBarMenu(String menu) throws Throwable {
        try {
            testContext.getObjectManager().getElement(menu).click();

        } catch (Exception e) {
            Assert.fail("User Failed to click on :" + menu, e.fillInStackTrace());
        }

    }

    @Then("^User accesses each category from the dropdown$")
    public void userAccessesEachSubmenuFromTheDropdown() throws InterruptedException {
        List<WebElement>  categories = testContext.getObjectManager().getElements("HomePage_Browse_Menu_Categories","1");
        for (int i=1; i< categories.size();i++) {
            WebElement category = testContext.getObjectManager().getElement("HomePage_Browse_Menu_Category",""+i);
            if(category.isDisplayed()){
                String expectedLinkText = category.getAttribute("href");
                category.click();
                Thread.sleep(1000);
                String actualLinkText = testContext.getWebDriverManager().getDriver().getCurrentUrl();
                if(!actualLinkText.equalsIgnoreCase(expectedLinkText)){
                    Assert.fail("User Failed to navigate to " +expectedLinkText);
                }
            }
            testContext.getObjectManager().getElement("HomePage_Browse_Menu").click();

        }

        }
    }

