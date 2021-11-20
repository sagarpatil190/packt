package packtpub.automation.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

//object manager class for managing the objects
public class ObjectManager {
    private WebDriver driver;
    private HashMap<String, String> ObjectsRepo;
    private final String ObjectsRepoFilePath = getClass().getClassLoader().getResource("ObjectRepository.loc").getPath();

    public ObjectManager(WebDriver driver) {
        this.driver = driver;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(ObjectsRepoFilePath));
            try {
                ObjectsRepo = new HashMap<>();
                String line=null;
                while((line=reader.readLine())!=null) {
                    String[] temp =line.split(":",2);
                    ObjectsRepo.put(temp[0],temp[1]);

                }
            } catch (Exception e) {
                throw new RuntimeException("Locators are not in expected format- [LocatorName]:[LocatorType]=[LocatorValue]");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found at path : " + ObjectsRepoFilePath);
        }finally {
            try { if(reader != null) reader.close(); }
            catch (IOException ignore) {}
        }
    }

    public WebElement getElement(String objectName, String... arg){
        String locatorType = null, locatorValue = null;
        try {
            locatorType = ObjectsRepo.get(objectName).split("=", 2)[0].toUpperCase();
            locatorValue = String.format(ObjectsRepo.get(objectName).split("=", 2)[1],arg);
        } catch (Exception e) {
            throw new RuntimeException("Locators are not in expected format- [LocatorName]:[LocatorType]=[LocatorValue] or either it is not added in object repository.");
        }
        WebDriverWait wait=new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(by(locatorType,locatorValue)));
        return driver.findElement(by(locatorType,locatorValue));
    }

    public List<WebElement> getElements(String objectName, String ... arg){
        String locatorType = null, locatorValue = null;
        try {
            locatorType = ObjectsRepo.get(objectName).split("=", 2)[0].toUpperCase();
            locatorValue = String.format(ObjectsRepo.get(objectName).split("=", 2)[1],arg);

        } catch (Exception e) {
            throw new RuntimeException("Locators are not in expected format- [LocatorName]:[LocatorType]=[LocatorValue] or either it is not added in object repository.");
        }
        WebDriverWait wait=new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(by(locatorType,locatorValue)));
        return driver.findElements(by(locatorType,locatorValue));
    }


    private By by(String locatorType,String locatorValue){
        switch (locatorType){
            case "XPATH":
                return By.xpath(locatorValue);
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "LINKTEXT":
                return By.linkText(locatorValue);
            case "PARTIALLINKTEXT":
                return By.partialLinkText(locatorValue);
            case "TAGNAME":
                return By.tagName(locatorValue);
            default:
                System.out.println("Please provide valid Locator Type");
        }
        return null;
    }

}
