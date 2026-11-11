package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.LoginPage;

public class LoginTests {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        // Automatically handles and sets up the matching ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessage();
        System.out.println("Actual Flash Message: " + message);
        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidCredentials() {
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessage();
        System.out.println("Actual Flash Message: " + message);
        Assert.assertTrue(message.contains("Your username is invalid!"));

    }@Test

    public void testEmptyCredentials() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessage();
        System.out.println("Actual Flash Message: " + message);
        Assert.assertTrue(message.contains("Your username is invalid!"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}