package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase{

    @BeforeMethod
    public void initTests(){
        waitUntilElementClickable(By.id("idsignin"),20);
    }


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
        waitUntilElementClickable(By.id("profile"),10);
        WebElement profileButton = driver.findElement(By.id("profile"));
        Assert.assertTrue(
                profileButton.getAttribute("title").contains(LOGIN));
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
        waitUntilElementIsVisible(By.id("wrongloginorpassword"),10);

        //------Receive wrong authorization message and close login window ------
        WebElement wrongAuthText = driver.findElement(By.id("wrongloginorpassword"));
        System.out.println("Wrong Authorization text: " + wrongAuthText.getText().contains("Wrong Authorization!"));
        WebElement closeLogin = driver.findElement(By.id("closedsignin"));
        closeLogin.click();
        waitUntilElementClickable(By.id("idsignin"),10);

        //------- Home window (not authorized) verification -----------
        WebElement homeIcon = driver.findElements(By.className("navi-item")).get(0);
        WebElement loginIcon = driver.findElements(By.className("navi-item")).get(1);
        WebElement registrationIcon = driver.findElements(By.className("navi-item")).get(2);
        WebElement homeAuthIcon = driver.findElement(By.id("ihome"));
        WebElement profileIcon = driver.findElement(By.id("profile"));

        int counter = 0;
        if (homeIcon.isDisplayed()) counter++;
        if (loginIcon.isDisplayed()) counter++;
        if (registrationIcon.isDisplayed())counter++;
        if (!homeAuthIcon.isDisplayed()) counter++;
        if (!profileIcon.isDisplayed()) counter++;
        System.out.println("Home icon is displayed: " + homeIcon.isDisplayed());
        System.out.println("Login icon is displayed: " + loginIcon.isDisplayed());
        System.out.println("Registration icon is displayed: " + registrationIcon.isDisplayed());
        System.out.println("Home auth icon isn't displayed: " + !homeAuthIcon.isDisplayed());
        System.out.println("Profile icon isn't displayed: " + !profileIcon.isDisplayed());
        Assert.assertEquals(counter,5);


    }
}
