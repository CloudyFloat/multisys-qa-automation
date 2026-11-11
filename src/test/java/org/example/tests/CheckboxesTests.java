package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.example.pages.CheckboxesPage;

public class CheckboxesTests {
    WebDriver driver;
    CheckboxesPage checkboxesPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        checkboxesPage = new CheckboxesPage(driver);
    }

    @Test
    public void testModifyCheckboxState() {
        // Verify initial states (Checkbox 1 should be unchecked, Checkbox 2 should be checked)
        boolean initialState1 = checkboxesPage.isCheckboxSelected(0);
        boolean initialState2 = checkboxesPage.isCheckboxSelected(1);

        System.out.println("Initial State - Checkbox 1: " + initialState1 + " | Checkbox 2: " + initialState2);
        Assert.assertFalse(initialState1, "Checkbox 1 should be unchecked initially.");
        Assert.assertTrue(initialState2, "Checkbox 2 should be checked initially.");

        // Modify states by clicking both
        checkboxesPage.clickCheckbox(0);
        checkboxesPage.clickCheckbox(1);

        // Verify updated states
        boolean updatedState1 = checkboxesPage.isCheckboxSelected(0);
        boolean updatedState2 = checkboxesPage.isCheckboxSelected(1);

        System.out.println("Updated State - Checkbox 1: " + updatedState1 + " | Checkbox 2: " + updatedState2);
        Assert.assertTrue(updatedState1, "Checkbox 1 should now be checked.");
        Assert.assertFalse(updatedState2, "Checkbox 2 should now be unchecked.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}