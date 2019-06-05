package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FamilyPageTests extends TestBase {

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
    public void profileFamilyInfoTest() throws InterruptedException {

        //-------- Status family verification -----------------------------
        driver.findElement(By.id("profile")).click();
        Thread.sleep(4000);
        if (driver.findElement(By.id("statusinmishpahuginprofile")).getText().equals("Guest"))
        {

            driver.findElement(By.id("idbtneditprofile")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("typeuser2inprofile")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("idbtnsaveprofile")).click();
            Thread.sleep(5000);
        }

        //------- Profile fields values saving ----------------------------
        String confessionProfile = driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText();
        String languagesProfile = driver.findElement(By.xpath("//div[contains(text(),'Languages:')]/../span")).getText();
        String foodProfile = driver.findElement(By.xpath("//span[@id='fieldobjfoodPreferences']")).getText();

        //-------Family fields values verification ------------------------
        driver.findElement(By.id("family")).click();
        Thread.sleep(4000);
        int counter = 0;
        if (driver.findElement(By.cssSelector("#fieldobjconfession")).getText().equals(confessionProfile))
                                    counter++;
        if (driver.findElement(By.cssSelector("span[id='fieldobjlanguages']")).getText().equals(languagesProfile))
                                    counter++;
        if (driver.findElement(By.cssSelector("#fieldobjfoodPreferences")).getText().equals(foodProfile))
                                    counter++;
       /* System.out.println("Confession verification: "
                + driver.findElement(By.cssSelector("#fieldobjconfession")).getText().equals(confessionProfile));
        System.out.println("Languages verification: "
                + driver.findElement(By.cssSelector("span[id='fieldobjlanguages']")).getText().equals(languagesProfile));
        System.out.println("Food verification: "
                + driver.findElement(By.cssSelector("#fieldobjfoodPreferences")).getText().equals(foodProfile));
        */
        Assert.assertEquals(3,counter);
    }
}
