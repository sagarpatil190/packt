package packtpub.automation.util;


import packtpub.automation.managers.ObjectManager;
import packtpub.automation.managers.WebDriverManager;
import packtpub.automation.runner.TestRunner;

public class TestContext extends TestRunner {
    private WebDriverManager webDriverManager;
    private packtpub.automation.managers.ObjectManager ObjectManager;

    public TestContext(){
        try {
            webDriverManager = new WebDriverManager();
            WebDriverManager.driverType = BrowserName;
            ObjectManager = new ObjectManager(webDriverManager.getDriver());
        } catch (Exception e) {
            webDriverManager.getDriver().quit();
            e.printStackTrace();
            System.exit(0);
        }
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public ObjectManager getObjectManager() {
        return ObjectManager;
    }
}
