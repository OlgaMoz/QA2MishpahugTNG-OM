package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String LOGIN = "marinaA";
    public static final String PASSWORD = "marina1!";

    public WebDriver driver;

    @BeforeMethod
    public void startApplication() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://mishpahug.co.il/");
        driver.findElement(By.id("closedIntro")).click();
        //Thread.sleep(4000);
        //waitUntilElementClickable(By.id("idsignin"),20);
    }

    @AfterMethod
    public void tearDown(){

        driver.quit();
    }

    public void waitUntilElementClickable(By locator, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(By locator, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElemAttrContainsText(By locator, String attribute, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.attributeContains(locator,attribute,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElemContainsText(By locator, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }


}
