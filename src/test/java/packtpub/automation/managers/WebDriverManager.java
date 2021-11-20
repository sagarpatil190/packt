package packtpub.automation.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

//Driver manager class which will manage where test cases needs to run and also create singletone instance of webdriver
public class WebDriverManager {
    private WebDriver driver;
    public static String driverType;
    private static String environmentType ="LOCAL";

    public WebDriverManager() {
        driverType = null;

    }

    public WebDriver getDriver() {

        try {
            if (driver == null) driver = createDriver();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return driver;
    }

    private WebDriver createDriver() {
        try {
            switch (environmentType) {
                case "LOCAL":
                    driver = createLocalDriver();
                    break;
                case "REMOTE":
                    driver = createRemoteDriver();
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case "FIREFOX" :

                //InputStream inputStream = classLoader.getResourceAsStream(fileName);

                System.setProperty("webdriver.gecko.driver", getClass().getClassLoader().getResource("drivers/geckodriver.exe").getPath());
                driver = new FirefoxDriver();
                break;
            case "CHROME" :
                ClassLoader classLoader1 = getClass().getClassLoader();
                System.setProperty("webdriver.chrome.driver", getClass().getClassLoader().getResource("drivers/chromedriver.exe").getPath());
                driver = new ChromeDriver();
                break;

        }
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
            driver.manage().window().maximize();
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

}
