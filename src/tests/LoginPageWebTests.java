package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageWebTests extends TestBase {

    @Test
    public void loginPositive() throws InterruptedException {

        driver.findElement(By.id("idsignin")).click();
        WebElement loginField = driver.findElement(By.id("logininput"));
        loginField.click();
        loginField.sendKeys(LOGIN);
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.click();
        passwordField.sendKeys(PASSWORD);
        WebElement signInButton = driver.findElement(By.id("signinrequest"));
        signInButton.click();
        Thread.sleep(2000);
        WebElement profileButton = driver.findElement(By.id("profile"));

        System.out.println("title of profileButton: "
                +profileButton.getAttribute("title"));
        Assert.assertTrue(profileButton.getAttribute("title").contains(LOGIN));
    }

    @Test
    public void loginNegative() throws InterruptedException {


        //--------Login window openning----------------
        driver.findElement(By.id("idsignin")).click();
        Thread.sleep(2000);

        //------- Not correct login/password entering--------------------
        WebElement loginField = driver.findElement(By.id("logininput"));
        loginField.click();
        loginField.sendKeys(LOGIN);
        WebElement passwordField = driver.findElement(By.id("passwordinput"));
        passwordField.click();
        passwordField.sendKeys("psw");
        WebElement signInButton = driver.findElement(By.id("signinrequest"));
        signInButton.click();
        Thread.sleep(3000);

        //------Receive wrong authorization message and close login window ------
        WebElement wrongAuthText = driver.findElement(By.id("wrongloginorpassword"));
        System.out.println("Wrong Authorization text: " + wrongAuthText.getText().contains("Wrong Authorization!"));
        WebElement closeLogin = driver.findElement(By.id("closedsignin"));
        closeLogin.click();
        Thread.sleep(5000);

        //------- Home window (not authorized) verification -----------
        WebElement homeIcon = driver.findElements(By.className("navi-item")).get(0);
        WebElement loginIcon = driver.findElements(By.className("navi-item")).get(1);
        WebElement registrationIcon = driver.findElements(By.className("navi-item")).get(2);
        WebElement homeAuthIcon = driver.findElement(By.id("ihome"));
        WebElement profileIcon = driver.findElement(By.id("profile"));

        System.out.println("Home icon is displayed: " + homeIcon.isDisplayed());
        System.out.println("Login icon is displayed: " + loginIcon.isDisplayed());
        System.out.println("Registration icon is displayed: " + registrationIcon.isDisplayed());
        System.out.println("Home auth icon isn't displayed: " + !homeAuthIcon.isDisplayed());
        System.out.println("Profile icon isn't displayed: " + !profileIcon.isDisplayed());

        Assert.assertTrue(homeIcon.isDisplayed());
        Assert.assertTrue(loginIcon.isDisplayed());
        Assert.assertTrue(registrationIcon.isDisplayed());
        Assert.assertFalse(homeAuthIcon.isDisplayed());
        Assert.assertFalse(profileIcon.isDisplayed());

    }
}
