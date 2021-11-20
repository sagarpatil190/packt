package packtpub.automation.stepdef;

import com.cucumber.listener.Reporter;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import packtpub.automation.util.TestContext;

import java.util.List;

public class AutomationTestingExercise {
    TestContext testContext;
    WebDriver driver;

    List<WebElement> carousels;

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

    @When("^User clicks on titles in the carousel below the main title inside \"([^\"]*)\" section\\.$")
    public void userClicksOnTitlesInTheCarouselBelowTheMainTitleInsideYourSuggestedTitlesSection(String page) {
         carousels = testContext.getObjectManager().getElements("HomePage_Carousel_Title_Text");
    }

    @Then("^User should be able to see carousel title in main title\\.$")
    public void userShouldBeAbleToSeeCarouselTitleInMainTitle() {
        for (WebElement carousel:carousels) {
            String expectedTitle = carousel.getText();
            carousel.click();
            String actualMainTitle = testContext.getObjectManager().getElement("HomePage_Main_Title_Text").getText();
            if(!actualMainTitle.equalsIgnoreCase(expectedTitle)){
                Assert.fail("User was failed to see carousel title in main title.");
            }
        }
    }


    @Then("^User should be able to see browse page$")
    public void userShouldBeAbleToSeeBrowsePage() {
        try {
            testContext.getObjectManager().getElement("BrowsePage_Search_Input").isDisplayed();
        } catch (Exception e) {
            Assert.fail("User Failed to navigate browse page.", e.fillInStackTrace());
        }
    }

    @When("^User remove filter if any already set$")
    public void userRemoveFilterIfAlreadySet() {
        try {
            if(testContext.getObjectManager().getElement("BrowsePage_ClearAll_Span").isDisplayed())
                testContext.getObjectManager().getElement("BrowsePage_ClearAll_Span").click();

        } catch (Exception e) {
            //do nothing as it is an optional check
        }

    }

    @And("^User set publication year as \"([^\"]*)\"$")
    public void userSetPublicationYearAs(String arg0) throws Throwable {

    }

    @Then("^User validates search results for below texts$")
    public void userValidatesSearchResultsForBelowTexts(DataTable dataTable) {

    }

    @Then("^User set publication year as \"([^\"]*)\" and validates search results for below texts$")
    public void userSetPublicationYearAsAndValidatesSearchResultsForBelowTexts(String arg0, DataTable dataTable) throws Throwable {
         try {
            List<List<String>> data = dataTable.raw();
            for (List<String> searchQuery : data){
                if(testContext.getObjectManager().getElement("BrowsePage_ClearAll_Span").isDisplayed())
                    testContext.getObjectManager().getElement("BrowsePage_ClearAll_Span").click();
                Thread.sleep(1000);
                testContext.getObjectManager().getElement("BrowsePage_PublicationYear_Input",arg0).click();
                Thread.sleep(500);
                testContext.getObjectManager().getElement("BrowsePage_Search_Input").clear();
                testContext.getObjectManager().getElement("BrowsePage_Search_Input").sendKeys(searchQuery.get(0));
                testContext.getObjectManager().getElement("BrowsePage_Search_Input").sendKeys(Keys.ENTER);
                Thread.sleep(1000);
                List<WebElement> searchResults = testContext.getObjectManager().getElements("BrowsePage_Search_Result");
                for (WebElement searchResult: searchResults ) {

                    String actualSerachResult = searchResult.getText();
                    if(!actualSerachResult.contains(searchQuery.get(0))){
                        Reporter.addStepLog(("1 or more than 1 Search results are not matching with the search query for:"+searchQuery.get(0)));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            //do nothing as it is an optional check
        }
    }
}

