package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePageTests extends TestBase {


    @BeforeMethod
    public void loginToAppl() throws InterruptedException {
        waitUntilElementClickable(By.id("idsignin"),20);
        driver.findElement(By.id("idsignin")).click();
        WebElement loginField = driver.findElement(By.id("logininput"));
        loginField.click();
        loginField.sendKeys(LOGIN);
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.click();
        passwordField.sendKeys(PASSWORD);
        WebElement signInButton = driver.findElement(By.id("signinrequest"));
        signInButton.click();
        //Thread.sleep(4000);
        waitUntilElementClickable(By.id("profile"),20);
        //builder = new Actions(driver);
    }

    @Test
    public void profileTitleTest() throws InterruptedException {

        WebElement profileButton
                = driver.findElement(By.id("profile"));
        profileButton.click();
        waitUntilElementClickable(By.id("idbtneditprofile"),20);
        //System.out.println("Text1: " + driver.findElement(By.id("titleprofile")).getText());
        waitUntilElemContainsText(By.id("titleprofile"),"My Profile",20);
        //System.out.println("Text2: " + driver.findElement(By.id("titleprofile")).getText());

        WebElement titleProfile
                = driver.findElement(By.id("titleprofile"));

        Assert.assertEquals(titleProfile.getText(),"My Profile: " + LOGIN);
    }

    @Test
    public void profileURGuestTest() throws InterruptedException {
        Actions builder = new Actions(driver);

        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        //waitUntilElementClickable(By.id("idbtnchangepassprofile"),10);
        //driver.findElement(By.id("idbtnchangepassprofile")).click();
        //waitUntilElementClickable(By.id("idbtnchangepasscancel"),15);

        waitUntilElementClickable(By.id("idbtneditprofile"),10);
        builder.moveToElement(driver.findElement(By.id("idbtneditprofile"))).perform();
        driver.findElement(By.id("idbtneditprofile")).click();

        waitUntilElementClickable(By.id("typeuser1inprofile"),10);
        driver.findElement(By.id("typeuser1inprofile")).click();

        waitUntilElemAttrContainsText(By.id("typeuser1inprofile"),
                "class","active",5);
        driver.findElement(By.id("idbtnsaveprofile")).click();

        waitUntilElementClickable(By.id("idbtneditprofile"),10);


        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        waitUntilElementClickable(By.xpath("//span[@id='typeviewsearchcalendar']"),10);
        System.out.println("Guest, + is  hidden: " + !driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }

    @Test
    public void profileURFamilyTest() throws InterruptedException {
        Actions builder = new Actions(driver);
        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        //waitUntilElementClickable(By.id("idbtnchangepassprofile"),15);
        //driver.findElement(By.id("idbtnchangepassprofile")).click();
        waitUntilElementClickable(By.id("idbtneditprofile"),15);

        builder.moveToElement(driver.findElement(By.id("idbtneditprofile"))).perform();
        driver.findElement(By.id("idbtneditprofile")).click();
        waitUntilElementClickable(By.id("typeuser2inprofile"),10);
        driver.findElement(By.id("typeuser2inprofile")).click();
        waitUntilElemAttrContainsText(By.id("typeuser2inprofile"),
                "class","active",10);
        driver.findElement(By.id("idbtnsaveprofile")).click();
        waitUntilElementClickable(By.id("idbtneditprofile"),15);

        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        waitUntilElementClickable(By.xpath("//span[@id='typeviewsearchcalendar']"),10);
        System.out.println("Family, + is  displayed: " + driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }

    @Test
    public void profileURFamilyAndGustTest()  {
        Actions builder = new Actions(driver);
        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        //waitUntilElementClickable(By.id("idbtnchangepassprofile"),15);
       // driver.findElement(By.id("idbtnchangepassprofile")).click();
        waitUntilElementClickable(By.id("idbtneditprofile"),15);
        builder.moveToElement(driver.findElement(By.id("idbtneditprofile"))).perform();
        driver.findElement(By.id("idbtneditprofile")).click();
        waitUntilElementClickable(By.id("typeuser3inprofile"),15);
        driver.findElement(By.id("typeuser3inprofile")).click();
        waitUntilElemAttrContainsText(By.id("typeuser3inprofile"),
                "class","active",5);
        driver.findElement(By.id("idbtnsaveprofile")).click();
        waitUntilElementClickable(By.id("idbtneditprofile"),20);

        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        waitUntilElementClickable(By.xpath("//span[@id='typeviewsearchcalendar']"),10);
        System.out.println("FamilyAndGuest, + is  displayed: " + driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }
}
