package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfilePageWebTests extends TestBase{

    @BeforeMethod
    public void loginCorrect() throws InterruptedException {
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
        System.out.println("Title profile verification: "
                + titleProfile.getText().contains(LOGIN));
        Assert.assertEquals(titleProfile.getText(),"My Profile: " + LOGIN);

    }


}
