package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePageTests extends TestBase {

    @BeforeMethod
    public void loginToAppl() throws InterruptedException {
        driver.findElement(By.id("idsignin")).click();
        WebElement loginField = driver.findElement(By.id("logininput"));
        loginField.click();
        loginField.sendKeys(LOGIN);
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.click();
        passwordField.sendKeys(PASSWORD);
        WebElement signInButton = driver.findElement(By.id("signinrequest"));
        signInButton.click();
        Thread.sleep(4000);
    }

    @Test
    public void profileTitleTest() throws InterruptedException {

        WebElement profileButton
                = driver.findElement(By.id("profile"));
        profileButton.click();
        Thread.sleep(2000);
        WebElement titleProfile
                = driver.findElement(By.id("titleprofile"));

        Assert.assertEquals(titleProfile.getText(),"My Profile: " + LOGIN);
    }

    @Test
    public void profileURGuestTest() throws InterruptedException {
        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtneditprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("typeuser1inprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtnsaveprofile")).click();
        Thread.sleep(3000);

        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        Thread.sleep(4000);
        System.out.println("Guest, + is  hidden: " + !driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }

    @Test
    public void profileURFamilyTest() throws InterruptedException {
        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtneditprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("typeuser2inprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtnsaveprofile")).click();
        Thread.sleep(3000);

        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        Thread.sleep(4000);
        System.out.println("Family, + is  displayed: " + driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }

    @Test
    public void profileURFamilyAndGustTest() throws InterruptedException {
        //-------------------change user role to Guest------
        driver.findElement(By.id("profile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtneditprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("typeuser3inprofile")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("idbtnsaveprofile")).click();
        Thread.sleep(3000);

        //----------------user rights verification: '+' has to be unavailable ----------
        driver.findElement(By.id("ihome")).click();
        Thread.sleep(4000);
        System.out.println("FamilyAndGuest, + is  displayed: " + driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("idcontainerbtnaddevent")).isDisplayed());
    }
}
